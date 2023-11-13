package org.movie.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.SessionFactory;
import org.movie.entity.Actor;

import java.util.List;
import java.util.Optional;

/**
 * @author Artur Tomeyan
 * @date 22/08/2022
 */
public class ActorRepository {

    private final EntityManager entityManager;
    private EntityTransaction transaction;

    public ActorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Actor> findByYear(Integer year) {

        return entityManager.createQuery("select a from Actor a where a.yearOfBirth > :year", Actor.class)
                .setParameter("yearOfBirth", year)
                .getResultList();
    }

    public List<Actor> findByNameChar(String s) {

        return entityManager.createQuery("select a from Actor a where a.lastName like :lastName", Actor.class)
                .setParameter("lastName", "%" + s)
                .getResultList();
    }

    public Optional<Actor> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Actor.class, id));
    }

    public Actor save(Actor actor) {

        try {
            transaction = entityManager.getTransaction();

            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.persist(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }

        return null;
    }
}