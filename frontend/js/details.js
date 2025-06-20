document.addEventListener('DOMContentLoaded', () => {

    const API_URL = window.API_URL || "http://localhost:3000";

    const movieDetailsContainer = document.getElementById('movie-details');
    const loader = document.getElementById('loader');
    const movieId = new URLSearchParams(window.location.search).get('id');
    let currentMovieTitle = '';

    ['filter-top-critic', 'filter-review-type', 'filter-sort-date'].forEach(id => {
        const el = document.getElementById(id);
        if (el) {
            el.addEventListener('change', () => {
                currentPageReviews = 0;
                loadReviews(currentMovieTitle, false);
            });
        }
    });



    function showLoader() {
        if (loader) loader.style.display = 'block';
    }
    function hideLoader() {
        if (loader) loader.style.display = 'none';
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


    function renderMovieDetails(movieDetails) {
        const {
            movie, genres, themes, countries, languages,
            studios, posters, actors, crew, oscars, releases
        } = movieDetails;

        movieDetailsContainer.innerHTML = `
        <div class="row">
            <div class="col-md-4 text-center">
                <img src="${posters?.link}"
                     alt="Poster of ${movie.name}"
                     class="img-fluid rounded shadow mb-3"
                     style="max-height: 600px; object-fit: contain;" />

                <h5 class="mt-3">⭐ Rating</h5>
                <div class="mb-2">
                    ${generateStars(movie.rating)}
                    <span class="badge bg-primary fs-6">${movie.rating ? movie.rating.toFixed(1) + "/5" : "N/A"}</span>
                </div>


                <h5 class="mt-4">🏆 Oscars</h5>
                ${oscars?.length > 0 ? oscars.map(o => `
                    <div class="alert alert-warning p-2 mb-2">
                        <strong>${o.category}</strong><br>
                        ${o.film} (${o.year_film})<br>
                        Ceremony #${o.ceremony} – ${o.year_ceremony}<br>
                        ${o.winner ? "<span class='text-success fw-bold'>Winner</span>" : "Nominee"}
                    </div>
                `).join('') : '<p>No Oscars</p>'}
            </div>

            <div class="col-md-8">
                <h1 style="font-style: italic">${movie.name || 'Unknown title'}</h1>
                <h5 class="mb-3">${movie.tagline || ''}</h5>
                <p class="mb-4">${movie.description || 'No plot available.'}</p>

                <ul class="list-group mb-4">
                    <li class="list-group-item"><strong>Release year:</strong> ${movie.date || '-'}</li>
                    <li class="list-group-item"><strong>Duration:</strong> ${movie.minute || '-'} min</li>
                    <li class="list-group-item"><strong>Genres:</strong> ${genres?.map(g => g.genre).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Themes:</strong> ${themes?.map(t => t.theme).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Countries:</strong> ${countries?.map(c => c.country).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Languages:</strong> ${languages?.map(l => l.language).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Studios:</strong> ${studios?.map(s => s.studio).join(', ') || '-'}</li>
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
                            <h6 class="card-title mb-1">${actor.name}</h6>
                            <small class="text-muted">${actor.role || 'Actor'}</small>
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
                            <h6 class="card-title mb-1">${member.name}</h6>
                            <small class="text-muted">${member.role || 'Crew'}</small>
                        </div>
                    </div>
                </div>
            `).join('') : '<p class="text-muted">No crew available.</p>'}
        </div>

        <h3 id="releases-section" class="mt-5">🌍 International Releases</h3>
        <ul class="list-group">
            ${releases?.length > 0 ? releases.map(r => `
                <li class="list-group-item">
                    ${r.country} (${r.type}) – ${new Date(r.date).toLocaleDateString()}
                </li>
            `).join('') : '<li class="list-group-item text-muted">No release data available.</li>'}
        </ul>
    `;
    }


    function loadMovieDetails(movieId) {
        showLoader();
        const url = `${API_URL}/spring/movies/${movieId}`;

        fetch(url)
            .then(response => {
                if (!response.ok) throw new Error('Movie not found');
                return response.json();
            })
            .then(data => {
                console.log('Movie details received:', data);
                renderMovieDetails(data);
                currentMovieTitle = data.movie.name;
                currentPageReviews = 0;
                loadReviews(currentMovieTitle, false);


                //scrolling section buttons
                document.querySelectorAll('[data-scroll]').forEach(btn => {
                    btn.addEventListener('click', () => {
                        const target = document.querySelector(btn.getAttribute('data-scroll'));
                        if (target) target.scrollIntoView({ behavior: 'smooth', block: 'start' });
                    });
                });
            })
            .catch(error => {
                console.error('Error:', error);
                movieDetailsContainer.innerHTML = `<p class="text-danger">Error: ${error.message}</p>`;
            })
            .finally(() => hideLoader());
    }
    loadMovieDetails(movieId);


    let currentPageReviews = 0;
    const limitReviews = 5;

    document.getElementById('load-more-reviews').addEventListener('click', () => {
        currentPageReviews++;
        loadReviews(currentMovieTitle, true);
    });

    function loadReviews(movieTitle, append = false){
        const reviewsContainer = document.getElementById('movie-reviews');
        if(!reviewsContainer) return;

        const encodedTitle = encodeURIComponent(movieTitle);
        const params = new URLSearchParams();
        params.append('page', currentPageReviews);
        params.append('limit', limitReviews);

        const topCritic = document.getElementById('filter-top-critic')?.value;
        const reviewType = document.getElementById('filter-review-type')?.value;
        const sortDate = document.getElementById('filter-sort-date')?.value;

        if (topCritic) params.append('top_critic', topCritic);
        if (reviewType) params.append('review_type', reviewType);
        if (sortDate) params.append('sort_date', sortDate);

        fetch(`${API_URL}/mongo/reviews/movie/${encodedTitle}?${params.toString()}`)
            .then(response => response.json())
            .then(reviews => {
                if(!append) reviewsContainer.innerHTML = '';

                if(!reviews.length && currentPageReviews === 0){
                    reviewsContainer.innerHTML = '<p>No reviews available for this movie.</p>';
                    document.getElementById('load-more-reviews').style.display = 'none';
                    return;
                }
                reviews.forEach(review => {
                    const reviewCard = document.createElement('div');
                    reviewCard.className = 'card mb-3 shadow-sm text-white';
                    reviewCard.innerHTML = `
                    <div class="card-body">
                        <h6 class="card-title">${review.critic_name} ${review.top_critic ? '<span class="badge bg-warning text-dark">Top Critic</span>' : ''}</h6>
                        <h6 class="card-subtitle">${review.publisher_name}</h6>
                        <p class="card-text">${review.review_content}</p>
                        <small>${review.review_date?.split('T')[0]} – ${review.review_type} – Score: ${review.review_score}</small>
                    </div>
                `;
                    reviewsContainer.appendChild(reviewCard);
                });
                if (reviews.length < limitReviews) {
                    document.getElementById('load-more-reviews').style.display = 'none';
                } else {
                    document.getElementById('load-more-reviews').style.display = 'block';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                reviewsContainer.innerHTML = `<p class="text-danger">Error loading reviews: ${error.message}</p>`;
            })
    }

    const searchForm = document.getElementById('search-form');
    const searchInput = document.getElementById('search-bar');

    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const query = searchInput.value.trim();
        if (query) {
            window.location.href = `index.html?q=${encodeURIComponent(query)}`;
        }
    });


});