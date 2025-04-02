package com.AssignmentTWEB.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AssignmentTWEB.springboot.model.OscarAward;
import java.util.List;

@Repository
public interface OscarAwardRepository extends JpaRepository<OscarAward, Integer>{
    List<OscarAward> findByMovieName(String movieName);
}
