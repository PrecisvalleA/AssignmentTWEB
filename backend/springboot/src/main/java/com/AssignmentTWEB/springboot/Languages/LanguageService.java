package com.AssignmentTWEB.springboot.Languages;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    //get all languages
    public List<String> getLanguageByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Language> languages = languageRepository.findByMovie(movie);

        return languages.stream()
                .map(language -> language.getType() + " - " + language.getLanguage())
                .collect(Collectors.toList());
    }
}
