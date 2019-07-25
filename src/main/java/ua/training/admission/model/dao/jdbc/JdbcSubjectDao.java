package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.SubjectDao;
import ua.training.admission.model.entity.Subject;
import ua.training.admission.view.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcSubjectDao implements SubjectDao {

    private static final Logger LOG = Logger.getLogger(JdbcSubjectDao.class);
    private Connection connection;

    JdbcSubjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Subject> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Subject> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Subject subject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Subject subject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    private Subject getEntityFromResultSet(ResultSet rs) throws SQLException {
        return Subject.builder()
                .id(rs.getLong(SQL.SUBJECT_ID))
                .name(rs.getString(SQL.SUBJECT_NAME))
                // TODO specialities & grades
                .build();
    }
}
