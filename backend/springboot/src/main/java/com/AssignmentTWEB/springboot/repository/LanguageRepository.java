package com.AssignmentTWEB.springboot.repository;

import com.AssignmentTWEB.springboot.model.Language;
import com.AssignmentTWEB.springboot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>{

    List<Language> findByMovie(Movie movie);

}
