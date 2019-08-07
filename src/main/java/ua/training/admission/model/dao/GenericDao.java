package ua.training.admission.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * interface GenericDao
 *
 * @author Oleksii Morenets
 */
public interface GenericDao<T> {

    /**
     * Find one entity by its id
     *
     * @param id entity id
     * @return Optional entity
     */
    Optional<T> findById(Long id);

    /**
     * Find all entities
     *
     * @return all entities
     */
    List<T> findAll();

    /**
     * Creates new entity
     *
     * @param t entity
     */
    void create(T t);

    /**
     * Updates existing entity
     *
     * @param t entity
     */
    void update(T t);

    /**
     * Deletes entity by its id
     *
     * @param id entity id
     */
    void delete(Long id);

}
