    CREATE DATABASE IF NOT EXISTS target;
    USE target;

    CREATE TABLE IF NOT EXISTS roles(
                               id int primary key auto_increment,
                               name varchar(20)
    );

    CREATE TABLE IF NOT EXISTS users(
                               id bigint primary key auto_increment,
                               email varchar(50) not null unique ,
                               password varchar(120) not null,
                               username varchar(20) not null unique
    );

    CREATE TABLE IF NOT EXISTS user_roles(
                               user_id bigint not null ,
                               role_id int not null
    );

    CREATE TABLE IF NOT EXISTS category(
                               id int primary key auto_increment,
                               name varchar(255) not null
    );

    CREATE TABLE IF NOT EXISTS product(
                               id int primary key auto_increment,
                               name varchar(255) not null,
                               price float
    );

    CREATE TABLE IF NOT EXISTS product_category(
                               id_category int,
                               id_product int
    );

    CREATE TABLE IF NOT EXISTS partner(
                               id int primary key auto_increment,
                               name varchar(255) not null
    );

    CREATE TABLE IF NOT EXISTS coupon(
                               id int primary key auto_increment,
                               name varchar(255) not null,
                               discount float,
                               partner_id int
    );

    CREATE TABLE IF NOT EXISTS purchase_order(
                               id int primary key auto_increment,
                               date timestamp,
                               coupon_id int,
                               discount float,
                               total float,
                               total_payment float
    );

    CREATE TABLE IF NOT EXISTS purchase_order_item(
                               purchase_order_id int,
                               product_id int,
                               quantity int
    );


    INSERT INTO roles (name) values ('ROLE_ADMIN'), ('ROLE_USER');

    INSERT INTO category (name) values ('Camisas');
    INSERT INTO category (name) values ('Acessorios');
    INSERT INTO category (name) values ('Carteiras');
    INSERT INTO category (name) values ('Bolsas');

    INSERT INTO product (name, price) values ('Camisa Tommy Masculina G', 159.99);
    INSERT INTO product (name, price) values ('Carteira Tommy Masculina ', 119.99);
    INSERT INTO product (name, price) values ('Bolsa Gucci preta', 1959.99);

    INSERT INTO product_category values (1,1);
    INSERT INTO product_category values (2,2);
    INSERT INTO product_category values (3,2);
    INSERT INTO product_category values (3,3);
    INSERT INTO product_category values (4,3);


    INSERT INTO partner (name) values ('Canal Moda Vida');

    INSERT INTO coupon (name, discount, partner_id ) values ('LIQUIDA TUDO', 7.50, 1);
