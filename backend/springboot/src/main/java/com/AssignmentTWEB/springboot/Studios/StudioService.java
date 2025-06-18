package com.AssignmentTWEB.springboot.Studios;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioService {
    @Autowired
    private StudioRepository studioRepository;

    //get all studios
    public List<Studio> getStudioByMovie(Integer id) {

        Movie movie = new Movie();
        movie.setId(id);

        return studioRepository.findByMovie(movie);
    }
}
