package ua.training.admission.model.validator;

import ua.training.admission.model.entity.dto.SubjectGradeDto;
import ua.training.admission.view.Constants;
import ua.training.admission.view.I18n;

public class SubjectGradeValidator extends EntityValidator<SubjectGradeDto> {

    @Override
    public void validate(SubjectGradeDto subjectGradeDto) {
        String gradeParam = subjectGradeDto.getGrade().trim();
        if (gradeParam.isEmpty()) {
            return;
        }

        int grade = -1;
        boolean isNumber;
        try {
            grade = Integer.parseInt(gradeParam);
            isNumber = true;

        } catch (NumberFormatException e) {
            isNumber = false;
        }

        if (!isNumber || grade < Constants.GRADE_MIN || grade > Constants.GRADE_MAX) {
            addError(subjectGradeDto.getSubject() + Constants.SUFFIX_ERROR, I18n.FORM_INVALID_INTEGER);
        }
    }
}
