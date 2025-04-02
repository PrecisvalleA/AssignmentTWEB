package com.AssignmentTWEB.springboot.service;

import com.AssignmentTWEB.springboot.model.Theme;
import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    //get all themes
    public List<String> getThemeById(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Theme> themes = themeRepository.findByMovie(movie);

        return themes.stream()
                .map(theme -> theme.getId_Theme().getTheme())
                .collect(Collectors.toList());
    }
}