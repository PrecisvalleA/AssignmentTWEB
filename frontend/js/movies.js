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
                        col.className = 'col-md-4';

                        col.innerHTML = `
                          <div class="card mb-4 h-100">
                            <img src="${details.poster}" class="card-img-top" alt="Poster di ${details.movie.name}">
                            <div class="card-body">
                              <h5 class="card-title">${details.movie.name}</h5>
                              <p class="card-text">Rating: ${details.movie.rating ?? 'N/A'}</p>
                              <p class="card-text">Anno: ${details.movie.date}</p>
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
