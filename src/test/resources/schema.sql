CREATE SCHEMA IF NOT EXISTS pizzeria;

DROP TABLE IF EXISTS pizzeria.pizza_info CASCADE;
DROP TABLE IF EXISTS pizzeria.menu_row CASCADE;
DROP TABLE IF EXISTS pizzeria.menu CASCADE;
DROP TABLE IF EXISTS pizzeria.menu_menu_row CASCADE;
DROP TABLE IF EXISTS pizzeria.stage CASCADE;
DROP TABLE IF EXISTS pizzeria.orders CASCADE;
DROP TABLE IF EXISTS pizzeria.selected_item CASCADE;
DROP TABLE IF EXISTS pizzeria.ticket CASCADE;
DROP TABLE IF EXISTS pizzeria.order_status CASCADE;
DROP TABLE IF EXISTS pizzeria.stage_order_status CASCADE;
DROP TABLE IF EXISTS pizzeria.done_order CASCADE;
DROP TABLE IF EXISTS pizzeria.pizza CASCADE;


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

CREATE TABLE IF NOT EXISTS public.orders
(
    id  SERIAL,
    creation_date timestamp without time zone,
    update_date   timestamp without time zone,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.selected_item
(
    id    SERIAL,
    menu_row_id   bigint,
    count         bigint,
    orders_id     bigint,
    creation_date timestamp without time zone,
    update_date   timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (menu_row_id) REFERENCES menu_row ON DELETE SET NULL,
    FOREIGN KEY (orders_id) REFERENCES orders ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS public.ticket
(
    id     SERIAL,
    orders_id     bigint,
    creation_date timestamp without time zone,
    update_date   timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (orders_id) REFERENCES orders ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS public.order_status
(
    id       SERIAL,
    ticket_id     bigint,
    done          boolean,
    creation_date timestamp without time zone,
    update_date   timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (ticket_id) REFERENCES ticket ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS public.stage_order_status
(
    stage_id        bigint,
    order_status_id bigint,
    FOREIGN KEY (stage_id) REFERENCES stage ON DELETE CASCADE,
    FOREIGN KEY (order_status_id) REFERENCES order_status ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS public.done_order
(
    id      SERIAL,
    ticket_id   bigint,
    creation_date timestamp without time zone,
    update_date timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (ticket_id) REFERENCES ticket ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS public.pizza
(
    id     SERIAL,
    name         character varying,
    size          bigint,
    creation_date timestamp without time zone,
    update_date   timestamp without time zone,
    done_order_id  bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (done_order_id) REFERENCES done_order ON DELETE SET NULL
);

