package ua.training.admission.model.validator;

import ua.training.admission.view.Constants;

public class NumberValidator {

    /**
     * Check whether form parameter is a valid Double
     *
     * @param paramPassGrade form parameter
     * @return true if user input is valid Double, false otherwise
     */
    public boolean isValidDouble(String paramPassGrade) {
        try {
            double passGrade = Double.parseDouble(paramPassGrade);

            return passGrade >= Constants.GRADE_MIN && passGrade <= Constants.GRADE_MAX;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
