package ua.training.admission.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * interface Mapper
 *
 * @param <T> type parameter
 *
 * @author Oleksii Morenets
 */
public interface Mapper<T> {

    T extractFromResultSet(ResultSet resultSet) throws SQLException;

}
