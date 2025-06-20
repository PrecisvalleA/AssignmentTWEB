package com.AssignmentTWEB.springboot.Movies;

import com.AssignmentTWEB.springboot.Actors.Actor;
import com.AssignmentTWEB.springboot.Languages.Language;
import com.AssignmentTWEB.springboot.Studios.Studio;
import com.AssignmentTWEB.springboot.Themes.Theme;
import com.AssignmentTWEB.springboot.Genres.Genre;
import com.AssignmentTWEB.springboot.Releases.Release;
import com.AssignmentTWEB.springboot.Crews.Crew;
import com.AssignmentTWEB.springboot.Countries.Country;
import com.AssignmentTWEB.springboot.Posters.Posters;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAward;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/** Data Transfer Object, that includes all the information about a movie
 * Includes related entities such as actors, crew, countries, genres, languages, releases,
 * studios, themes, posters, and Oscars.
 */
public class MovieDetailsDTO {

    private Movie movie;
    private Set<Actor> actors;
    private Set<Crew> crew;
    private Set<Country> countries;
    private Set<Genre> genres;
    private Set<Language> languages;
    private Set<Release> releases;
    private Set<Studio> studios;
    private Set<Theme> themes;
    private Posters posters;
    private List<OscarAward> oscars;

    /**
     * Constructor that initializes all fields of MovieDetailsDTO
     *
     * @param movie the main Movie entity
     * @param actors associated cast
     * @param crew associated crew members
     * @param countries production countries
     * @param genres associated genres
     * @param posters associated posters
     * @param languages spoken languages
     * @param releases release information
     * @param studios associated studios
     * @param themes associated themes
     * @param oscars Oscars won
     */
    public MovieDetailsDTO(Movie movie,
                           Set<Actor> actors,
                           Set<Crew> crew,
                           Set<Country> countries,
                           Set<Genre> genres,
                           Posters posters,
                           Set<Language> languages,
                           Set<Release> releases,
                           Set<Studio> studios,
                           Set<Theme> themes,
                           List<OscarAward> oscars) {
        this.movie = movie;
        this.actors = actors;
        this.crew = crew;
        this.countries = countries;
        this.genres = genres;
        this.languages = languages;
        this.releases = releases;
        this.studios = studios;
        this.themes = themes;
        this.posters = posters;
        this.oscars = oscars;
    }

    // Getters e Setters
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Set<Actor> getActors() {
        return actors;
    }
    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Crew> getCrew() {
        return crew;
    }
    public void setCrew(Set<Crew> crew) {
        this.crew = crew;
    }

    public Set<Country> getCountries() {
        return countries;
    }
    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public Set<Genre> getGenres() {
        return genres;
    }
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<Release> getReleases() {
        return releases;
    }
    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    public Set<Studio> getStudios() {
        return studios;
    }
    public void setStudios(Set<Studio> studios) {
        this.studios = studios;
    }

    public Set<Theme> getThemes() {
        return themes;
    }
    public void setThemes(Set<Theme> themes) {
        this.themes = themes;
    }

    public Posters getPosters() {
        return posters;
    }
    public void setPosters(Posters posters) {
        this.posters = posters;
    }

    public List<OscarAward> getOscars() {
        return oscars;
    }
    public void setOscars(List<OscarAward> oscars) {
        this.oscars = oscars;
    }

    // toString()
    @Override
    public String toString() {
        return "MovieDetailsDTO{" +
                "movie=" + movie +
                ", actors=" + actors +
                ", crew=" + crew +
                ", countries=" + countries +
                ", genres=" + genres +
                ", languages=" + languages +
                ", releases=" + releases +
                ", studios=" + studios +
                ", themes=" + themes +
                ", posters=" + posters +
                ", oscars=" + oscars +
                '}';
    }

    // equals() e hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDetailsDTO)) return false;
        MovieDetailsDTO that = (MovieDetailsDTO) o;
        return Objects.equals(movie, that.movie) &&
                Objects.equals(actors, that.actors) &&
                Objects.equals(crew, that.crew) &&
                Objects.equals(countries, that.countries) &&
                Objects.equals(genres, that.genres) &&
                Objects.equals(languages, that.languages) &&
                Objects.equals(releases, that.releases) &&
                Objects.equals(studios, that.studios) &&
                Objects.equals(themes, that.themes) &&
                Objects.equals(posters, that.posters) &&
                Objects.equals(oscars, that.oscars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, actors, crew, countries, genres, languages,releases, studios, themes, posters, oscars);
    }
}
