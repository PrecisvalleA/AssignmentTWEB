package com.AssignmentTWEB.springboot.Crews;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewService {

    @Autowired
    private CrewRepository crewRepository;

    //get all crews
    public List<String> getCrewsByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Crew> crews = crewRepository.findByMovie(movie);

        return crews.stream()
                .map(crew -> crew.getRole() + " - " + crew.getName())
                .collect(Collectors.toList());
    }
}