package com.AssignmentTWEB.springboot.repository;

import com.AssignmentTWEB.springboot.model.Release;
import com.AssignmentTWEB.springboot.primarykey.ReleasePrimaryKey;
import com.AssignmentTWEB.springboot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {
    List<Release> findByMovie(Movie id_movie); // Find all releases by movie ID
}
