package com.AssignmentTWEB.springboot.OscarAwards;

import jakarta.persistence.*;

@Entity
@Table(name = "the_oscar_awards")
public class OscarAward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year_film;

    @Column(nullable = false)
    private Integer year_ceremony;

    @Column(nullable = false)
    private Integer ceremony;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String film;

    @Column(nullable = false)
    private Boolean winner;



    public OscarAward() {}

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
