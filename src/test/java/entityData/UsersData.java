package entityData;

import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.Speciality;
import ua.training.admission.model.entity.User;

import java.util.Collections;
import java.util.Set;

public enum UsersData {

    A(1L, "serhii.bobkov@ua", "Сергій", "Бобков",
            "0cc175b9c0f1b6a831c399e269772661", "a", Collections.singleton(Role.ADMIN), null),
    Z(2L, "iryna.zaychenko@ua", "Ірина", "Зайченко",
            "fbade9e36a3f36d3d676c1b808451dd7", "z", Collections.singleton(Role.USER), Speciality.builder().id(2L).build()),
    X(3L, "kateryna.osadcha@ua", "Катерина", "Осадча",
            "9dd4e461268c8034f5c8564e155c67a6", "x", Collections.singleton(Role.USER), Speciality.builder().id(3L).build()),
    Q(4L, "alla.shurova@ua", "Алла", "Шурова",
            "7694f4a66316e53c8cdd9d9954bd611d", "q", Collections.singleton(Role.USER), Speciality.builder().id(1L).build()),
    S(5L, "nataliya.tarasova@ua", "Наталія", "Тарасова",
            "03c7c0ace395d80182db07ae2c30f034", "s", Collections.singleton(Role.USER), null),
    W(6L, "serhii.zhdanov@ua", "Сергій", "Жданов",
            "f1290186a5d0b1ceab27f4e77c0c5d68", "w", Collections.singleton(Role.USER), Speciality.builder().id(4L).build()),
    ADMIN(7L, "oleksii.morenets@cmail.com", "Олексій", "Моренець",
            "21232f297a57a5a743894a0e4a801fc3", "admin", Collections.singleton(Role.ADMIN), null),
    USER(8L, "dmytro.gordiychuk@ua", "Дмитро", "Гордійчук",
            "ee11cbb19052e40b07aac0ca060c23ee", "user", Collections.singleton(Role.USER), null),
    STUDENT(9L, "pavlo.polyakov@ua", "Павло", "Поляков",
            "cd73502828457d15655bbd7a63fb0bc8", "student", Collections.singleton(Role.USER), null);

    public final User user;

    UsersData(Long id, String email, String firstName, String lastName,
              String password, String username, Set<Role> roles, Speciality speciality) {

        user = User.builder()
                .id(id)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .username(username)
                .roles(roles)
                .speciality(speciality)
                .build();
    }
}
