package com.AssignmentTWEB.springboot.Themes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/movies/{id}")
    public List<Theme> getAllThemes(@PathVariable Integer id) {
        return themeService.getThemeById(id);
    }
}