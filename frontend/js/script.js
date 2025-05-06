document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('movies-list');
    const searchInput = document.getElementById('search-bar');

    fetch('http://localhost:8080/movies/details/top')
        .then(response => response.json())
        .then(data => {
            data.forEach(item => {
                const movie = item.movie;
                const poster = item.poster;

                const col = document.createElement('div');
                col.className = 'col-md-2'; // 5 card per riga

                col.innerHTML = `
                    <div class="card mb-4 h-100">
                        <img src="${poster}" class="card-img-top object-fit-contain" style="height: 300px" alt="Poster di ${movie.name}">
                        <div class="card-body d-flex flex-column justify-content-between">
                            <h6 class="card-title">${movie.name}</h6>
                            <p class="card-text">Rating: ${movie.rating ?? 'N/A'}</p>
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
