CREATE SCHEMA IF NOT EXISTS pizzeria;

DROP TABLE IF EXISTS pizzeria.pizza_info CASCADE;
DROP TABLE IF EXISTS pizzeria.menu_row CASCADE;
DROP TABLE IF EXISTS pizzeria.menu CASCADE;
DROP TABLE IF EXISTS pizzeria.menu_menu_row CASCADE;
DROP TABLE IF EXISTS pizzeria.stage CASCADE;

CREATE TABLE IF NOT EXISTS public.pizza_info
(
    id SERIAL,
    name character varying,
    description character varying,
    size bigint,
    creation_date timestamp without time zone,
    update_date timestamp without time zone,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.menu_row
(
    id SERIAL,
    pizza_info_id bigint,
    price         double precision,
    creation_date timestamp without time zone,
    update_date   timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (pizza_info_id) REFERENCES pizza_info ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS public.menu
(
    id SERIAL,
    name          character varying,
    enabled       boolean,
    creation_date timestamp without time zone,
    update_date   timestamp without time zone,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.menu_menu_row
(
    menu_id     bigint,
    menu_row_id bigint,
    FOREIGN KEY (menu_id) REFERENCES menu ON DELETE CASCADE,
    FOREIGN KEY (menu_row_id) REFERENCES menu_row ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.stage
(
    id      SERIAL,
    description character varying,
    creation_date timestamp without time zone,
    update_date timestamp without time zone,
    PRIMARY KEY (id)
);