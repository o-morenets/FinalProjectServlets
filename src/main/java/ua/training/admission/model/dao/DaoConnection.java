package ua.training.admission.model.dao;

/**
 * interface DaoConnection
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public interface DaoConnection extends AutoCloseable {

	void begin();

	void commit();

	void rollback();

	void close();

}
