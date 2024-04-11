insert into seller (name, email, passport) values
 ('Кабан Кабаныч', 'vinxgradxv@yandex.ru', 0000123456),
 ('Любовь Львовна', 'trofimchenko.vladtrof@yandex.ru', 0000123456);

insert into client (name, email, age, address) values
    ('Виноградов Глеб Дмитриевич', 'boba@mail.com',69, 'Белорусская, 6'),
    ('Трофимченко Владислав Михайлович', 'biba@mail.com', 228, 'Вяземский переулок');

insert into product(name, count, price, category, size, seller_id) values
    ('car', 3, 1200, 'car', 120, 1);

insert into cart(product_id, client_id, count) values
    (1, 1, 2);