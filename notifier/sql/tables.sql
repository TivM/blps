create table if not exists notification(
    id serial primary key,
    status varchar not null,
    client_email varchar not null,
    seller_email varchar not null,
    order_id int not null,
    address varchar not null
);