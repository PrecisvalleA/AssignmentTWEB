document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('movies-list');

    fetch('http://localhost:8080/movies/details/top')
        .then(response => response.json())
        .then(data => {
            data.forEach(item => {
                const movie = item.movie;
                const poster = item.poster;

                const col = document.createElement('div');
                col.className = 'col-md-4';

                col.innerHTML = `
                    <div class="card mb-4">
                        <img src="${poster}" class="card-img-top" alt="Poster di ${movie.name}">
                        <div class="card-body">
                            <h6 class="card-title">${movie.name}</h6>
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
