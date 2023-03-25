CREATE TABLE users
(
    id       BIGSERIAL    NOT NULL,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance  DECIMAL(10, 2) DEFAULT 0,
    role     VARCHAR(20)  NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);