DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    category_id INT NOT NULL,
    supplier_id INT NOT NULL
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
    status VARCHAR(32)
);

DROP TABLE IF EXISTS order_items;

CREATE TABLE order_items (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    picked INT NOT NULL,
    shipped INT NOT NULL
);

