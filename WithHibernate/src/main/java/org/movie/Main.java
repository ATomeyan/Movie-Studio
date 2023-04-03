package org.movie;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.movie.entity.Actor;
import org.movie.entity.Genre;
import org.movie.entity.Movie;
import org.movie.repository.GenreRepository;

/**
 * @author Artur Tomeyan
 * @date ${DATE}
 */

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();

        GenreRepository genreRepository = new GenreRepository(sessionFactory.createEntityManager());

        genreRepository.save(new Genre("Action", null));
        System.out.println(genreRepository.getAllGenre().toString());
    }
}