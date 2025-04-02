
package com.AssignmentTWEB.springboot.repository;

import com.AssignmentTWEB.springboot.model.Theme;
import com.AssignmentTWEB.springboot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    List<Theme> findByMovie(Movie movie); //find all movie's themes
}
