CREATE TABLE organizations
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    logoUrl     VARCHAR(255),
    status      VARCHAR(255) NOT NULL,
    createdAt   TIMESTAMP    NOT NULL,
    updatedAt   TIMESTAMP    NOT NULL
);

CREATE TABLE productDtos
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255)   NOT NULL,
    description     TEXT,
    price           NUMERIC(19, 2) NOT NULL,
    organization_id BIGINT         NOT NULL,
    status          VARCHAR(255)   NOT NULL,
    createdAt       TIMESTAMP      NOT NULL,
    updatedAt       TIMESTAMP      NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations (id)
);