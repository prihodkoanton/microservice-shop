CREATE TABLE organizations
(
    id                 SERIAL PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    description        TEXT,
    logoUrl            VARCHAR(255),
    organizationStatus VARCHAR(255) NOT NULL,
    createdAt          TIMESTAMP    NOT NULL,
    updatedAt          TIMESTAMP    NOT NULL
);

CREATE TABLE products
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255)   NOT NULL,
    description     TEXT,
    price           NUMERIC(19, 2) NOT NULL,
    organization_id BIGINT         NOT NULL,
    productStatus   VARCHAR(255)   NOT NULL,
    createdAt       TIMESTAMP      NOT NULL,
    updatedAt       TIMESTAMP      NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations (id)
);