package ua.training.admission.model.entity;

import java.util.Set;

public class Subject {

    private Long id;
    private String name;
    private Set<SubjectGrade> grades;
    private Set<Speciality> specialities;
}
