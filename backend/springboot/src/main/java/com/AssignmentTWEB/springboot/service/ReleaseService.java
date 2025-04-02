package com.AssignmentTWEB.springboot.service;

import com.AssignmentTWEB.springboot.model.Actor;
import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.model.Release;
import com.AssignmentTWEB.springboot.primarykey.ReleasePrimaryKey;
import com.AssignmentTWEB.springboot.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    //get all releases
    public List<String> getReleaseByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Release> releases = releaseRepository.findByMovie(movie);

        return releases.stream()
                .map(release -> release.getId_release().getCountry() + " - " + release.getId_release().getDate()
                + " - " + release.getId_release().getType() + " - " + release.getRating())
                .collect(Collectors.toList());
    }
}

