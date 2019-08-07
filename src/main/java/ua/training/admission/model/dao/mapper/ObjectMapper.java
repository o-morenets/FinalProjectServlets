package ua.training.admission.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Object Mapper
 *
 * @param <T> type parameter
 *
 * @author Oleksii Morenets
 */
public abstract class ObjectMapper<T> implements Mapper<T> {

    /** Map to store unique entities */
    private Map<Long, T> cache = new HashMap<>();

    @Override
    public abstract T extractFromResultSet(ResultSet resultSet) throws SQLException;

    /**
     * Make unique an entity
     *
     * @param item   entity
     * @param itemId entity id
     * @return unique entity
     */
    T makeUnique(T item, Long itemId) {
        cache.putIfAbsent(itemId, item);

        return cache.get(itemId);
    }

    /**
     * Returns unique entity list from cache
     *
     * @return unique entity list from cache
     */
    public List<T> getUniqueList() {
        return new ArrayList<>(cache.values());
    }
}
