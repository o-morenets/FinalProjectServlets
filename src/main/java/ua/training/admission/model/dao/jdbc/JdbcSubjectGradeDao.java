package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.SubjectGradeDao;
import ua.training.admission.model.entity.Subject;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSubjectGradeDao implements SubjectGradeDao {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcSubjectGradeDao.class);

    private Connection connection;

    JdbcSubjectGradeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<SubjectGrade> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SubjectGrade> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(SubjectGrade subjectGrade) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.INSERT_INTO_SUBJECT_GRADE))) {
            stmt.setLong(1, subjectGrade.getUser().getId());
            stmt.setLong(2, subjectGrade.getSubject().getId());
            stmt.setInt(3, subjectGrade.getGrade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(SubjectGrade subjectGrade) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.UPDATE_SUBJECT_GRADE))) {
            stmt.setInt(1, subjectGrade.getGrade());
            stmt.setLong(2, subjectGrade.getUser().getId());
            stmt.setLong(3, subjectGrade.getSubject().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<SubjectGrade> findByUserIdAndSubjectId(Long userId, Long subjectId) {
        Optional<SubjectGrade> result = Optional.empty();
        try (PreparedStatement stmt =
                     connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_FROM_SUBJECT_GRADE_BY_USER_ID_AND_SUBJECT_ID)))
        {
            stmt.setLong(1, userId);
            stmt.setLong(2, subjectId);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                SubjectGrade subjectGrade = getEntityFromResultSet(resultSet);
                result = Optional.of(subjectGrade);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public List<SubjectGrade> findAllUserSubjectGradeByUser(User user) {
        List<SubjectGrade> result = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_USER_SUBJECT_GRADES))) {
            stmt.setLong(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                SubjectGrade subjectGrade = getEntityFromResultSet(resultSet);
                setSubject(subjectGrade, resultSet);
                result.add(subjectGrade);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public void deleteByUserIdAndSubjectId(Long userId, Long subjectId) {
        try (PreparedStatement stmt =
                     connection.prepareStatement(SQL.getSqlElement(SQL.DELETE_FROM_SUBJECT_GRADE_BY_USER_ID_AND_SUBJECT_ID)))
        {
            stmt.setLong(1, userId);
            stmt.setLong(2, subjectId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    private SubjectGrade getEntityFromResultSet(ResultSet rs) throws SQLException {
        Integer grade = rs.getInt(SQL.SUBJECT_GRADE_GRADE);
        if (rs.wasNull()) {
            grade = null;
        }

        return SubjectGrade.builder()
                .grade(grade)
                .build();
    }

    private void setSubject(SubjectGrade subjectGrade, ResultSet resultSet) throws SQLException {
        subjectGrade.setSubject(Subject.builder()
                .id(resultSet.getLong(SQL.SUBJECT_ID))
                .name(resultSet.getString(SQL.SUBJECT_NAME))
                .build());
    }
}
