package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.Language;
import com.AssignmentTWEB.springboot.service.LanguageService;
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
