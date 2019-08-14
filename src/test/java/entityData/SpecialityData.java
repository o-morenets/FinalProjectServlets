package entityData;

import ua.training.admission.model.entity.Speciality;

public enum SpecialityData {
    
    SP_1(1L, "Хімік"),
    SP_2(2L, "Археолог"),
    SP_3(3L, "Слюсар"),
    SP_4(4L, "Ботанік"),
    SP_5(5L, "Філософ");

    public final Speciality speciality;

    SpecialityData(Long id, String name) {
        speciality = Speciality.builder()
                .id(id)
                .name(name)
                .build();
    }
}
