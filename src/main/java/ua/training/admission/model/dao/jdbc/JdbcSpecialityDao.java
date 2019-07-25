package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.SpecialityDao;
import ua.training.admission.model.entity.Speciality;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSpecialityDao implements SpecialityDao {

    private static final Logger LOG = Logger.getLogger(JdbcSpecialityDao.class);
    private Connection connection;

    JdbcSpecialityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Speciality> findById(Long id) {
        Optional<Speciality> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SQL.SELECT_SPECIALITY_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Speciality speciality = getEntityFromResultSet(resultSet);
                result = Optional.of(speciality);
            }
        } catch (SQLException ex) {
            throw new AppException(Messages.SQL_ERROR, ex);
        }
        return result;
    }

    @Override
    public List<Speciality> findAll() {
        List<Speciality> result = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_SPECIALITIES)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            throw new AppException(Messages.SQL_ERROR, e);
        }

        return result;
    }

    @Override
    public void create(Speciality speciality) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Speciality speciality) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    private Speciality getEntityFromResultSet(ResultSet rs) throws SQLException {
        return Speciality.builder()
                .id(rs.getLong(SQL.SPECIALITY_ID))
                .name(rs.getString(SQL.SPECIALITY_NAME))
                .build();
    }
}
