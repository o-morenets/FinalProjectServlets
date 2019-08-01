package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.SpecialityDao;
import ua.training.admission.model.entity.Speciality;
import ua.training.admission.model.entity.Subject;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcSpecialityDao implements SpecialityDao {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcSpecialityDao.class);

    private Connection connection;

    JdbcSpecialityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Speciality> findById(Long id) {
        Optional<Speciality> result = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_SPECIALITY_BY_ID))) {
            stmt.setString(1, String.valueOf(id));
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Speciality speciality = getEntityFromResultSet(resultSet);
                result = Optional.of(speciality);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public List<Speciality> findAll() {
        Map<Long, Speciality> specialityMap = new HashMap<>();

        try (PreparedStatement stmt =
                     connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_SPECIALITIES_WITH_SUBJECTS)))
        {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Speciality speciality = getEntityFromResultSet(resultSet);
                setSubject(specialityMap, resultSet, speciality);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return new ArrayList<>(specialityMap.values());
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
                .subjects(new HashSet<>())
                .build();
    }

    private void setSubject(Map<Long, Speciality> specialityMap, ResultSet resultSet, Speciality speciality)
            throws SQLException
    {
        specialityMap.putIfAbsent(speciality.getId(), speciality);
        speciality = specialityMap.get(speciality.getId());

        Subject subject = null;
        Long id = resultSet.getLong(SQL.SUBJECT_ID);
        if (!resultSet.wasNull()) {
            subject = Subject.builder()
                    .id(id)
                    .name(resultSet.getString(SQL.SUBJECT_NAME))
                    .specialities(new HashSet<>())
                    .build();
        }

        if (subject != null) {
            speciality.getSubjects().add(subject);
            subject.getSpecialities().add(speciality);
        }
    }
}
