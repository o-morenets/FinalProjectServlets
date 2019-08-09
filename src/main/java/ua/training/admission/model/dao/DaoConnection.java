package ua.training.admission.model.dao;

/**
 * interface DaoConnection
 *
 * @author Oleksii Morenets
 */
public interface DaoConnection extends AutoCloseable {

	/**
	 * Starts SQL transaction
	 */
	void beginTransaction();

	/**
	 * Commits changes to database
	 */
	void commit();

	/**
	 * Rolls back changes from database
	 */
	void rollback();

	/**
	 * Closes database connection
	 */
	void close();
}
