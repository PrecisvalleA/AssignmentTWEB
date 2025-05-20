document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('movies-list');
    const loadMoreBtn = document.getElementById('load-more-btn');
    const searchInput = document.getElementById('search-bar');
    const searchForm = document.getElementById('search-form');
    const sectionTitle = document.getElementById('section-title');


    let currentPage = 0;
    const limit = 12;

    //creating card function
    function renderCard(movie, posterUrl) {
        const col = document.createElement('div');
        col.className = 'col-12 col-sm-6 col-md-3 mt-3'
        col.innerHTML = `
        <div class="card mb-4 h-100">
          <img src="${posterUrl}" class="card-img-top object-fit-contain" style="height: 300px" alt="Poster di ${movie.name}">
            <div class="card-body">
             <h6 class="card-title text-center">${movie.name}</h6>
              <div class="card-text mt-3">Rating: ${movie.rating ?? 'N/A'}</div>
             <small class="card-text">Release Date: ${movie.date}</small>
              </div>
        </div>
        `;
        container.appendChild(col);
    }

    //HomePage function
    function loadHomePage() {

        sectionTitle.textContent = 'Top Rated';
        loadMoreBtn.style.display = '';
        if (currentPage === 0) container.innerHTML = '';

        fetch(`http://localhost:8080/movies/paginated?limit=${limit}&page=${currentPage}`)
            .then(response => response.json())
            .then(data => {
                data.forEach(item => renderCard(item.movie, item.posters));
                //disable buttons
                loadMoreBtn.disabled = data.length < limit;
            })
            .catch(error => {
                console.error('Errore nel recupero dei film:', error);
            });
    }

    //Search function
    function loadSearchPage(query) {
        sectionTitle.textContent = `Risultati per “${query}”`;
        loadMoreBtn.style.display = 'none';
        container.innerHTML = '';

        fetch(`http://localhost:8080/movies/search/${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(movies => {
                if (!movies.length) {
                    container.innerHTML = '<p class="text-center text-danger">Nessun film trovato.</p>';
                    return;
                }
                movies.forEach(movie => {
                    fetch(`http://localhost:8080/movies/details/${movie.id_movie}`)
                        .then(response => response.json())
                        .then(details => {
                            renderCard(details.movie, details.posters || 'defaultPoster.jpg');
                        })
                        .catch(error => {
                            console.error('Errore nel recupero dei film:', error);
                            renderCard(movie, 'defaultPoster.jpg');
                        })
                })
            })
            .catch(error => {
                console.error('Errore nel recupero dei film:', error);
                container.innerHTML = '<p class="text-center text-danger">Errore nella ricerca.</p>';
            })
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