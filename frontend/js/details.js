document.addEventListener("DOMContentLoaded", () =>{
    const detailsContainer = document.getElementById("detailsContainer");
    if(!detailsContainer){return;}

    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");
    if(!id){
        detailContainer.innerHTML = '<p class="text-danger">ID non specificato.</p>';
        return;
    }

    fetch(`http://localhost:8080/movies/details/{id}`)
        .then(response => {
            if (!response.ok) throw new Error(`Status ${response.status}`);
            return response.json();
        })
        .then(data => {
            if(data.error){
                detailsContainer.innerHTML = `<p class="text-danger">${data.error}</p>`;
                return;
            }
            const m = data.movie;

        })
})