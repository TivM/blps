create table if not exists seller(
    id serial primary key,
    name varchar not null,
    email varchar not null,
    passport int not null
);

create table if not exists product (
    id serial primary key,
    name varchar not null,
    count int not null,
    price int not null,
    category varchar not null,
    size int,
    seller_id int not null references seller(id)
);

create table if not exists client (
    id serial primary key,
    name varchar not null,
    email varchar not null,
    age int not null,
    address varchar not null
);

create table if not exists cart(
    product_id int not null references product(id),
    client_id int not null references client(id),
    count int not null,

    primary key (product_id, client_id)
);

create table if not exists product_order(
    id serial primary key,
    pickup_point_address varchar not null,
    delivery_time int,
    cost_of_delivery int,
    client_id int not null references client(id)
);

create table if not exists ordered_item(
    id serial primary key,
    count int not null,
    product_order_id int not null references product_order(id),
    product_id int not null references product(id),
    status varchar not null
);

create table if not exists payment(
    id serial primary key,
    receive int not null,
    change int not null,
    processor varchar not null,
    product_order_id int not null references product_order(id)
);

create table if not exists pickup_point_employee(
    id serial primary key,
    name varchar not null,
    email varchar not null,
    passport int not null
);

create table if not exists _user(
    id serial primary key,
    email varchar not null unique,
    password varchar not null,
    role varchar check (role in ('SELLER','CLIENT','PICKUP_POINT_EMPLOYEE'))
)

