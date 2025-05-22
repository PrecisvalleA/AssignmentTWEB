package com.AssignmentTWEB.springboot.Genres;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    //get all genres
    public List<String> getGenreById(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Genre> genres = genreRepository.findByMovie(movie);

        return genres.stream()
                .map(genre -> genre.getGenre())
                .collect(Collectors.toList());
    }
}