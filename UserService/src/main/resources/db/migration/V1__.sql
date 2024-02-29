CREATE TABLE `role`
(
    id        BIGINT NOT NULL,
    role_name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE token
(
    id          BIGINT NOT NULL,
    token       VARCHAR(255) NULL,
    user_id     BIGINT NULL,
    expiry_date datetime NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                BIGINT NOT NULL,
    name              VARCHAR(255) NULL,
    password          VARCHAR(255) NULL,
    email             VARCHAR(255) NULL,
    is_email_verified BIT(1) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);