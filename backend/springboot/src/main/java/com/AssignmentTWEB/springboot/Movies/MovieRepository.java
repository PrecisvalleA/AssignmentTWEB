package com.AssignmentTWEB.springboot.Movies; // package class

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List; // list is necessary for function which return many results

@Repository

public interface MovieRepository extends JpaRepository<Movie, Integer>{ //extends JpaRepository for Movie entity with Integer ID

    List<Movie> findByNameContainingIgnoreCase(String title); // return all film where name contains String name

    List<Movie> findByDateContaining(String date); // return all films where date is >= than a value

    List<Movie> findByMinuteGreaterThanEqual(Double minute); //return all film where minute is >= than a value

    List<Movie> findByMinuteBetween(Double min, Double max); //return all film where minute is between 2 values

    List<Movie> findByRatingBetween(Double min, Double max); //return all film where rating is between 2 values

    List<Movie> findAllByOrderByRatingDesc(); //return best movies by rating

    Page<Movie> findByRatingNotNull(Pageable pageable);

}