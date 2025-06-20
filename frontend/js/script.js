/**
 * FrameVerse Frontend Client Logic
 *
 * This script handles:
 * - Fetching movie data via API Gateway
 * - Applying filters and sorting
 * - Handling search queries
 * - Rendering movies as Bootstrap cards
 */
document.addEventListener('DOMContentLoaded', () => {
    const API_URL = window.API_BASE_URL || "http://localhost:3000";
    // DOM Elements
    const container = document.getElementById('movies-list');
    const loadMoreBtn = document.getElementById('load-more-btn');
    const searchInput = document.getElementById('search-bar');
    const searchForm = document.getElementById('search-form');
    const sectionTitle = document.getElementById('section-title');
    const minDateFilter   = document.getElementById('min-date-filter');
    const maxDateFilter   = document.getElementById('max-date-filter');
    const sortFilter   = document.getElementById('sort-filter');
    const genreFilter = document.getElementById('genre-filter');

    let selectedRating = null;
    const ratingStars = document.querySelectorAll('#rating-stars .fa-star');


    let currentPage = 0;
    let searchPage = 0;
    let currentSearchQuery = '';
    const limit = 12;

    // Handle search query from URL params
    const urlParams = new URLSearchParams(window.location.search);
    const searchQuery = urlParams.get('q');
    if (searchQuery) {
        searchInput.value = searchQuery;
        loadSearchPage(searchQuery);
    } else {
        loadHomePage();
    }


    // Rating stars filtering logic
    ratingStars.forEach(star => {
        star.addEventListener('click', () => {
            const clickedValue = parseInt(star.getAttribute('data-value'));
            if (selectedRating === clickedValue) {
                selectedRating = null;
                updateStars(-1);
            } else {
                selectedRating = clickedValue;
                updateStars(clickedValue);
            }
            currentPage = 0;
            loadHomePage();
        });
    });

    function updateStars(rating) {
        ratingStars.forEach(star => {
            const val = parseInt(star.getAttribute('data-value'));
            star.classList.toggle('checked', val <= rating);
        });
    }


    // Filters (year range, genre, sort) event listeners
    [minDateFilter, maxDateFilter, sortFilter, genreFilter].forEach(filterEl => {
        filterEl.addEventListener('change', () => {
            currentPage = 0;
            loadHomePage();
        });
    });


    // Render single movie card
    function renderCard(movie, posterUrl, genre) {
        const col = document.createElement('div');
        col.setAttribute('data-id', movie.id);
        col.className = 'col-12 col-sm-6 col-md-3 mt-3'

        col.addEventListener('click', () => {
            const movieId = col.getAttribute('data-id');
            if (movieId) {
                console.log(`Navigating to movie details page with ID: ${movieId}`);
                window.location.href = `details.html?id=${movieId}`;
            } else {
                console.error('Movie ID not found!');
                alert('Error: Movie ID not found!.');
            }
        });


        col.innerHTML = `
        <div class="card mb-4 h-100">
          <img src="${posterUrl}" class="card-img-top object-fit-contain" style="height: 300px" alt="Poster of ${movie.name}">
            <div class="card-body">
             <h6 class="card-title text-center">${movie.name}</h6>
              <div class="card-text mt-3">Rating: ${movie.rating ?? 'N/A'}</div>
             <small class="card-text">Release Date: ${movie.date}</small>
              <div class="card-text mt-3">Genres: ${genre ?? 'N/A'}</div>
              </div>
        </div>
        `;
        container.appendChild(col);
    }

    // Show and hide loader functions
    function showLoader() {
        document.getElementById('loader').style.display = 'block';
    }
    function hideLoader() {
        document.getElementById('loader').style.display = 'none';
    }

    // Load movies for homepage (with filters)
    function loadHomePage() {
        showLoader();
        const params = new URLSearchParams();
        params.append('size', limit);
        params.append('page', currentPage);

        if (selectedRating) params.append('minRating', selectedRating);
        if (minDateFilter.value) params.append('minDate', minDateFilter.value);
        if (maxDateFilter.value) params.append('maxDate', maxDateFilter.value);
        if (sortFilter.value) params.append('sort', sortFilter.value);
        if (genreFilter.value) params.append('genre', genreFilter.value);

        sectionTitle.textContent = 'Top Rated';
        loadMoreBtn.style.display = '';
        if (currentPage === 0) container.innerHTML = '';

        const url = `${API_URL}/spring/movies/paginated?` + params.toString();


        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log('Response API:', data);
                data.content.forEach(({ movie, posterUrl, genres }) => {
                    console.log('Film received:', movie);
                    renderCard(movie, posterUrl, genres);
                });
                //disable buttons
                loadMoreBtn.disabled = data.content.length < limit;
            })
            .catch(error => {
                console.error('Error: movies not found', error);
            })
            .finally(() => hideLoader());
    }

    // Load search results page
    function loadSearchPage(query) {
        showLoader();
        sectionTitle.textContent = `Response for “${query}”`;

        const params = new URLSearchParams();
        params.append('page', searchPage);
        params.append('size', limit);

        if (searchPage === 0) container.innerHTML = '';

        fetch(`${API_URL}/spring/movies/search/${encodeURIComponent(query)}?` + params.toString())
            .then(response => response.json())
            .then(data=> {
                const items = data.content || [];
                if (items.length === 0) {
                    container.innerHTML = '<p class="text-center text-danger">Movies not found.</p>';
                    loadMoreBtn.style.display = 'none';
                    return;
                }
                items.forEach(({ movie }) => {
                    fetch(`${API_URL}/spring/movies/${movie.id}`)
                        .then(response => response.json())
                        .then(details => {
                            const posterUrl = details.posters?.link ?? 'default.jpg';
                            const genres = details.genres?.map(g => g.genre).join(', ') ?? 'N/A';
                            renderCard(details.movie, posterUrl, genres);
                        })
                        .catch(error => {
                            console.error('Movie details error:', error);
                            renderCard(movie, 'default.jpg', 'N/A');
                        });
                    loadMoreBtn.style.display = '';
                    loadMoreBtn.disabled = items.length < limit;
                })
            })
            .catch(error => {
                console.error('Error: movies not found', error);
                container.innerHTML = '<p class="text-center text-danger">Search error!.</p>';
            })
            .finally(() => hideLoader());
    }

    // Load more button handler
    loadMoreBtn.addEventListener('click', () => {
        if (currentSearchQuery) {
            searchPage++;
            loadSearchPage(currentSearchQuery);
        } else {
            currentPage++;
            loadHomePage();
        }
    });

    // Handle search form submit
    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const q = searchInput.value.trim();
        currentPage = 0;
        searchPage = 0;

        if (q) {
            currentSearchQuery = q;
            loadSearchPage(currentSearchQuery);
        } else {
            // if the search is empty, return to the homepage
            currentSearchQuery = '';
            loadHomePage();
        }
    });
});