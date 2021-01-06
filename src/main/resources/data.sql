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

INSERT INTO partdetails (partdetailid, partname)
    VALUES (35, 'Systems'),
           (36, 'Chassis'),
           (37, 'Blueprint'),
           (38, 'Neuroptics'),
           (39, 'Set');

INSERT INTO parts (partid, itemid, partdetailid)
    VALUES (4, 1, 35),
           (5, 1, 36),
           (6, 1, 37),
           (7, 1, 38),
           (8, 1, 39),
           (9, 2, 36),
           (10, 2, 37),
           (11, 2, 38),
           (12, 2, 39),
           (13, 3, 37),
           (14, 3, 38),
           (15, 3, 35),
           (16, 3, 39);

INSERT INTO orders (orderid, price, partid)
    VALUES (20, 12, 4),
           (21, 16, 5),
           (22, 19, 4),
           (23, 13, 10),
           (24, 122, 16),
           (25, 20, 16);

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


alter sequence hibernate_sequence restart with 70;