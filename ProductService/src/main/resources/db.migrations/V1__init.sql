CREATE TABLE category
(
    id   BIGINT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_mentor
(
    id     BIGINT NOT NULL,
    rating INT    NOT NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (id)
);

CREATE TABLE jt_student
(
    id    BIGINT NOT NULL,
    psp   INT    NOT NULL,
    batch VARCHAR(255) NULL,
    CONSTRAINT pk_jt_student PRIMARY KEY (id)
);

CREATE TABLE jt_user
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE mentor
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    rating   INT    NOT NULL,
    CONSTRAINT pk_mentor PRIMARY KEY (id)
);

CREATE TABLE mentor_tpc
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    rating   INT    NOT NULL,
    CONSTRAINT pk_mentor_tpc PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price         BIGINT NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id        BIGINT NOT NULL,
    user_type INT NULL,
    email     VARCHAR(255) NULL,
    password  VARCHAR(255) NULL,
    rating    INT    NOT NULL,
    psp       INT    NOT NULL,
    batch     VARCHAR(255) NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE student
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    psp      INT    NOT NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE student_tpc
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    psp      INT    NOT NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_student_tpc PRIMARY KEY (id)
);

CREATE TABLE user_tpc
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_user_tpc PRIMARY KEY (id)
);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE jt_student
    ADD CONSTRAINT FK_JT_STUDENT_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);