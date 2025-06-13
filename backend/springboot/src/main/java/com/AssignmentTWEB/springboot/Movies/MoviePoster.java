package com.AssignmentTWEB.springboot.Movies;

public interface MoviePoster {
    Movie   getMovie();
    String  getPosterUrl();
    String getGenre();
    String getGenres();
}
