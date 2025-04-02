package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.OscarAward;
import com.AssignmentTWEB.springboot.service.OscarAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/oscarAwards")
public class OscarAwardController {
    @Autowired
    private OscarAwardService oscarAwardService;

    @GetMapping
    public List<OscarAward> getAllOscarAwards() {
        return oscarAwardService.getAll();
    }

    @GetMapping("/movie/{name}")
    public List<OscarAward> getOscarAwardsByMovie(@PathVariable String name) {
        return oscarAwardService.getByMovieName(name);
    }
}
