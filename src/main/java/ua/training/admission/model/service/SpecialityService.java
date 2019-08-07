package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.SpecialityDao;
import ua.training.admission.model.entity.Speciality;

import java.util.List;

/**
 * Speciality Service
 *
 * @author Oleksii Morenets
 */
public class SpecialityService {

    /* Logger */
    private static final Logger log = Logger.getLogger(SpecialityService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /** Lazy holder for service instance */
    private static class Holder {
        static final SpecialityService INSTANCE = new SpecialityService();
    }

    public static SpecialityService getInstance() {
        return SpecialityService.Holder.INSTANCE;
    }

    /**
     * Find all Specialities
     *
     * @return all specialities
     */
    public List<Speciality> findAll() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SpecialityDao userDao = daoFactory.createSpecialityDao(connection);

            return userDao.findAll();
        }
    }
}
