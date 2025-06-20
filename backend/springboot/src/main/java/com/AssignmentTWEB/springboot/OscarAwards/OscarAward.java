package com.AssignmentTWEB.springboot.OscarAwards;

import jakarta.persistence.*;

/**
 * Entity class representing an Oscar Award entry.
 * Maps to the "the_oscar_awards" table in the database.
 */
@Entity
@Table(name = "the_oscar_awards")
public class OscarAward {

    /** Unique identifier for the Oscar Award entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The year of the film's release. */
    @Column(nullable = false)
    private Integer year_film;

    /** The year in which the Oscar ceremony took place. */
    @Column(nullable = false)
    private Integer year_ceremony;

    /** The number (edition) of the Oscar ceremony. */
    @Column(nullable = false)
    private Integer ceremony;

    /** The award category */
    @Column(nullable = false)
    private String category;

    /** The name of the nominee. */
    @Column(nullable = false)
    private String name;

    /** The film associated with the nomination. */
    @Column(nullable = false)
    private String film;

    /** Boolean flag indicating if the nominee won the award. */
    @Column(nullable = false)
    private Boolean winner;

    /** Default constructor required by JPA. */
    public OscarAward() {}

    /** Getters and setters for the OscarAwards parameters */

    public Long getId_oscar_award() {return id;}
    public void setId_oscar_award(Long id) {this.id = id;}

    public Integer getYear_film() {return year_film;}
    public void setYear_film(Integer year_film) {this.year_film = year_film;}

    public Integer getYear_ceremony() {return year_ceremony;}
    public void setYear_ceremony(Integer year_ceremony) {this.year_ceremony = year_ceremony;}

    public Integer getCeremony() {return ceremony;}
    public void setCeremony(Integer ceremony) {this.ceremony = ceremony;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getFilm() {return film;}
    public void setFilm(String film) {this.film = film;}

    public Boolean getWinner() {return winner;}
    public void setWinner(Boolean winner) {this.winner = winner;}

}
