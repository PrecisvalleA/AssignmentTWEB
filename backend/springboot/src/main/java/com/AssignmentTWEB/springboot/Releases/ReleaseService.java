package com.AssignmentTWEB.springboot.Releases;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    //get all releases
    public List<Release> getReleaseByMovie(Integer id) {

        Movie movie = new Movie();
        movie.setId(id);

        return releaseRepository.findByMovie(movie);
    }
}

