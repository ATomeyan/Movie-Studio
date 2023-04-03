package org.movie.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author Artur Tomeyan
 * @date 19/08/2022
 */

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "year_of_release")
    private Integer yearOfRelease;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    @ManyToOne
    private Genre genre;

    public Movie() {
    }

    public Movie(String title, Integer yearOfRelease, List<Actor> actors, Genre genre) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.actors = actors;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return new EqualsBuilder().append(title, movie.title)
                .append(yearOfRelease, movie.yearOfRelease).append(actors, movie.actors)
                .append(genre, movie.genre).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title).append(yearOfRelease).append(actors).append(genre).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("yearOfRelease", yearOfRelease)
                .append("actors", actors)
                .append("genre", genre)
                .toString();
    }
}