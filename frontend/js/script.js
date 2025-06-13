document.addEventListener('DOMContentLoaded', () => {
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

    ratingStars.forEach(star => {
        star.addEventListener('click', () => {
            const clickedValue = parseInt(star.getAttribute('data-value'));
            if (selectedRating === clickedValue) {
                selectedRating = null;
                updateStars(0);
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


    [minDateFilter, maxDateFilter, sortFilter, genreFilter].forEach(filterEl => {
        filterEl.addEventListener('change', () => {
            currentPage = 0;
            loadHomePage();
        });
    });



    const API_URL = window.API_BASE_URL || "http://localhost:8080";

    let currentPage = 0;
    const limit = 12;

    //creating card function
    function renderCard(movie, posterUrl, genre) {
        const col = document.createElement('div');
        col.className = 'col-12 col-sm-6 col-md-3 mt-3'
        col.innerHTML = `
        <div class="card mb-4 h-100">
          <img src="${posterUrl}" class="card-img-top object-fit-contain" style="height: 300px" alt="Poster di ${movie.name}">
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


    function showLoader() {
        document.getElementById('loader').style.display = 'block';
    }
    function hideLoader() {
        document.getElementById('loader').style.display = 'none';
    }

    //HomePage function
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

        const url = `${API_URL}/movies/paginated?` + params.toString();


        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log('Risposta API:', data);
                data.content.forEach(({ movie, posterUrl, genres }) => {
                    renderCard(movie, posterUrl, genres);
                });
                //disable buttons
                loadMoreBtn.disabled = data.content.length < limit;
            })
            .catch(error => {
                console.error('Errore nel recupero dei film:', error);
            })
            .finally(() => hideLoader());
    }

    //Search function
    function loadSearchPage(query) {
        showLoader();
        sectionTitle.textContent = `Risultati per “${query}”`;
        loadMoreBtn.style.display = 'none';
        container.innerHTML = '';

        const params = new URLSearchParams();
        params.append('page', 0);
        params.append('size', limit);

        fetch(`${API_URL}/movies/search/${encodeURIComponent(query)}?` + params.toString())
            .then(response => response.json())
            .then(data=> {
                const items = data.content || [];
                if (items.length === 0) {
                    container.innerHTML = '<p class="text-center text-danger">Nessun film trovato.</p>';
                    return;
                }
                items.forEach(({ movie, posterUrl, genre}) => {
                    fetch(`${API_URL}/movies/details/${movie.id}`)
                        .then(response => response.json())
                        .then(details => {
                            renderCard(details.movie, posterUrl, genre);
                        })
                        .catch(error => {
                            console.error('Errore nel recupero dei film:', error);
                            renderCard(movie, 'defaultPoster.jpg');
                        });
                });
            })
            .catch(error => {
                console.error('Errore nel recupero dei film:', error);
                container.innerHTML = '<p class="text-center text-danger">Errore nella ricerca.</p>';
            })
            .finally(() => hideLoader());
    }

    //click 'Altro'
    loadMoreBtn.addEventListener('click', () => {
        currentPage++;
        loadHomePage();
    });

    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const q = searchInput.value.trim();
        if (q) {
            loadSearchPage(q);
        } else {
            // if the search is empty, return to the homepage
            currentPage = 0;
            loadHomePage();
        }
    });

    loadHomePage();
});