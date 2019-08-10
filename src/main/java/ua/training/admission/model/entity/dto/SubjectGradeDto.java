package ua.training.admission.model.entity.dto;

/**
 * SubjectGrade DTO for data transfer
 */
public class SubjectGradeDto {

    private String subject;
    private String grade;

    public SubjectGradeDto(String subject, String grade) {
        this.subject = subject;
        this.grade = grade;
    }

    // Getters & Setters

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
