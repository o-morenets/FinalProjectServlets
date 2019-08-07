package ua.training.admission.model.dao;

/**
 * interface DaoConnection
 *
 * @author Oleksii Morenets
 */
public interface DaoConnection extends AutoCloseable {

	void beginTransaction();

	void commit();

	void rollback();

	void close();

}
