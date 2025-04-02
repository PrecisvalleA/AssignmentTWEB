package com.AssignmentTWEB.springboot.service;

import java.util.List;
import com.AssignmentTWEB.springboot.model.OscarAward;
import com.AssignmentTWEB.springboot.repository.OscarAwardRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OscarAwardService {
    @Autowired
    private OscarAwardRepository oscarAwardRepository;

    public List<OscarAward> getByMovieName(String movieName) {
        return oscarAwardRepository.findByMovieName(movieName);
    }

    public List<OscarAward> getAll() {
        return oscarAwardRepository.findAll();
    }
}
