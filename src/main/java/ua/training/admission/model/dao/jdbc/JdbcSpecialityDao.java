package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.SpecialityDao;
import ua.training.admission.model.dao.mapper.SpecialityMapper;
import ua.training.admission.model.dao.mapper.SubjectMapper;
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

            SpecialityMapper specialityMapper = new SpecialityMapper();

            if (resultSet.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(resultSet);
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
        List<Speciality> result;

        try (PreparedStatement stmt = connection.prepareStatement(
                SQL.getSqlElement(SQL.SELECT_SPECIALITIES_WITH_SUBJECTS))
        ) {
            ResultSet resultSet = stmt.executeQuery();

            SpecialityMapper specialityMapper = new SpecialityMapper();
            SubjectMapper subjectMapper = new SubjectMapper();

            while (resultSet.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(resultSet);
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                speciality.getSubjects().add(subject);
                subject.getSpecialities().add(speciality);
            }

            result = specialityMapper.getUniqueList();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
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
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
