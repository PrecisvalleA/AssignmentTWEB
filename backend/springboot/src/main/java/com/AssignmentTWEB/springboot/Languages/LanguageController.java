package com.AssignmentTWEB.springboot.Languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("movies/{id}")
    public List<Language> getAllLanguages(@PathVariable Integer id) {
        return languageService.getLanguageByMovie(id);
    }
}
