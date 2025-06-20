package com.AssignmentTWEB.springboot.Themes;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for business logic related to Themes.
 */
@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    /**
     * Retrieve all themes associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of themes for the specified movie
     */
    public List<Theme> getThemeById(Integer id) {

        Movie movie = new Movie();
        movie.setId(id);

        return themeRepository.findByMovie(movie);
    }
}