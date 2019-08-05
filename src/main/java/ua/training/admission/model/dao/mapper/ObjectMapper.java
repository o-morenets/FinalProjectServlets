package ua.training.admission.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ObjectMapper<T> implements Mapper<T> {

    private Map<Long, T> cache = new HashMap<>();

    @Override
    public abstract T extractFromResultSet(ResultSet resultSet) throws SQLException;

    T makeUnique(T item, Long itemId) {
        cache.putIfAbsent(itemId, item);

        return cache.get(itemId);
    }

    public List<T> getUniqueList() {
        return new ArrayList<>(cache.values());
    }
}
