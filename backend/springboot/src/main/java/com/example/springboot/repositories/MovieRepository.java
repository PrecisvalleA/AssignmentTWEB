package com.example.reviewsite.repository;

import com.example.reviewsite.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
