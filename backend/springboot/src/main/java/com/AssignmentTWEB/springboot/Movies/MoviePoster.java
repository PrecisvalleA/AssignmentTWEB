package com.AssignmentTWEB.springboot.Movies;

/**
 * Projection interface for retrieving simplified movie information
 * including poster and genres.
 */
public interface MoviePoster {
    /**
     * Gets the movie entity.
     * @return the Movie object
     */
    Movie   getMovie();

    /**
     * Gets the URL of the associated poster.
     * @return poster URL
     */
    String  getPosterUrl();

    /**
     * Gets the genre (deprecated - use getGenres()).
     * @return genre string
     */
    String getGenre();

    /**
     * Gets the concatenated genres as a string.
     * @return genres string
     */
    String getGenres();
}
