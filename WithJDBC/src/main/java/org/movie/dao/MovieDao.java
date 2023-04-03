package org.movie.dao;

import org.movie.entity.Movie;

import java.util.List;
import java.util.Optional;

/**
 * @author Artur Tomeyan
 * @date 23/08/2022
 */
public interface MovieDao {

    void createTable();

    List<Movie> findAll();

    Optional<Movie> findById(Integer id);

    Movie findByTitle(String title);

    List<Movie> findByYear(Integer year);

    Movie findByReleaseYear(Integer year);

    Movie save(Movie movie);

    void remove(Integer id);
}