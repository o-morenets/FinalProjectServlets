package ua.training.admission.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface Mapper<T> {

    T extractFromResultSet(ResultSet resultSet) throws SQLException;

}
