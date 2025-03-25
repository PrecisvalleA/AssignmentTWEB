package com.AssignmentTWEB.springboot.service;

import com.AssignmentTWEB.springboot.model.Actor;
import com.AssignmentTWEB.springboot.model.Language;
import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.primarykey.LanguagePrimaryKey;
import com.AssignmentTWEB.springboot.repository.LanguageRepository;
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
                .map(language -> language.getId_language().getType() + " - " + language.getId_language().getLanguage())
                .collect(Collectors.toList());
    }
}
