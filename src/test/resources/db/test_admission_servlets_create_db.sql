DROP SCHEMA IF EXISTS test_admission_servlets ;

CREATE SCHEMA IF NOT EXISTS test_admission_servlets DEFAULT CHARACTER SET utf8 ;
USE test_admission_servlets ;


DROP TABLE IF EXISTS speciality ;

CREATE TABLE IF NOT EXISTS speciality (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS subject ;

CREATE TABLE IF NOT EXISTS subject (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS speciality_subject ;

CREATE TABLE IF NOT EXISTS speciality_subject (
  speciality_id BIGINT(20) NOT NULL,
  subject_id BIGINT(20) NOT NULL,
  PRIMARY KEY (speciality_id, subject_id),
  INDEX FKbvlrspwe5cj3t0fde37px7lyu (subject_id ASC),
  CONSTRAINT FKbvlrspwe5cj3t0fde37px7lyu
    FOREIGN KEY (subject_id)
    REFERENCES subject (id),
  CONSTRAINT FKi1h4260k9cra32fo5a8u3shpf
    FOREIGN KEY (speciality_id)
    REFERENCES speciality (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS usr ;

CREATE TABLE IF NOT EXISTS usr (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NULL DEFAULT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  speciality_id BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UKdfui7gxngrgwn9ewee3ogtgym (username ASC),
  INDEX FKpxxdan81grbl691clwm0i4ux7 (speciality_id ASC),
  CONSTRAINT FKpxxdan81grbl691clwm0i4ux7
    FOREIGN KEY (speciality_id)
    REFERENCES speciality (id)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS message (
  average_grade DOUBLE NOT NULL,
  message_read BIT(1) NULL,
  user_id BIGINT(20) NOT NULL,
  entered BIT(1) NULL,
  PRIMARY KEY (user_id),
  CONSTRAINT FK70bv6o4exfe3fbrho7nuotopf
    FOREIGN KEY (user_id)
    REFERENCES usr (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS subject_grade ;

CREATE TABLE IF NOT EXISTS subject_grade (
  grade INT(11) NOT NULL,
  subject_id BIGINT(20) NOT NULL,
  user_id BIGINT(20) NOT NULL,
  PRIMARY KEY (subject_id, user_id),
  INDEX FK947k4ym625vhlb4afylpuul8i (user_id ASC),
  CONSTRAINT FK7ndcktiy0re9epm98or2yu0f9
    FOREIGN KEY (subject_id)
    REFERENCES subject (id),
  CONSTRAINT FK947k4ym625vhlb4afylpuul8i
    FOREIGN KEY (user_id)
    REFERENCES usr (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS user_role ;

CREATE TABLE IF NOT EXISTS user_role (
  user_id BIGINT(20) NOT NULL,
  role_name VARCHAR(255) NOT NULL,
  INDEX FKfpm8swft53ulq2hl11yplpr5 (user_id ASC),
  CONSTRAINT FKfpm8swft53ulq2hl11yplpr5
    FOREIGN KEY (user_id)
    REFERENCES usr (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


START TRANSACTION;
USE test_admission_servlets;
INSERT INTO speciality (id, name) VALUES (1, 'Хімік');
INSERT INTO speciality (id, name) VALUES (2, 'Археолог');
INSERT INTO speciality (id, name) VALUES (3, 'Слюсар');
INSERT INTO speciality (id, name) VALUES (4, 'Ботанік');
INSERT INTO speciality (id, name) VALUES (5, 'Філософ');

COMMIT;


START TRANSACTION;
USE test_admission_servlets;
INSERT INTO subject (id, name) VALUES (1, 'Математика');
INSERT INTO subject (id, name) VALUES (2, 'Українська мова');
INSERT INTO subject (id, name) VALUES (3, 'Біологія');
INSERT INTO subject (id, name) VALUES (4, 'Хімія');
INSERT INTO subject (id, name) VALUES (5, 'Образотворче мистецтво');
INSERT INTO subject (id, name) VALUES (6, 'Географія');
INSERT INTO subject (id, name) VALUES (7, 'Фізика');
INSERT INTO subject (id, name) VALUES (8, 'Технології');
INSERT INTO subject (id, name) VALUES (9, 'Інформатика');
INSERT INTO subject (id, name) VALUES (10, 'Графіка');

COMMIT;


START TRANSACTION;
USE test_admission_servlets;
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (1, 1);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (1, 4);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (1, 7);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (2, 2);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (2, 3);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (2, 4);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (2, 10);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (3, 1);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (3, 3);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (3, 5);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (4, 6);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (4, 10);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (4, 4);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (4, 1);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (5, 8);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (5, 7);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (5, 3);
INSERT INTO speciality_subject (speciality_id, subject_id) VALUES (5, 2);

COMMIT;


START TRANSACTION;
USE test_admission_servlets;
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (1, 'serhii.bobkov@ua', 'Сергій', 'Бобков', '0cc175b9c0f1b6a831c399e269772661', 'a', NULL);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (2, 'iryna.zaychenko@ua', 'Ірина', 'Зайченко', 'fbade9e36a3f36d3d676c1b808451dd7', 'z', 2);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (3, 'kateryna.osadcha@ua', 'Катерина', 'Осадча', '9dd4e461268c8034f5c8564e155c67a6', 'x', 3);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (4, 'alla.shurova@ua', 'Алла', 'Шурова', '7694f4a66316e53c8cdd9d9954bd611d', 'q', 1);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (5, 'nataliya.tarasova@ua', 'Наталія', 'Тарасова', '03c7c0ace395d80182db07ae2c30f034', 's', NULL);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (6, 'serhii.zhdanov@ua', 'Сергій', 'Жданов', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'w', 4);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (7, 'oleksii.morenets@cmail.com', 'Олексій', 'Моренець', '21232f297a57a5a743894a0e4a801fc3', 'admin', NULL);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (8, 'dmytro.gordiychuk@ua', 'Дмитро', 'Гордійчук', 'ee11cbb19052e40b07aac0ca060c23ee', 'user', NULL);
INSERT INTO usr (id, email, first_name, last_name, password, username, speciality_id) VALUES (9, 'pavlo.polyakov@ua', 'Павло', 'Поляков', 'cd73502828457d15655bbd7a63fb0bc8', 'student', NULL);

COMMIT;


START TRANSACTION;
USE test_admission_servlets;
INSERT INTO subject_grade (grade, subject_id, user_id) VALUES (70, 2, 2);
INSERT INTO subject_grade (grade, subject_id, user_id) VALUES (85, 3, 2);
INSERT INTO subject_grade (grade, subject_id, user_id) VALUES (90, 10, 2);
INSERT INTO subject_grade (grade, subject_id, user_id) VALUES (100, 1, 3);
INSERT INTO subject_grade (grade, subject_id, user_id) VALUES (65, 5, 3);

COMMIT;


START TRANSACTION;
USE test_admission_servlets;
INSERT INTO user_role (user_id, role_name) VALUES (1, 'ADMIN');
INSERT INTO user_role (user_id, role_name) VALUES (2, 'USER');
INSERT INTO user_role (user_id, role_name) VALUES (3, 'USER');
INSERT INTO user_role (user_id, role_name) VALUES (4, 'USER');
INSERT INTO user_role (user_id, role_name) VALUES (5, 'USER');
INSERT INTO user_role (user_id, role_name) VALUES (6, 'USER');
INSERT INTO user_role (user_id, role_name) VALUES (7, 'ADMIN');
INSERT INTO user_role (user_id, role_name) VALUES (8, 'USER');
INSERT INTO user_role (user_id, role_name) VALUES (9, 'USER');

COMMIT;
