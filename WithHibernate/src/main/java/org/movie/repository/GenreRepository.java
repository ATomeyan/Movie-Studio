package org.movie.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.movie.entity.Genre;

import java.util.List;
import java.util.Optional;

/**
 * @author Artur Tomeyan
 * @date 19/08/2022
 */
public class GenreRepository {

    private final EntityManager entityManager;
    private EntityTransaction transaction;

    public GenreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Genre> getAllGenre() {
        return entityManager.createQuery("select g from Genre g",Genre.class).getResultList();
    }

    public Optional<Genre> getGenreById(Integer id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    public List<Genre> getByName(String name) {
        return entityManager.createQuery("select g from Genre g where name = :name", Genre.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Genre save(Genre genre) {

        try {
            transaction = entityManager.getTransaction();

            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.persist(genre);
            transaction.commit();
            return genre;
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }

        return null;
    }

    public void delete(Integer id) {

        try {
            transaction = entityManager.getTransaction();

            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.remove(id);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }
    }
}