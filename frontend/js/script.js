let currentOffset = 0;
const limit = 12;

document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('movies-list');
    const loadMoreBtn = document.getElementById('load-more-btn');
    const searchInput = document.getElementById('search-bar');

    let currentPage = 0;
    function loadMovies() {
        fetch(`http://localhost:8080/movies/paginated?limit=${limit}&page=${currentPage}`)
            .then(response => response.json())
            .then(data => {
                data.forEach(item => {
                    const movie = item.movie;
                    const posters = item.posters;

                    const col = document.createElement('div');
                    col.className = 'col-12 col-sm-6 col-md-3 mt-3'; // 4 card per riga

                    col.innerHTML = `
                        <div class="card mb-4 h-100">
                            <img src="${posters}" class="card-img-top object-fit-contain" style="height: 300px" alt="Poster di ${movie.name}">
                            <div class="card-body">
                                <h6 class="card-title text-center">${movie.name}</h6>
                                <div class="card-text mt-3">Rating: ${movie.rating ?? 'N/A'}</div>
                                <small class="card-text">Release Date: ${movie.date}</small>
                            </div>
                        </div>
                    `;

                    container.appendChild(col);
                });
                //disable buttons
                loadMoreBtn.disabled = data.length < limit;
            })
            .catch(error => {
                console.error('Errore nel recupero dei film:', error);
            });
    }

    loadMoreBtn.addEventListener('click', () => {
        currentPage++;
        loadMovies();
    });

    loadMovies();

    if (searchInput) {
        searchInput.addEventListener('input', function () {
            const query = this.value.toLowerCase();
            const cards = document.querySelectorAll('.card');
            cards.forEach(card => {
                const title = card.querySelector('.card-title').textContent.toLowerCase();
                card.parentElement.style.display = title.includes(query) ? '' : 'none';
            });
        });

        searchInput.addEventListener('keydown', function (event) {
            if (event.key === 'Enter') {
                event.preventDefault();
                const query = searchInput.value.trim();
                if (query) {
                    window.location.href = `movies.html?query=${encodeURIComponent(query)}`;
                }
            }
        });
    }
});


document.getElementById('search-bar').addEventListener('input', function () {
    const query = this.value.toLowerCase();
    const cards = document.querySelectorAll('.card');

    cards.forEach(card => {
        const title = card.querySelector('.card-title').textContent.toLowerCase();
        if (title.includes(query)) {
            card.parentElement.style.display = '';
        } else {
            card.parentElement.style.display = 'none';
        }
    });
});
