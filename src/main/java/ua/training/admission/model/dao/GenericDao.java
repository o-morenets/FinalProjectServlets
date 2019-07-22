package ua.training.admission.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * interface GenericDao
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public interface GenericDao<T> {
	
    Optional<T> findById(int id);

    List<T> findAll();

    void create(T t);

    void update(T t);

    void delete(int id);

}
