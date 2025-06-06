package com.AssignmentTWEB.springboot.Themes;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    //get all themes
    public List<Theme> getThemeById(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        return themeRepository.findByMovie(movie);
    }
}