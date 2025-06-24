package com.AssignmentTWEB.springboot.Movies;

/**
 * Projection interface for retrieving simplified movie information
 * including poster and genres.
 */
public class MoviePoster {
    private Movie movie;
    private String posterUrl;
    private String genres;

    public MoviePoster(Movie movie, String posterUrl, String genres) {
        this.movie = movie;
        this.posterUrl = posterUrl;
        this.genres = genres;
    }
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
//    /**
//     * Gets the movie entity.
//     * @return the Movie object
//     */
//    Movie   getMovie();
//
//    /**
//     * Gets the URL of the associated poster.
//     * @return poster URL
//     */
//    String  getPosterUrl();
//
//    /**
//     * Gets the genre (deprecated - use getGenres()).
//     * @return genre string
//     */
//    String getGenre();
//
//    /**
//     * Gets the concatenated genres as a string.
//     * @return genres string
//     */
//    String getGenres();
}
