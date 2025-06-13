-- PostgreSQL��
-- psql -U postgres
-- �Őڑ�

-- \i todo.sql
-- �����s

\c postgres
DROP DATABASE IF EXISTS team_dev_pisuta_shop;
DROP ROLE IF EXISTS student;
CREATE ROLE student WITH PASSWORD 'himitu' LOGIN;
CREATE DATABASE team_dev_pisuta_shop OWNER student ENCODING 'UTF8';

/* DB�� */
\c team_dev_pisuta_shop

DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS conditions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS deals;

CREATE TABLE categories
(
id  SERIAL      PRIMARY KEY,
name    VARCHAR(50) NOT NULL
);

CREATE TABLE conditions
(
id  SERIAL      PRIMARY KEY,
name    VARCHAR(50) NOT NULL
);

-- ���[�U�[�e�[�u��
CREATE TABLE users
(
id      SERIAL      NOT NULL PRIMARY KEY,
name        VARCHAR(60) NOT NULL,
address VARCHAR(150)    NOT NULL,
tel     VARCHAR(11) NOT NULL,
email       VARCHAR(250)    NOT NULL,
birthday    DATE        NOT NULL,
password    VARCHAR(16) NOT NULL,
UNIQUE(email)
);

CREATE TABLE items
(
id      SERIAL      PRIMARY KEY,
category_id INTEGER     NOT NULL REFERENCES categories(id),
seller_id   INTEGER     NOT NULL REFERENCES users(id),
name        VARCHAR(100)    NOT NULL,
price       INTEGER     NOT NULL,
cond_id INTEGER     NOT NULL REFERENCES conditions(id),
status  BOOLEAN     NOT NULL DEFAULT TRUE,
comment TEXT
);

CREATE TABLE deals
(
id      SERIAL  PRIMARY KEY,
item_id INTEGER NOT NULL REFERENCES items(id),
buyer_id    INTEGER NOT NULL REFERENCES users(id)
);

ALTER TABLE users OWNER TO student;
ALTER TABLE categories OWNER TO student;
ALTER TABLE conditions OWNER TO student;
ALTER TABLE items OWNER TO student;
ALTER TABLE deals OWNER TO student;
ALTER TABLE team_dev_pisuta_shop OWNER TO student;