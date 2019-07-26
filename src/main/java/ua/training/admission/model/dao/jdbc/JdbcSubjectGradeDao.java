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

    private static final Logger LOG = Logger.getLogger(JdbcSubjectGradeDao.class);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(SubjectGrade subjectGrade) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SubjectGrade> findAllUserSubjectGradeByUser(User user) {
        List<SubjectGrade> result = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_USER_SUBJECT_GRADES)) {
            stmt.setLong(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                final SubjectGrade subjectGrade = getEntityFromResultSet(resultSet);
                result.add(subjectGrade);
            }

        } catch (SQLException e) {
            throw new AppException(Messages.SQL_ERROR, e);
        }

        return result;
    }

    private SubjectGrade getEntityFromResultSet(ResultSet rs) throws SQLException {
        Integer grade = rs.getInt(SQL.SUBJECT_GRADE_GRADE);
        if (rs.wasNull()) {
            grade = null;
        }
        return SubjectGrade.builder()
                .id(rs.getLong(SQL.SUBJECT_GRADE_ID))
                .subject(Subject.builder()
                        .id(rs.getLong(SQL.SUBJECT_ID))
                        .name(rs.getString(SQL.SUBJECT_NAME))
                        .build())
                .grade(grade)
                .build();
    }
}
