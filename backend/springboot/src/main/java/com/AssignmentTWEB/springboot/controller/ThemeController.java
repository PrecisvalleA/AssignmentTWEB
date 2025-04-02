package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.Theme;
import com.AssignmentTWEB.springboot.service.ThemeService;
import com.AssignmentTWEB.springboot.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/movies/{id_movie}")
    public List<String> getAllThemes(@PathVariable Integer id_movie) {
        return themeService.getThemeById(id_movie);
    }
}