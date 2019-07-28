package ua.training.admission.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * interface GenericDao
 */
public interface GenericDao<T> {
	
    Optional<T> findById(Long id);

    List<T> findAll();

    void create(T t);

    void update(T t);

    void delete(int id);

}
