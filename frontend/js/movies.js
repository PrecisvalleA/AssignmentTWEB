document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('movies-list');
    const urlParams = new URLSearchParams(window.location.search);
    const query = urlParams.get('query');

    if (!query) {
        container.innerHTML = '<p class="text-muted">Nessuna ricerca specificata.</p>';
        return;
    }

    fetch(`http://localhost:8080/movies/search/${encodeURIComponent(query)}`)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                container.innerHTML = '<p class="text-danger">Nessun film trovato.</p>';
                return;
            }

            data.forEach(movie => {
                fetch(`http://localhost:8080/movies/details/${movie.id_movie}`)
                    .then(resp => resp.json())
                    .then(details => {
                        const col = document.createElement('div');
                        col.className = 'col-12 col-sm-6 col-md-3 mt-3';

                        col.innerHTML = `
                          <div class="card mb-4 h-100">
                            <img src="${details.posters}" class="card-img-top object-fit-contain" alt="Poster di ${details.movie.name}">
                            <div class="card-body">
                              <h6 class="card-title text-center">${details.movie.name}</h6>
                              <div class="card-text mt-3">Rating: ${details.movie.rating ?? 'N/A'}</div>
                              <small class="card-text">Anno: ${details.movie.date}</small>
                            </div>
                          </div>
                        `;

                        container.appendChild(col);
                    });
            });
        })
        .catch(error => {
            container.innerHTML = '<p class="text-danger">Errore durante la ricerca.</p>';
            console.error('Errore:', error);
        });
});
