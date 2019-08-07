package ua.training.admission.model.entity;

import java.util.Set;

/**
 * Represents a Subject Entity
 *
 * @author Oleksii Morenets
 */
public class Subject {

    private Long id;
    private String name;
    private Set<SubjectGrade> grades;
    private Set<Speciality> specialities;

    public static class Builder {

        private Long id;
        private String name;
        private Set<SubjectGrade> grades;
        private Set<Speciality> specialities;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder grades(Set<SubjectGrade> grades) {
            this.grades = grades;
            return this;
        }

        public Builder specialities(Set<Speciality> specialities) {
            this.specialities = specialities;
            return this;
        }

        public Subject build() {
            Subject subject = new Subject();
            subject.setId(id);
            subject.setName(name);
            subject.setSpecialities(specialities);
            subject.setGrades(grades);

            return subject;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SubjectGrade> getGrades() {
        return grades;
    }

    public void setGrades(Set<SubjectGrade> grades) {
        this.grades = grades;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
