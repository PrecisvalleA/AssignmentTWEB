package com.AssignmentTWEB.springboot.OscarAwards;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service class responsible for business logic related to Oscar Awards.
 */
@Service
public class OscarAwardService {
    @Autowired
    private OscarAwardRepository oscarAwardRepository;

    /**
     * Retrieve Oscar awards by movie name.
     *
     * @param film the name of the film
     * @return list of OscarAward entries for the given film
     */
    public List<OscarAward> getByMovieName(String film) {
        return oscarAwardRepository.findByFilm(film);
    }

    /**
     * Retrieve all Oscar awards from the database.
     *
     * @return list of all OscarAward entries
     */
    public List<OscarAward> getAll() {
        return oscarAwardRepository.findAll();
    }
}
