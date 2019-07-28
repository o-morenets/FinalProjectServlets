package ua.training.admission.model.dao;

/**
 * interface DaoConnection
 */
public interface DaoConnection extends AutoCloseable {

	void beginTransaction();

	void commit();

	void rollback();

	void close();

}
