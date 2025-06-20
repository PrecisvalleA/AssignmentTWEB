package com.AssignmentTWEB.springboot.Languages;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for business logic related to Languages.
 */
@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    /**
     * Retrieve all languages associated with a specific movie.
     *
     * @param id_movie the ID of the movie
     * @return list of languages for the specified movie
     */
    public List<Language> getLanguageByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId(id_movie);

        return languageRepository.findByMovie(movie);
    }
}
