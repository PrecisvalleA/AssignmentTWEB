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
    public List<String> getStudioByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Studio> studios = studioRepository.findByMovie(movie);

        return studios.stream()
                .map(studio -> studio.getId_studio().getStudio())
                .collect(Collectors.toList());
    }
}
