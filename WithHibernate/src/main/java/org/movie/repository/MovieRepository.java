package org.movie.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.movie.entity.Movie;

import java.util.List;
import java.util.Optional;

/**
 * @author Artur Tomeyan
 * @date 22/08/2022
 */
public class MovieRepository {

    private final EntityManager entityManager;

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Movie> findAll() {
        return entityManager.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    public Movie findByTitle(String title) {
        return entityManager.createQuery("select m from Movie m where m.title = :title", Movie.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    public Optional<Movie> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    public List<Movie> findByActor() {
        return entityManager
                .createQuery("select m from Movie m left join fetch m.actors", Movie.class)
                .getResultList();
    }

    public Movie save(Movie movie) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();

            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.persist(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }

        return null;
    }

    public void remove(Integer id) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();

            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.remove(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }
}