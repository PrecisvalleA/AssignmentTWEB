document.addEventListener('DOMContentLoaded', () => {

    const API_URL = window.API_URL || "http://localhost:8080";

    const movieDetailsContainer = document.getElementById('movie-details');
    const loader = document.getElementById('loader');
    const movieId = new URLSearchParams(window.location.search).get('id');

    function showLoader() {
        if (loader) loader.style.display = 'block';
    }
    function hideLoader() {
        if (loader) loader.style.display = 'none';
    }

    function generateStars(rating) {
        const maxStars = 5;
        const filledStars = Math.round(rating || 0);
        let starsHtml = '';

        for (let i = 1; i <= maxStars; i++) {
            starsHtml += `<i class="fa fa-star${i <= filledStars ? ' checked' : '-o'}"></i>`;
        }

        return `<div class="text-warning fs-5">${starsHtml}</div>`;
    }


    function renderMovieDetails(movieDetails) {
        const {
            movie, genres, themes, countries, languages,
            studios, posters, actors, crew, oscars, releases
        } = movieDetails;

        movieDetailsContainer.innerHTML = `
        <div class="row">
            <div class="col-md-4 text-center">
                <img src="${posters?.link}"
                     alt="Poster of ${movie.name}"
                     class="img-fluid rounded shadow mb-3"
                     style="max-height: 600px; object-fit: contain;" />

                <h5 class="mt-3">⭐ Rating</h5>
                <div class="mb-2">
                    ${generateStars(movie.rating)}
                    <span class="badge bg-primary fs-6">${movie.rating ? movie.rating.toFixed(1) + "/5" : "N/A"}</span>
                </div>


                <h5 class="mt-4">🏆 Oscars</h5>
                ${oscars?.length > 0 ? oscars.map(o => `
                    <div class="alert alert-warning p-2 mb-2">
                        <strong>${o.category}</strong><br>
                        ${o.film} (${o.year_film})<br>
                        Ceremony #${o.ceremony} – ${o.year_ceremony}<br>
                        ${o.winner ? "<span class='text-success fw-bold'>Winner</span>" : "Nominee"}
                    </div>
                `).join('') : '<p>No Oscars</p>'}
            </div>

            <div class="col-md-8">
                <h1 style="font-style: italic">${movie.name || 'Unknown title'}</h1>
                <h5 class="mb-3">${movie.tagline || ''}</h5>
                <p class="mb-4">${movie.description || 'No plot available.'}</p>

                <ul class="list-group mb-4">
                    <li class="list-group-item"><strong>Release year:</strong> ${movie.date || '-'}</li>
                    <li class="list-group-item"><strong>Duration:</strong> ${movie.minute || '-'} min</li>
                    <li class="list-group-item"><strong>Genres:</strong> ${genres?.map(g => g.genre).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Themes:</strong> ${themes?.map(t => t.theme).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Countries:</strong> ${countries?.map(c => c.country).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Languages:</strong> ${languages?.map(l => l.language).join(', ') || '-'}</li>
                    <li class="list-group-item"><strong>Studios:</strong> ${studios?.map(s => s.studio).join(', ') || '-'}</li>
                </ul>
            </div>
        </div>

        <h3 class="mt-5">🎭 Cast</h3>
        <div class="row">
            ${actors?.length > 0 ? actors.map(actor => `
                <div class="col-6 col-sm-4 col-md-3 col-lg-2 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body p-2">
                            <h6 class="card-title mb-1">${actor.name}</h6>
                            <small class="text-muted">${actor.role || 'Actor'}</small>
                        </div>
                    </div>
                </div>
            `).join('') : '<p class="text-muted">No cast available.</p>'}
        </div>

        <h3 class="mt-5">🎬 Crew</h3>
        <div class="row">
            ${crew?.length > 0 ? crew.map(member => `
                <div class="col-6 col-sm-4 col-md-3 col-lg-2 mb-4">
                    <div class="card h-100 text-center shadow-sm">
                        <div class="card-body p-2">
                            <h6 class="card-title mb-1">${member.name}</h6>
                            <small class="text-muted">${member.role || 'Crew'}</small>
                        </div>
                    </div>
                </div>
            `).join('') : '<p class="text-muted">No crew available.</p>'}
        </div>

        <h3 class="mt-5">🌍 International Releases</h3>
        <ul class="list-group">
            ${releases?.length > 0 ? releases.map(r => `
                <li class="list-group-item">
                    ${r.country} (${r.type}) – ${new Date(r.date).toLocaleDateString()}
                </li>
            `).join('') : '<li class="list-group-item text-muted">No release data available.</li>'}
        </ul>
    `;
    }



    function loadMovieDetails(movieId) {
        showLoader();
        const url = `${API_URL}/movies/${movieId}`;

        fetch(url)
            .then(response => {
                if (!response.ok) throw new Error('Film non trovato');
                return response.json();
            })
            .then(data => {
                console.log('Dati del film ricevuti:', data);
                renderMovieDetails(data);
            })
            .catch(error => {
                console.error('Errore:', error);
                movieDetailsContainer.innerHTML = `<p class="text-danger">Errore: ${error.message}</p>`;
            })
            .finally(() => hideLoader());
    }
    loadMovieDetails(movieId);

    const searchForm = document.getElementById('search-form');
    const searchInput = document.getElementById('search-bar');

    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const query = searchInput.value.trim();
        if (query) {
            window.location.href = `index.html?q=${encodeURIComponent(query)}`;
        }
    });

});