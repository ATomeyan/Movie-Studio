package org.movie.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author Artur Tomeyan
 * @date 23/08/2022
 */

public class Actor {

    private String firstName;
    private String lastName;

    private Integer yearOfBirth;

    private List<Movie> movies;

    public Actor() {
    }

    public Actor(String firstName, String lastName, Integer yearOfBirth, List<Movie> movies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.movies = movies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        return new EqualsBuilder().append(firstName, actor.firstName).append(lastName, actor.lastName)
                .append(yearOfBirth, actor.yearOfBirth)
                .append(movies, actor.movies).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstName).append(lastName).append(yearOfBirth).append(movies).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("yearOfBirth", yearOfBirth)
                .append("movies", movies)
                .toString();
    }
}