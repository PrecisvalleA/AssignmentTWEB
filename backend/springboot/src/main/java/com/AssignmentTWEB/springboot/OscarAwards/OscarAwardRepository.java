package com.AssignmentTWEB.springboot.OscarAwards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for OscarAward entity.
 * Provides CRUD operations and custom queries for Oscar Awards.
 */
@Repository
public interface OscarAwardRepository extends JpaRepository<OscarAward, Long>{
    /**
     * Retrieve Oscar awards by film name.
     *
     * @param film the name of the film
     * @return list of OscarAward entries related to the given film
     */
    List<OscarAward> findByFilm(String film);
}
