package org.movie;

import org.movie.dao.MovieDao;
import org.movie.entity.Movie;
import org.movie.service.MovieService;

import java.util.List;

/**
 * @author Artur Tomeyan
 * @date ${DATE}
 */
public class Main {
    public static void main(String[] args) {

        MovieDao movie = new MovieService();

        movie.save(new Movie(1, "Back to the future with typo", 1985));

        List<Movie> all = movie.findAll();

        System.out.println(all.toString());
    }
}