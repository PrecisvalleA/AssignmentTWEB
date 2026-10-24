/**
 * FrameVerse - Movie Details Page Logic
 *
 * Fetches and renders:
 * - Movie Details (from Spring Boot API via API Gateway)
 * - Reviews (from MongoDB Microservice via API Gateway)
 * - Supports filtering reviews by Top Critic, Type, Sort by Date
 * - Supports paginated reviews ("Load More")
 * - Real-time chat per movie (Socket.IO)
 * - Smooth scrolling for internal sections
 */
document.addEventListener('DOMContentLoaded', () => {

    const API_URL = window.API_URL || "http://localhost:3000";

    const movieDetailsContainer = document.getElementById('movie-details');
    const loader = document.getElementById('loader');
    const reviewsContainer = document.getElementById('movie-reviews');
    const loadMoreReviewsBtn = document.getElementById('load-more-reviews');

    // Get movie ID from URL params
    const movieId = new URLSearchParams(window.location.search).get('id');
    let currentMovieTitle = '';

    // Reviews pagination
    let currentPageReviews = 0;
    const limitReviews = 5;

    // Shown when a poster is missing or cannot be loaded
    const PLACEHOLDER_POSTER = 'data:image/svg+xml;utf8,' + encodeURIComponent(
        '<svg xmlns="http://www.w3.org/2000/svg" width="200" height="300">' +
        '<rect width="100%" height="100%" fill="#222"/>' +
        '<text x="50%" y="50%" fill="#999" font-size="18" text-anchor="middle" ' +
        'font-family="sans-serif">No poster</text></svg>');

    // ---------- Helpers ----------

    /** Escapes a value before it is inserted in an HTML template (prevents HTML injection). */
    function esc(value) {
        return String(value ?? '').replace(/[&<>"']/g, c => ({
            '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
        }[c]));
    }

    function showLoader() {
        if (loader) loader.style.display = 'block';
    }

    function hideLoader() {
        if (loader) loader.style.display = 'none';
    }

    function formatDate(value) {
        const d = new Date(value);
        return isNaN(d) ? '-' : d.toLocaleDateString();
    }

    function generateStars(rating) {
        const maxStars = 5;
        const filledStars = Math.round(rating || 0);
        let starsHtml = '';

        for (let i = 1; i <= maxStars; i++) {
            starsHtml += `<i class="fa fa-star${i <= filledStars ? ' checked' : '-o'}"></i>`;
        }

        return `<div class="text-warning fs-5">${starsHtml}</div>`;
    }

    // ---------- Search form (navbar) ----------
    const searchForm = document.getElementById('search-form');
    const searchInput = document.getElementById('search-bar');

    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const query = searchInput.value.trim();
        if (query) {
            window.location.href = `index.html?q=${encodeURIComponent(query)}`;
        }
    });

    // ---------- Movie details ----------

    // Render full movie details including:
    // - Poster, Rating, Oscars, Description, Genres, Countries, Languages, Themes, Studios, Cast, Crew, Releases
    function renderMovieDetails(movieDetails) {
        const {
            movie, genres, themes, countries, languages,
            studios, posters, actors, crew, oscars, releases
        } = movieDetails;

        movieDetailsContainer.innerHTML = `
        <div class="row">
            <div class="col-md-4 text-center">
                <img id="movie-poster" src="${esc(posters?.link || PLACEHOLDER_POSTER)}"
                     alt="Poster of ${esc(movie.name)}"
                     class="img-fluid rounded shadow mb-3"
                     style="max-height: 600px; object-fit: contain;" />

                <h5 class="mt-3">⭐ Rating</h5>
                <div class="mb-2">
                    ${generateStars(movie.rating)}
                    <span class="badge bg-primary fs-6">${typeof movie.rating === 'number' ? movie.rating.toFixed(1) + "/5" : "N/A"}</span>
                </div>

                <h5 class="mt-4">🏆 Oscars</h5>
                ${oscars?.length > 0 ? oscars.map(o => `
                    <div class="alert alert-warning p-2 mb-2">
                        <strong>${esc(o.category)}</strong><br>
                        ${esc(o.film)} (${esc(o.year_film)})<br>
                        Ceremony #${esc(o.ceremony)} – ${esc(o.year_ceremony)}<br>
                        ${o.winner ? "<span class='text-success fw-bold'>Winner</span>" : "Nominee"}
                    </div>
                `).join('') : '<p>No Oscars</p>'}
            </div>

            <div class="col-md-8">
                <h1 style="font-style: italic">${esc(movie.name || 'Unknown title')}</h1>
                <h5 class="mb-3">${esc(movie.tagline)}</h5>
                <p class="mb-4">${esc(movie.description || 'No plot available.')}</p>

                <ul class="list-group mb-4">
                    <li class="list-group-item"><strong>Release year:</strong> ${esc(movie.date || '-')}</li>
                    <li class="list-group-item"><strong>Duration:</strong> ${movie.minute ? esc(movie.minute) + ' min' : '-'}</li>
                    <li class="list-group-item"><strong>Genres:</strong> ${genres?.map(g => esc(g.genre)).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Themes:</strong> ${themes?.map(t => esc(t.theme)).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Countries:</strong> ${countries?.map(c => esc(c.country)).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Languages:</strong> ${languages?.map(l => esc(l.language)).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Studios:</strong> ${studios?.map(s => esc(s.studio)).join(', ') || '-'}</li>
                </ul>
                <div class="btn-group my-4" role="group">
                    <button type="button" class="btn btn-outline-primary" data-scroll="#cast-section" style="color: white">Cast</button>
                    <button type="button" class="btn btn-outline-primary" data-scroll="#crew-section" style="color: white">Crew</button>
                    <button type="button" class="btn btn-outline-primary" data-scroll="#releases-section" style="color: white">Releases</button>
                    <button type="button" class="btn btn-outline-primary" data-scroll="#reviews-section" style="color: white">Reviews</button>
                </div>
            </div>
        </div>

        <h3 id="cast-section" class="mt-5">🎭 Cast</h3>
        <div class="row">
            ${actors?.length > 0 ? actors.map(actor => `
                <div class="col-6 col-sm-4 col-md-3 col-lg-2 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body p-2">
                            <h6 class="card-title mb-1">${esc(actor.name)}</h6>
                            <small class="text-muted">${esc(actor.role || 'Actor')}</small>
                        </div>
                    </div>
                </div>
            `).join('') : '<p class="text-muted">No cast available.</p>'}
        </div>

        <h3 id="crew-section" class="mt-5">🎬 Crew</h3>
        <div class="row">
            ${crew?.length > 0 ? crew.map(member => `
                <div class="col-6 col-sm-4 col-md-3 col-lg-2 mb-4">
                    <div class="card h-100 text-center shadow-sm">
                        <div class="card-body p-2">
                            <h6 class="card-title mb-1">${esc(member.name)}</h6>
                            <small class="text-muted">${esc(member.role || 'Crew')}</small>
                        </div>
                    </div>
                </div>
            `).join('') : '<p class="text-muted">No crew available.</p>'}
        </div>

        <h3 id="releases-section" class="mt-5">🌍 International Releases</h3>
        <ul class="list-group">
            ${releases?.length > 0 ? releases.map(r => `
                <li class="list-group-item">
                    ${esc(r.country)} (${esc(r.type)}) – ${formatDate(r.date)}
                </li>
            `).join('') : '<li class="list-group-item text-muted">No release data available.</li>'}
        </ul>
    `;

        // poster fallback (only one attempt, to avoid loops)
        const posterEl = document.getElementById('movie-poster');
        if (posterEl) {
            posterEl.addEventListener('error', () => { posterEl.src = PLACEHOLDER_POSTER; }, { once: true });
        }
    }

    // Fetch full movie details from Spring Boot API
    async function loadMovieDetails(id) {
        showLoader();
        try {
            const response = await fetch(`${API_URL}/spring/movies/${encodeURIComponent(id)}`);
            if (!response.ok) throw new Error('Movie not found');
            const data = await response.json();

            renderMovieDetails(data);
            currentMovieTitle = data.movie.name;
            currentPageReviews = 0;
            loadReviews(currentMovieTitle, false);

            // scrolling section buttons
            document.querySelectorAll('[data-scroll]').forEach(btn => {
                btn.addEventListener('click', () => {
                    const target = document.querySelector(btn.getAttribute('data-scroll'));
                    if (target) target.scrollIntoView({ behavior: 'smooth', block: 'start' });
                });
            });
        } catch (error) {
            console.error('Error:', error);
            movieDetailsContainer.innerHTML = '';
            const p = document.createElement('p');
            p.className = 'text-danger';
            p.textContent = `Error: ${error.message}`;
            movieDetailsContainer.appendChild(p);
        } finally {
            hideLoader();
        }
    }

    // ---------- Reviews ----------

    /** Builds the card of a single review (textContent: the review text is never interpreted as HTML). */
    function renderReview(review) {
        const card = document.createElement('div');
        card.className = 'card mb-3 shadow-sm text-white';

        const body = document.createElement('div');
        body.className = 'card-body';

        const title = document.createElement('h6');
        title.className = 'card-title';
        title.appendChild(document.createTextNode(`${review.critic_name ?? 'Unknown critic'} `));
        if (review.top_critic) {
            const badge = document.createElement('span');
            badge.className = 'badge bg-warning text-dark';
            badge.textContent = 'Top Critic';
            title.appendChild(badge);
        }

        const publisher = document.createElement('h6');
        publisher.className = 'card-subtitle';
        publisher.textContent = review.publisher_name ?? '';

        const text = document.createElement('p');
        text.className = 'card-text';
        text.textContent = review.review_content ?? '';

        const meta = document.createElement('small');
        const day = review.review_date ? String(review.review_date).split('T')[0] : '-';
        meta.textContent = `${day} – ${review.review_type ?? '-'} – Score: ${review.review_score ?? 'N/A'}`;

        body.append(title, publisher, text, meta);
        card.appendChild(body);
        return card;
    }

    // Fetch reviews for the current movie title from the MongoDB API
    async function loadReviews(movieTitle, append = false) {
        if (!reviewsContainer) return;

        const params = new URLSearchParams();
        params.append('page', currentPageReviews);
        params.append('limit', limitReviews);

        // Apply filters
        const topCritic = document.getElementById('filter-top-critic')?.value;
        const reviewType = document.getElementById('filter-review-type')?.value;
        const sortDate = document.getElementById('filter-sort-date')?.value;

        if (topCritic) params.append('top_critic', topCritic);
        if (reviewType) params.append('review_type', reviewType);
        if (sortDate) params.append('sort_date', sortDate);

        try {
            const response = await fetch(`${API_URL}/mongo/reviews/movie/${encodeURIComponent(movieTitle)}?${params}`);
            if (!response.ok) throw new Error(`Server error (${response.status})`);
            const reviews = await response.json();

            if (!append) reviewsContainer.innerHTML = '';

            if (reviews.length === 0 && currentPageReviews === 0) {
                reviewsContainer.innerHTML = '<p>No reviews available for this movie.</p>';
                loadMoreReviewsBtn.style.display = 'none';
                return;
            }

            reviews.forEach(review => reviewsContainer.appendChild(renderReview(review)));
            loadMoreReviewsBtn.style.display = reviews.length < limitReviews ? 'none' : 'block';
        } catch (error) {
            console.error('Error loading reviews:', error);
            if (append) {
                currentPageReviews--;   // allows to retry
                alert('Unable to load more reviews. Please try again.');
            } else {
                reviewsContainer.innerHTML = '';
                const p = document.createElement('p');
                p.className = 'text-danger';
                p.textContent = 'Error loading reviews. Please try again later.';
                reviewsContainer.appendChild(p);
                loadMoreReviewsBtn.style.display = 'none';
            }
        }
    }

    // Event listeners for review filters (dropdowns)
    ['filter-top-critic', 'filter-review-type', 'filter-sort-date'].forEach(id => {
        const el = document.getElementById(id);
        if (el) {
            el.addEventListener('change', () => {
                currentPageReviews = 0;
                loadReviews(currentMovieTitle, false);
            });
        }
    });

    loadMoreReviewsBtn.addEventListener('click', () => {
        currentPageReviews++;
        loadReviews(currentMovieTitle, true);
    });

    // ---------- Start ----------
    if (!/^\d+$/.test(movieId || '')) {
        movieDetailsContainer.innerHTML = '<p class="text-danger">Error: invalid movie id.</p>';
        return;   // no details, no reviews, no chat
    }
    loadMovieDetails(movieId);

    // ---------- Socket.IO chat ----------
    const chatForm = document.getElementById('chat-form');
    const chatInput = document.getElementById('chat-input');
    const chatMessages = document.getElementById('chat-messages');

    /** A random "Guest-1234" nickname, kept for the whole browser session. */
    function getChatUsername() {
        try {
            let name = sessionStorage.getItem('chatUsername');
            if (!name) {
                name = 'Guest-' + Math.floor(1000 + Math.random() * 9000);
                sessionStorage.setItem('chatUsername', name);
            }
            return name;
        } catch (e) {
            return 'Guest';
        }
    }
    const chatUsername = getChatUsername();

    const socket = io(`${API_URL}/chat`, { transports: ['websocket'] });

    // Join the room named after the movie id (isolated chat per film).
    // On every (re)connection, because the server forgets the rooms of a disconnected socket.
    socket.on('connect', () => socket.emit('joinRoom', movieId));

    chatForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const text = chatInput.value.trim();
        if (!text) return;

        socket.emit('chatMessage', { movieId, username: chatUsername, message: text });
        chatInput.value = '';
    });

    socket.on('chatMessage', ({ username = 'Guest', message, timestamp }) => {
        const time = new Date(timestamp)
            .toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        const line = document.createElement('div');
        line.className = 'mb-2';
        const author = document.createElement('strong');
        author.textContent = `[${time}] ${username}: `;
        // textContent/text nodes: a message is never interpreted as HTML
        line.append(author, document.createTextNode(message));
        chatMessages.appendChild(line);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    });

    socket.on('connect_error', err => console.error('Socket.IO connection error:', err.message));
});