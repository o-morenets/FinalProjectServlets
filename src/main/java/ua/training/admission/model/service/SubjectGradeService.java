package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;

import java.util.List;

public class SubjectGradeService {

    private static final Logger LOG = Logger.getLogger(SubjectGradeService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {

        static final SubjectGradeService INSTANCE = new SubjectGradeService();
    }

    public static SubjectGradeService getInstance() {
        return SubjectGradeService.Holder.INSTANCE;
    }

    public List<SubjectGrade> getUserSubjectGradeList(User user) {
        // TODO
        return null;
    }
}
