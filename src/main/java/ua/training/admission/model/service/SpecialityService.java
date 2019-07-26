package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.SpecialityDao;
import ua.training.admission.model.entity.Speciality;

import java.util.List;

public class SpecialityService {

    private static final Logger log = Logger.getLogger(SpecialityService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {

        static final SpecialityService INSTANCE = new SpecialityService();
    }

    public static SpecialityService getInstance() {
        return SpecialityService.Holder.INSTANCE;
    }

    public List<Speciality> findAll() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SpecialityDao userDao = daoFactory.createSpecialityDao(connection);
            return userDao.findAll();
        }
    }
}
