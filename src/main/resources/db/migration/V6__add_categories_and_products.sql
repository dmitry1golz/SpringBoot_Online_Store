CREATE TABLE categories
(
    id   TINYINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    category_id TINYINT,
    constraint fk_category
        foreign key (category_id) references categories (id)
            on delete restrict
);

