package com.AssignmentTWEB.springboot.OscarAwards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OscarAwardRepository extends JpaRepository<OscarAward, Long>{
    List<OscarAward> findByFilm(String film);
}
