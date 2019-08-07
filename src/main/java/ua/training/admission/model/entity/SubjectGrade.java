package ua.training.admission.model.entity;

/**
 * Represents a SubjectGrade Entity
 *
 * @author Oleksii Morenets
 */
public class SubjectGrade {

    private User user;
    private Subject subject;
    private Integer grade;

    public static class Builder {

        private User user;
        private Subject subject;
        private Integer grade;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Builder grade(Integer grade) {
            this.grade = grade;
            return this;
        }

        public SubjectGrade build() {
            SubjectGrade subjectGrade = new SubjectGrade();
            subjectGrade.setUser(user);
            subjectGrade.setSubject(subject);
            subjectGrade.setGrade(grade);

            return subjectGrade;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters & Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}