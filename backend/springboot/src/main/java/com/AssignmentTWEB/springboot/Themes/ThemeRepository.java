
package com.AssignmentTWEB.springboot.Themes;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    List<Theme> findByMovie(Movie movie); //find all movie's themes
}
