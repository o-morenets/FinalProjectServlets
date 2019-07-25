package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.SubjectGradeDao;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.view.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private SubjectGrade getEntityFromResultSet(ResultSet rs) throws SQLException {
        return SubjectGrade.builder()
                .id(rs.getLong(SQL.SUBJECT_GRADE_ID))
                // TODO user & subject
                .grade(rs.getInt(SQL.SUBJECT_GRADE_GRADE))
                .build();
    }
}
