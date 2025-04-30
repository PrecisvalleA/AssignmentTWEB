document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('movies-list');

    fetch('http://localhost:8080/movies/search/Avengers')
        .then(response => response.json())
        .then(movies => {
            movies.forEach(movie => {
                const col = document.createElement('div');
                col.className = 'col-md-4';
                col.innerHTML = `
          <div class="card mb-4">
            <img src="${movie.poster}" class="card-img-top" alt="Poster di ${movie.name}">
            <div class="card-body">
              <h5 class="card-title">${movie.name}</h5>
              <p class="card-text">Rating: ${movie.rating}</p>
              <p class="card-text">Release Date: ${movie.date}</p>
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
