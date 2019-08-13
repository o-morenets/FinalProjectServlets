package ua.training.admission.model.validator;

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

            return passGrade >= 0.0 && passGrade <= 100.0;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
