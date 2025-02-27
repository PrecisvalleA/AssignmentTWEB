package com.AssignmentTWEB.springboot.repository; // package class

import com.AssignmentTWEB.springboot.model.Movie; // import movie entity which represents "movies" table
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // list is necessary for function which return many results

@Repository

public interface MovieRepository extends JpaRepository<Movie, Integer>{ //extends JpaRepository for Movie entity with Long ID

    List<Movie> findByNameContaining(String name); // return all film where name contains String name

    List<Movie> findByMinuteGreaterThanEqual(double minute); //return all film where

    List<Movie> findByMinuteBetween(double min, double max);

    List<Movie> findByRatingBetween(double min, double max);

    List<Movie> findByRatingGreaterThanEqual(double rating); //return all film where rating is greater or equal to a value

    List<Movie> findAllByOrderByRatingDesc(); //return best movies by rating
}