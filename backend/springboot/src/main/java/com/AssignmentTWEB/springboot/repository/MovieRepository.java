package com.AssignmentTWEB.springboot.repository; // package class

import com.AssignmentTWEB.springboot.model.Movie; // import movie entity which represents "movies" table
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // list is necessary for function which return many results

@Repository

public interface MovieRepository extends JpaRepository<movie, long>{ //extends JpaRepository for Movie entity with Long ID

    List<Movie> findByNameContaing(String name); // return all film where name contains String name

    List<Movie> findByRatingGreaterThanEqual(double rating); //return all film where rating is greater or equal to a value

}