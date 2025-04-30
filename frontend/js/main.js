document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8080/movies/search/Avengers')
        .then(response => response.json())
        .then(movies => {
            const container = document.getElementById('movies-list');
            movies.forEach(movie => {
                const col = document.createElement('div');
                col.className = 'col-md-4';

                col.innerHTML = `
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="card-title">${movie.name}</h5>
              <p class="card-text">${movie.tagline}</p>
              <a href="movie.html?title=${encodeURIComponent(movie.name)}" class="btn btn-primary">Dettagli</a>
            </div>
          </div>
        `;
                container.appendChild(col);
            });
        })
        .catch(error => {
            console.error('Errore nel recupero dei film:', error);
        });
});
