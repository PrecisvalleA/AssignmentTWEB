/**
 * FrameVerse Frontend Client Logic
 *
 * This script handles:
 * - Fetching movie data via the API Gateway
 * - Applying filters and sorting
 * - Handling search queries
 * - Rendering movies as Bootstrap cards
 */
document.addEventListener('DOMContentLoaded', () => {
    const API_URL = window.API_URL || "http://localhost:3000";

    // DOM Elements
    const container = document.getElementById('movies-list');
    const loadMoreBtn = document.getElementById('load-more-btn');
    const searchInput = document.getElementById('search-bar');
    const searchForm = document.getElementById('search-form');
    const sectionTitle = document.getElementById('section-title');
    const minDateFilter = document.getElementById('min-date-filter');
    const maxDateFilter = document.getElementById('max-date-filter');
    const sortFilter = document.getElementById('sort-filter');
    const genreFilter = document.getElementById('genre-filter');
    const ratingStars = document.querySelectorAll('#rating-stars .fa-star');

    const limit = 12;
    let selectedRating = null;
    let currentPage = 0;
    let searchPage = 0;
    let currentSearchQuery = '';

    // Shown when a poster is missing or cannot be loaded
    const PLACEHOLDER_POSTER = 'data:image/svg+xml;utf8,' + encodeURIComponent(
        '<svg xmlns="http://www.w3.org/2000/svg" width="200" height="300">' +
        '<rect width="100%" height="100%" fill="#222"/>' +
        '<text x="50%" y="50%" fill="#999" font-size="18" text-anchor="middle" ' +
        'font-family="sans-serif">No poster</text></svg>');

    // ---------- Helpers ----------

    function showLoader() {
        document.getElementById('loader').style.display = 'block';
    }

    function hideLoader() {
        document.getElementById('loader').style.display = 'none';
    }

    /** GET request that returns the parsed JSON or throws if the status is not 2xx. */
    async function getJson(url) {
        const response = await fetch(url);
        if (!response.ok) throw new Error(`Server error (${response.status})`);
        return response.json();
    }

    /** Replaces the movie list with a message and hides the "Next" button. */
    function showMessage(text) {
        container.innerHTML = '';
        const p = document.createElement('p');
        p.className = 'text-center text-danger';
        p.textContent = text;
        container.appendChild(p);
        loadMoreBtn.style.display = 'none';
    }

    /** Renders a single movie card (built with the DOM API: no HTML injection). */
    function renderCard(movie, posterUrl, genres) {
        const col = document.createElement('div');
        col.className = 'col-12 col-sm-6 col-md-3 mt-3';
        col.addEventListener('click', () => {
            window.location.href = `details.html?id=${encodeURIComponent(movie.id)}`;
        });

        const card = document.createElement('div');
        card.className = 'card mb-4 h-100';

        const img = document.createElement('img');
        img.className = 'card-img-top object-fit-contain';
        img.style.height = '300px';
        img.alt = `Poster of ${movie.name}`;
        img.addEventListener('error', () => { img.src = PLACEHOLDER_POSTER; }, { once: true });
        img.src = posterUrl || PLACEHOLDER_POSTER;

        const body = document.createElement('div');
        body.className = 'card-body';

        const title = document.createElement('h6');
        title.className = 'card-title text-center';
        title.textContent = movie.name;

        const rating = document.createElement('div');
        rating.className = 'card-text mt-3';
        rating.textContent = `Rating: ${movie.rating ?? 'N/A'}`;

        const date = document.createElement('small');
        date.className = 'card-text';
        date.textContent = `Release Date: ${movie.date ?? 'N/A'}`;

        const genresEl = document.createElement('div');
        genresEl.className = 'card-text mt-3';
        genresEl.textContent = `Genres: ${genres || 'N/A'}`;

        body.append(title, rating, date, genresEl);
        card.append(img, body);
        col.appendChild(card);
        container.appendChild(col);
    }

    function updateStars(rating) {
        ratingStars.forEach(star => {
            const val = parseInt(star.getAttribute('data-value'), 10);
            star.classList.toggle('checked', val <= rating);
        });
    }

    /** Goes back to the homepage list (first page) with the current filters. */
    function reloadHome() {
        currentSearchQuery = '';
        currentPage = 0;
        loadHomePage();
    }

    // ---------- Data loading ----------

    // Load movies for homepage (with filters)
    async function loadHomePage() {
        showLoader();
        const params = new URLSearchParams();
        params.append('size', limit);
        params.append('page', currentPage);

        if (selectedRating) params.append('minRating', selectedRating);
        if (/^\d{4}$/.test(minDateFilter.value)) params.append('minDate', minDateFilter.value);
        if (/^\d{4}$/.test(maxDateFilter.value)) params.append('maxDate', maxDateFilter.value);
        params.append('sort', sortFilter.value || 'rating,desc');
        if (genreFilter.value) params.append('genre', genreFilter.value);

        sectionTitle.textContent = 'Top Rated';
        loadMoreBtn.style.display = '';
        if (currentPage === 0) container.innerHTML = '';

        try {
            const data = await getJson(`${API_URL}/spring/movies/paginated?${params}`);
            if (data.content.length === 0 && currentPage === 0) {
                showMessage('No movies found.');
                return;
            }
            data.content.forEach(({ movie, posterUrl, genres }) => renderCard(movie, posterUrl, genres));
            loadMoreBtn.disabled = data.last;
        } catch (error) {
            console.error('Error loading movies:', error);
            if (currentPage === 0) {
                showMessage('Unable to load movies. Please try again later.');
            } else {
                currentPage--;   // allows to retry
                alert('Unable to load more movies. Please try again.');
            }
        } finally {
            hideLoader();
        }
    }

    // Load search results page
    async function loadSearchPage(query) {
        showLoader();
        sectionTitle.textContent = `Response for “${query}”`;

        const params = new URLSearchParams();
        params.append('page', searchPage);
        params.append('size', limit);

        if (searchPage === 0) container.innerHTML = '';

        try {
            const data = await getJson(`${API_URL}/spring/movies/search/${encodeURIComponent(query)}?${params}`);
            const items = data.content || [];
            if (items.length === 0 && searchPage === 0) {
                showMessage('Movies not found.');
                return;
            }
            // each item is an array: [movie, posterUrl, genres]
            items.forEach(([movie, posterUrl, genres]) => renderCard(movie, posterUrl, genres));
            loadMoreBtn.style.display = '';
            loadMoreBtn.disabled = data.last;
        } catch (error) {
            console.error('Search error:', error);
            if (searchPage === 0) {
                showMessage('Search error. Please try again later.');
            } else {
                searchPage--;   // allows to retry
                alert('Unable to load more results. Please try again.');
            }
        } finally {
            hideLoader();
        }
    }

    // ---------- Event listeners ----------

    // Rating stars (values 1..5): click again on the selected star to remove the filter
    ratingStars.forEach(star => {
        star.addEventListener('click', () => {
            const clickedValue = parseInt(star.getAttribute('data-value'), 10);
            if (selectedRating === clickedValue) {
                selectedRating = null;
                updateStars(0);
            } else {
                selectedRating = clickedValue;
                updateStars(clickedValue);
            }
            reloadHome();
        });
    });

    // Filters (year range, genre, sort)
    [minDateFilter, maxDateFilter, sortFilter, genreFilter].forEach(filterEl => {
        filterEl.addEventListener('change', reloadHome);
    });

    // "Next" button
    loadMoreBtn.addEventListener('click', () => {
        if (currentSearchQuery) {
            searchPage++;
            loadSearchPage(currentSearchQuery);
        } else {
            currentPage++;
            loadHomePage();
        }
    });

    // Search form
    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const q = searchInput.value.trim();
        if (q) {
            currentSearchQuery = q;
            searchPage = 0;
            loadSearchPage(q);
        } else {
            reloadHome();   // empty search: back to the homepage
        }
    });

    // ---------- First load ----------
    const searchQuery = (new URLSearchParams(window.location.search).get('q') || '').trim();
    if (searchQuery) {
        searchInput.value = searchQuery;
        currentSearchQuery = searchQuery;   // needed so that "Next" loads the next page of the search
        loadSearchPage(searchQuery);
    } else {
        loadHomePage();
    }
});