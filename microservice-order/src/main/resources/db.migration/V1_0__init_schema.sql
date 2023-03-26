CREATE TABLE products
(
    id          BIGSERIAL      NOT NULL,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(255)   NOT NULL,
    price       decimal(10, 2) NOT NULL,
    created     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE customers
(
    id      BIGSERIAL    NOT NULL,
    name    VARCHAR(255) NOT NULL,
    email   VARCHAR(255) NOT NULL,
    created TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);


CREATE TABLE organizations
(
    id      BIGSERIAL    NOT NULL,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    created TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT organization_pkey PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id              BIGSERIAL   NOT NULL,
    customer_id     BIGINT      NOT NULL,
    organization_id BIGINT      NOT NULL,
    status          VARCHAR(20) NOT NULL DEFAULT 'NOT_COMPLETED',
    created         TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated         TIMESTAMP   NOT null DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);
CREATE TABLE order_customers
(
    order_id    bigint NOT NULL,
    customer_id bigint NOT NULL,
    CONSTRAINT order_customers_pkey PRIMARY KEY (order_id, customer_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);
CREATE TABLE order_items
(
    id         BIGSERIAL NOT NULL,
    product_id bigint    NOT NULL,
    quantity   integer   NOT NULL,
    order_id   bigint    NOT NULL,
    CONSTRAINT orderItem_pkey PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE TABLE order_products
(
    order_id   bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT order_products_pkey PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);