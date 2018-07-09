DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    category_id INT NOT NULL,
    supplier_id INT NOT NULL,
    filename VARCHAR(128),
    content_type VARCHAR(128),
    content blob
);

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

DROP TABLE IF EXISTS suppliers;

CREATE TABLE suppliers (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(32),
    last_name VARCHAR (32),
    status VARCHAR(32),
    email VARCHAR(64)
);

DROP TABLE IF EXISTS order_items;

CREATE TABLE order_items (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    picked INT NOT NULL DEFAULT 0,
    shipped INT NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(32) UNIQUE,
    password VARCHAR(512),
    department VARCHAR(32)
);

DROP TABLE IF EXISTS department_status;

CREATE TABLE department_status (
    department VARCHAR(64) NOT NULL,
    status VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS ship_methods;

CREATE TABLE ship_methods (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ship_method VARCHAR(64) NOT NULL
);

DROP TABLE IF EXISTS ship_details;

CREATE TABLE ship_details (
    order_id INT NOT NULL,
    ship_method VARCHAR(64) NOT NULL,
    tracking VARCHAR(64) NOT NULL
);