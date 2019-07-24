package ua.training.admission.model.entity;

import java.util.Set;

public class Speciality {

    private Long id;
    private String name;
    private Set<Subject> subjects;

    public static class Builder {

        private Long id;
        private String name;
        private Set<Subject> subjects;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder subjects(Set<Subject> subjects) {
            this.subjects = subjects;
            return this;
        }

        public Speciality build() {
            Speciality speciality = new Speciality();
            speciality.setId(id);
            speciality.setName(name);
            speciality.setSubjects(subjects);

            return speciality;
        }
    }

    public static Speciality.Builder builder() {
        return new Builder();
    }

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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
