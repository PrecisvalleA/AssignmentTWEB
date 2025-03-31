package com.AssignmentTWEB.springboot.service;

import com.AssignmentTWEB.springboot.model.Release;
import com.AssignmentTWEB.springboot.primarykey.ReleasePrimaryKey;
import com.AssignmentTWEB.springboot.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    // Get all releases
    public List<Release> getAllReleases() {
        return releaseRepository.findAll();
    }

    // Get all releases for a specific movie ID
    public List<Release> getReleasesByMovie(Long id_movie) {
        return releaseRepository.findByIdRelease_IdMovie(id_movie);
    }
}
