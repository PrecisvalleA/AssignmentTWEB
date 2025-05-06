package com.AssignmentTWEB.springboot.OscarAwards;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OscarAwardService {
    @Autowired
    private OscarAwardRepository oscarAwardRepository;

    public List<OscarAward> getByMovieName(String film) {
        return oscarAwardRepository.findByFilm(film);
    }

    public List<OscarAward> getAll() {
        return oscarAwardRepository.findAll();
    }
}
