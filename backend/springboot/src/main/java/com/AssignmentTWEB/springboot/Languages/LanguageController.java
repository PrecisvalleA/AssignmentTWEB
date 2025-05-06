package com.AssignmentTWEB.springboot.Languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("movies/{id_movie}")
    public List<String> getAllLanguages(@PathVariable Integer id_movie) {
        return languageService.getLanguageByMovie(id_movie);
    }
}
