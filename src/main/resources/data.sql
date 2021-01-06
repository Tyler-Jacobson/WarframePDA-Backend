DELETE
FROM items;

DELETE
FROM parts;

DELETE
FROM orders;

DELETE
FROM sellers;

INSERT INTO items (itemid, itemname, imageurl)
    VALUES (1, 'Equinox', 'www.url.com'),
           (2, 'Saryn', 'www.url.com'),
           (3, 'Nezha', 'www.url.com');

INSERT INTO parts (partid, partname, itemid)
    VALUES (4, 'Systems', 1),
           (5, 'Chassis', 1),
           (6, 'Blueprint', 1),
           (7, 'Neuroptics', 1),
           (8, 'Set', 1),
           (9, 'Chassis', 2),
           (10, 'Blueprint', 2),
           (11, 'Neuroptics', 2),
           (12, 'Set', 2),
           (13, 'Blueprint', 3),
           (14, 'Neuroptics', 3),
           (15, 'Systems', 3),
           (16, 'Set', 3);

INSERT INTO sellers (sellerid, sellername)
    VALUES (30, 'Timmy'),
           (31, 'Tromba'),
           (32, 'Taco');

INSERT INTO orders (orderid, price, partid, sellerid)
    VALUES (20, 12, 4, 30),
           (21, 16, 5, 32),
           (22, 19, 4, 31),
           (23, 13, 10, 30),
           (24, 122, 16, 32),
           (25, 20, 16, 30);




alter sequence hibernate_sequence restart with 70;