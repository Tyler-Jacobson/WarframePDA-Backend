DELETE
FROM items;

DELETE
FROM parts;

DELETE
FROM orders;

DELETE
FROM sellers;

DELETE
FROM orderssellers;

INSERT INTO items (itemid, itemname, imageurl)
    VALUES (1, 'Equinox', 'www.url.com'),
           (2, 'Saryn', 'www.url.com'),
           (3, 'Nezha', 'www.url.com');

INSERT INTO parts (partid, partname, itemid)
    VALUES (4, 'Set', 1),
           (5, 'Chassis', 1),
           (6, 'Neuroptics', 1),
           (7, 'Systems', 1),
           (8, 'Blueprint', 1),
           (9, 'Set', 2),
           (10, 'Chassis', 2),
           (11, 'Neuroptics', 2),
           (12, 'Systems', 2),
           (13, 'Blueprint', 3),
           (14, 'Set', 3),
           (15, 'Chassis', 3),
           (16, 'Neuroptics', 3),
           (17, 'Systems', 3),
           (18, 'Blueprint', 3);

INSERT INTO orders (orderid, price, partid)
    VALUES (20, 12, 4),
           (21, 16, 5),
           (22, 19, 4),
           (23, 13, 10),
           (24, 122, 17),
           (25, 20, 18);

INSERT INTO sellers (sellerid, sellername)
    VALUES (30, 'Timmy'),
           (31, 'Tromba'),
           (32, 'Taco');

INSERT INTO orderssellers
    VALUES (20, 30),
           (21, 31),
           (22, 32),
           (23, 30),
           (24, 30),
           (25, 32);


alter sequence hibernate_sequence restart with 50;