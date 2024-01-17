-- drop database onlinemarketplace;
CREATE DATABASE onlinemarketplace;
USE onlinemarketplace;


-- creating supplier table
CREATE TABLE supplier(
	supplierId varchar(20) NOT NULL,
    supplierName varchar(50) NOT NULL,
    contactNo varchar(30) NOT NULL,
    address varchar(150) NOT NULL,
    primary key (supplierId)
);


insert into supplier(supplierId,supplierName,contactNo,address) values('S1001','ABCElectronics','(123) 456-7890','New York');
insert into supplier values  ('S2002', 'XYZPharmaceuticals', '(555) 123-4567', 'California');
insert into supplier values   ('S3003', 'Smith&SonsHardware', '(789) 234-5678', 'Chicago');
insert into supplier values   ('S4004', 'FreshFarmsProduce', '(987) 654-3210', 'Florida');
insert into supplier values   ('S5005', 'JohnsonAutoParts', '(333) 999-8888', 'Texas');
insert into supplier values   ('S5006', 'ABFlower', '(333) 999-8888', 'Texas');
insert into supplier values   ('S5007', 'MLGroup', '(323) 999-6888', 'Mumbai');
insert into supplier values   ('S5008', 'EAPGroup', '(323) 979-6988', 'Mumbai');

UPDATE supplier
SET contactNo = '(333) 222-4444'
WHERE supplierId ='S5005';

UPDATE supplier
SET supplierName = 'CATElectronics' 
WHERE supplierId = 'S5008';

DELETE from supplier
where supplierId = 'S5008';

-- Creating product table
CREATE TABLE product(
	productId varchar(20) NOT NULL,
    supplierId varchar(20),
    productName varchar(50) NOT NULL,
    price decimal(10,2) NOT NULL,
    primary key (productId),
    constraint FK_products_supplier 
    foreign key (supplierId) references supplier(supplierId)
);


INSERT INTO Product (ProductId, SupplierId, ProductName, Price)
VALUES ('P1001', 'S1001', 'Smartphone', 599.99),
       ('P1002', 'S2002', 'Plaster', 999.99),
       ('P1003', 'S3003', 'Nails', 299.99),
       ('P1004', 'S4004', 'Meats', 79.99),
       ('P1005', 'S5005', 'Tube', 499.99),
       ('P1006', 'S5006', 'Rose', 199.99),
       ('P1007', 'S5007', 'Car', 11499.99),
       ('P1008', 'S3003', 'Tube', 23.99),
       ('P1009', 'S3003', 'Shampoo', 23.99);
       

       
UPDATE Product
SET ProductName= 'Tube'
WHERE ProductId ='P1005';

UPDATE Product
SET supplierId = 'S4004' 
WHERE supplierId = 'S3003';

DELETE from Product
where ProductId = 'P1009';

-- Creating customer table
CREATE TABLE customer (
	customerId VARCHAR(30) NOT NULL,
	firstName VARCHAR(150) NOT NULL,
	lastName VARCHAR(150) NOT NULL,
	country VARCHAR(30) NOT NULL,	
	PRIMARY KEY (customerId)
);


INSERT INTO customer (customerId, firstName, lastName, country)
VALUES
    ('1', 'John', 'Smith', 'United States'),
    ('2', 'Alice', 'Johnson', 'Canada'),
    ('3', 'Michael', 'Brown', 'United Kingdom'),
    ('4', 'Emma', 'Williams', 'Australia'),
    ('5', 'Daniel', 'Martinez', 'Spain'),
    ('6', 'Sophia', 'Lee', 'South Korea'),
    ('7', 'Liam', 'Garcia', 'Mexico'),
    ('8', 'Olivia', 'Chen', 'China'),
    ('9', 'Ethan', 'Wang', 'Taiwan');

UPDATE customer
SET firstName = 'Irina'
WHERE customerId = '8';

UPDATE customer
SET country = 'Lithuvania' 
WHERE customerId = '9';

DELETE from customer
where customerId = '9';


-- Creating customer contact table
CREATE TABLE customerContact(
	customerId VARCHAR(30) NOT NULL,
    contactNo VARCHAR(20) NOT NULL,
    PRIMARY KEY (customerId, contactNo),
    foreign key (customerId) references customer(customerId)
);

INSERT INTO customerContact (customerId, contactNo)
VALUES
    ('1', '123-456-7890'),
    ('2', '987-654-3210'),
    ('3', '555-123-4567'),
    ('4', '888-555-9999'),
	('5', '777-111-2222'),
    ('4', '555-777-8888'),
    ('7', '999-333-4444'),
    ('8', '934-333-4444');
    
    
UPDATE customerContact
SET contactNo= '999-333-4444'
WHERE customerId ='2';

UPDATE customerContact
SET contactNo = '455-727-8342' 
WHERE customerId = '3';

DELETE from customerContact
where customerId = '8';

-- creating reviews table
CREATE TABLE reviews (
	reviewId VARCHAR(30) NOT NULL,
	customerId VARCHAR(30),
	productId VARCHAR(20),
	rating DECIMAL(3, 1) NOT NULL,
	review VARCHAR(300) NOT NULL,
	PRIMARY KEY (reviewId),
	constraint FK_reviews foreign key(productId) references product(productId) on delete set null on update cascade,
	constraint FK_reviews_gives foreign key(customerId) references customer(customerId) on delete set null on update cascade
);

INSERT INTO reviews (reviewId, customerId, productId, rating, review)
VALUES
    (1011, 1, 'P1001', 4.5, 'Good'),
    (2011, 2, 'P1002', 3.0, 'Average'),
    (3011, 3, 'P1003', 5.0, 'Recommended'),
    (4011, 4, 'P1004', 4.0, 'Good'),
    (5011, 3, 'P1005', 2.5, 'NotSatisfied'),
    (6011, 6, 'P1006', 4.0, 'Good'),
    (7011, 7, 'P1007', 2.5,'Bad'),
    (8011, 7, 'P1003', 1.5,'Bad'),
    (9011, 2, 'P1001', 4.5,'Good');
 
UPDATE reviews
SET rating = '2.0'
WHERE reviewId ='5011';

UPDATE reviews
SET review = 'Good product' 
WHERE reviewId = '4011';

DELETE from reviews
where reviewId = '9011';

-- Creating shopping cart table
CREATE TABLE shoppingCart (
	cartId VARCHAR(30) NOT NULL,
    createDate DATE NOT NULL,
    customerId VARCHAR(30) NOT NULL,
    purcheseStatus bool NOT NULL,
    discountAmount DECIMAL(10,2),
	PRIMARY KEY (cartId),
    constraint FK_cusId foreign key(customerId) references customer(customerId)
);


INSERT INTO shoppingCart (cartId, createDate, customerId, purcheseStatus, discountAmount) 
VALUES ('Cart001', '2023-08-08', 3,TRUE, 25.50),
('Cart002', '2023-07-05', 3,FALSE, NULL),
('Cart003', '2023-09-10', 2,TRUE, 10.00),
('Cart004', '2023-06-10', 1,TRUE, 5.25),
('Cart005', '2023-05-11', 5,FALSE, NULL),
('Cart006', '2023-09-06', 4,TRUE, 15.75),
('Cart007', '2023-09-12', 4,TRUE, 7.50),
('Cart008', '2023-08-22', 1,FALSE, 6.50),
('Cart009', '2023-03-12', 2,TRUE, NULL);

UPDATE shoppingCart
SET discountAmount= 1.5
WHERE cartId ='Cart009';

UPDATE shoppingCart
SET purcheseStatus =TRUE
WHERE cartId = 'Cart008';

DELETE from shoppingCart
where cartId = 'Cart009';

-- Creating product_shoppingCart table
create table product_shoppingCart(
	productId VARCHAR(20) NOT NULL,
	cartId VARCHAR(30) NOT NULL,
	primary key(productId,cartId),
	constraint FK_products foreign key (productId) references product(productId),
	constraint FK_cart foreign key (cartId) references shoppingCart(cartId)
);

INSERT INTO product_shoppingCart (productId, cartId)
VALUES
    ('P1001', 'Cart001'),
    ('P1002', 'Cart002'),
    ('P1003', 'Cart003'),
    ('P1004', 'Cart004'),
    ('P1005', 'Cart005'),
    ('P1006', 'Cart006'),
    ('P1007', 'Cart007');

-- Creating address table
CREATE TABLE address(
	addressId VARCHAR(150) NOT NULL,
	customerId varchar(30) NOT NULL,
    state VARCHAR(40) NOT NULL,
	city VARCHAR(40) NOT NULL,
	street VARCHAR(40) NOT NULL,
	zip VARCHAR(30) NOT NULL,
	PRIMARY KEY (addressId),
    constraint FK_customer_address foreign key (customerId) references customer(customerId)
);


INSERT INTO address (addressId, customerId, state, city, street, zip)
VALUES (11111, 1, 'Male01', 'Male', '123MainSt', '10001'),
(33333, 3, 'Delhi', 'NewDelhi', '456ElmSt', '30003'),
(44444, 4, 'NSW', 'Sydney', '789OakAve', '40004'),
(22222, 2, 'ON', 'Ottwa', '567PineRd', '20002'),
(55555, 5, 'Berlin05', 'Berlin', '890BirchLn', '50005'),
(66666, 6, 'Paris06', 'Paris', '101CedarRd', '60006'),
(77777, 7, 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),

(88888, 7, 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),
(99999, 7, 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),
(99991, 2, 'Bermingham', 'London', '222RedwoodAve', '70008'),
(99992, 1, 'North-West', 'Ruswells', '222RedwoodAve', '70009');

UPDATE address
SET state = 'Vesex'
WHERE addressId ='11111';

UPDATE address
SET zip = '70008' 
WHERE addressId = '66666';

DELETE from address
where addressId = '99992';

-- Creating shipper table
CREATE TABLE shipper(
	shipperId VARCHAR(30) NOT NULL,
	shipperName VARCHAR(100) NOT NULL,
	contactNo VARCHAR(20) NOT NULL,
	PRIMARY KEY (shipperId)
);

INSERT INTO shipper (shipperId, shipperName, contactNo)
VALUES
    ('001', 'Shipper1', '123-456-7780'),
    ('002', 'Shipper2', '987-654-3210'),
    ('003', 'Shipper3', '555-123-4567'),
    ('004', 'Shipper4', '999-888-7777'),
    ('005', 'Shipper5', '111-222-3333'),
    ('006', 'Shipper6', '444-555-6666'),
    ('007', 'Shipper7', '777-888-9999'),
	('008', 'Shipper8', '733-888-9999'),
	('009', 'Shipper9', '777-338-9344');

UPDATE shipper
SET contactNo = '111-222-3324'
WHERE shipperId ='009';

UPDATE shipper
SET shipperName = 'Shipper9' 
WHERE shipperId = '008';

DELETE from shipper
where shipperId = '009';

-- Creating shipment table
CREATE TABLE shipments (
	shippingId VARCHAR(30) NOT NULL,
	shipperId VARCHAR(30),
	addressId VARCHAR(150) NOT NULL,
	shipmentStatus VARCHAR(150) NOT NULL,
	shipmentDate DATE NOT NULL,
	PRIMARY KEY (shippingId,addressId),
	constraint FK_shipments foreign key (addressId) references address(addressId),
	constraint FK_shipments_shipper foreign key (shipperId) references shipper(shipperId) on delete set null on update cascade
);


INSERT INTO shipments (shippingId, shipperId, addressId, shipmentStatus, shipmentDate)
VALUES
    ('SHIP001', '001', 11111, 'InTransit', '2023-09-10'),
    ('SHIP002', '002', 22222, 'Delivered', '2023-09-11'),
    ('SHIP003', '003', 33333, 'Pending', '2023-09-12'),
    ('SHIP004', '004', 44444, 'InTransit', '2023-09-13'),
    ('SHIP005', '005', 55555, 'Delivered', '2023-09-14'),
    ('SHIP006', '006', 66666, 'Delivered', '2023-09-15'),
	('SHIP007', '007', 77777, 'Delivered', '2023-10-14'),
	('SHIP008', '004', 44444, 'InTransit', '2023-10-24'),
	('SHIP009', '004', 33333, 'Delivered', '2023-10-14');

UPDATE shipments
SET shipmentStatus = 'Delivered'
WHERE shippingId ='SHIP008';

UPDATE shipments
SET addressId = '44444' 
WHERE shippingId = 'SHIP009';

DELETE from shipments
where shippingId = 'SHIP009';

-- Creating orders table
CREATE TABLE orders(
	orderId VARCHAR(30) NOT NULL,
	customerId VARCHAR(30),
	cartId VARCHAR(30),
	shippingId VARCHAR(30),
	orderDate DATE NOT NULL,
	total INT DEFAULT NULL,
	PRIMARY KEY (orderId),
	constraint FK_orders foreign key (customerId) references customer(customerId) on delete set null on update cascade,
	constraint FK_orders_shoppingCart foreign key (cartId) references shoppingCart(cartId) on delete set null on update cascade,
	constraint FK_orders_shipments foreign key (shippingId) references shipments(shippingId) on delete set null on update cascade
);

INSERT INTO orders (orderId, customerId, cartId, shippingId, orderDate, total)
VALUES
    ('ORDER001', 1, 'Cart001', 'SHIP001', '2023-09-10', 250),
    ('ORDER002', 2, 'Cart002', 'SHIP002', '2023-09-11', 150),
    ('ORDER003', 3, 'Cart003', 'SHIP003', '2023-09-12', 300),
    ('ORDER004', 4, 'Cart004', 'SHIP004', '2023-09-13', 175),
    ('ORDER005', 5, 'Cart005', 'SHIP005', '2023-09-14', 200),
    ('ORDER006', 6,'Cart006', 'SHIP006', '2023-09-15', 180),
    ('ORDER007', 7, 'Cart007', 'SHIP007', '2023-09-16', 210),
    ('ORDER009', 2, 'Cart008', 'SHIP005', '2023-09-16', 410),
    ('ORDER010', 4, 'Cart008', 'SHIP006', '2023-09-16', 310);

UPDATE orders
SET orderDate = '2023-08-18'
WHERE orderId ='ORDER010';

UPDATE orders
SET total = '540' 
WHERE orderId = 'ORDER009';

DELETE from orders
where orderId = 'ORDER010';

-- Creating payment table
CREATE TABLE payment (
	paymentNo VARCHAR(30) NOT NULL,
    orderId VARCHAR(30) NOT NULL,
	paymentType VARCHAR(100) NOT NULL,
	paymentDetails VARCHAR(150) NOT NULL,
	PRIMARY KEY (paymentNo,orderId),
    constraint FK_order_payment foreign key (orderId) references orders(orderId)
);

INSERT INTO payment (paymentNo, orderId, paymentType, paymentDetails)
VALUES
    ('PAYMENT001', 'ORDER001', 'CreditCard', 'Card_No:0000-1111-1111-1234-Expiry:12/25'),
    ('PAYMENT002', 'ORDER002', 'PayPal', 'PayPal_Email:abc@gmail.com'),
    ('PAYMENT003', 'ORDER003', 'CreditCard', 'Card_No:1111-0000-2222-5678-Expiry:06/24'),
    ('PAYMENT004', 'ORDER004', 'BankTransfer', 'Account_No:12345678-Bank:XYZBank'),
    ('PAYMENT005', 'ORDER005', 'CashOnDelivery', 'Amount:200USD-Collect_upon_Delivery'),
    ('PAYMENT006', 'ORDER006', 'CreditCard', 'Card_No:1234-0000-3434-7890-Expiry:10/25'),
    ('PAYMENT007', 'ORDER007', 'PayPal', 'PayPal_Email:sam@yahooo.com'),
    ('PAYMENT008', 'ORDER003', 'CreditCard', 'Amount:200USD-Collect_upon_Delivery'),
    ('PAYMENT009', 'ORDER003', 'CreditCard', 'PayPal_Email:sam@yahooo.com');

UPDATE payment
SET paymentType= 'PayPal'
WHERE paymentNo ='PAYMENT008';

UPDATE payment
SET paymentDetails = 'PayPal_Email:abc@gmail.com' 
WHERE paymentNo = 'PAYMENT009';

DELETE from payment
where paymentNo = 'PAYMENT009';

-- Trigers to update total in order when a new item added to the cart
DELIMITER //

CREATE TRIGGER insert_order_total
AFTER INSERT ON product_shoppingCart
FOR EACH ROW
BEGIN
    DECLARE order_total DECIMAL(10, 2);
    
    -- Calculate the total for the given cartId
    SELECT SUM(p.price)
    INTO order_total
    FROM product_shoppingCart scp
    INNER JOIN product p ON scp.productId = p.productId
    WHERE scp.cartId = NEW.cartId;
    
    -- Update the order table with the new total
    UPDATE orders
    SET total = order_total
    WHERE cartId = NEW.cartId;
END;
//


DELIMITER //

CREATE TRIGGER update_order_total
AFTER UPDATE ON product_shoppingCart
FOR EACH ROW
BEGIN
    DECLARE order_total DECIMAL(10, 2);
    
    -- Calculate the total for the given cartId
    SELECT SUM(p.price)
    INTO order_total
    FROM product_shoppingCart scp
    INNER JOIN product p ON scp.productId = p.productId
    WHERE scp.cartId = NEW.cartId;
    
    -- Update the order table with the new total
    UPDATE orders
    SET total = order_total
    WHERE cartId = NEW.cartId;
END;
//
       
       

-- simple quaries

-- 1) Retrieve all the details of products in the 'Electronics' category.
SELECT * FROM product WHERE supplierId = 'S1001';

-- 2) Display the names and contact numbers of all suppliers.

select supplierName,contactNo from supplier;

-- 3) Find all possible combinations of customers and products.

SELECT firstName, productName FROM customer, product;

-- 4) Create a view named 'HighlyRatedProducts' that shows products with a rating greater than 4.0.
CREATE VIEW HighlyRatedProducts AS
SELECT productName, rating FROM product
JOIN reviews ON product.productId = reviews.productId
WHERE rating > 4.0;

SELECT *
FROM HighlyRatedProducts;

-- 5) Rename the 'customerName' column in the 'customer' table to 'FullName'.
-- SELECT firstName AS 'FullName' FROM customer;
ALTER TABLE customer
RENAME COLUMN firstName TO FullName;


ALTER TABLE customer
RENAME COLUMN FullName TO firstName;

-- 6) Calculate the average rating for all the products
SELECT productId, AVG(rating) AS average_rating
FROM reviews
GROUP BY productId;

-- 7) Find customers whose names start with 'A'.
SELECT * FROM customer WHERE firstName LIKE 'A%';


-- complex quaries

-- 1.Union Operation 
SELECT firstName AS name FROM customer
UNION
SELECT supplierName AS name FROM supplier;

-- 2.Intersection Operation 
SELECT firstName AS 'Customer Name'
FROM customer
WHERE customerId IN (SELECT DISTINCT customerId FROM reviews);

-- 3.Set Difference Operation.  (not working)
SELECT firstName
FROM customer
WHERE customerId NOT IN (SELECT DISTINCT customerId FROM reviews);


-- 4.Inner Join
SELECT c.firstName, o.orderId, o.orderDate
FROM customer c
INNER JOIN orders o ON c.customerId = o.customerId;

-- 5.Natural Join
CREATE VIEW ProductSupplierView AS
SELECT productName, supplierName
FROM product
NATURAL JOIN supplier;

SELECT productName, supplierName
FROM ProductSupplierView;

-- 6.Left Outer Join - Find all cutomers who has given more than or equal to 4.0 rating for a product
CREATE VIEW HighRatingReviews AS
SELECT c.firstName, r.rating, r.review
FROM customer c
LEFT JOIN reviews r ON c.customerId = r.customerId
WHERE r.rating >= 4.0;

SELECT * FROM HighRatingReviews;


-- 7.Right Outer Join - Retrive all the product names and supplier names who are located in Chicago.
CREATE VIEW chicagoSuppliers AS
SELECT p.productName, s.supplierName
FROM product p
RIGHT JOIN supplier s ON p.supplierId = s.supplierId
WHERE s.address = "Chicago";

SELECT * FROM chicagoSuppliers;

-- 8.Full Outer Join - Get all the products that customer names and reviews where review rating is more than 3.0

CREATE VIEW reviwers AS
SELECT firstName, rating, review
FROM (
    SELECT c.firstName, r.rating, r.review
    FROM customer c
    LEFT JOIN reviews r ON c.customerId = r.customerId

    UNION

    SELECT c.firstName, r.rating, r.review
    FROM customer c
    RIGHT JOIN reviews r ON c.customerId = r.customerId
) AS combined_data
WHERE rating >= 3;

SELECT *
FROM reviwers;

-- 9.Outer Union - Show the customers living in same state as suppliers 

INSERT INTO supplier(supplierId,supplierName,contactNo,address)
VALUES  ('S5009', 'CHAngGroup', '(323) 979-23488', 'Vesex');

CREATE VIEW supplierCustomerState AS
SELECT c.firstName AS name, a_c.state AS customer_state, NULL AS supplier_name, NULL AS supplier_state
FROM customer c
LEFT JOIN address a_c ON c.customerId = a_c.customerId
WHERE a_c.state = "Vesex" 
UNION
SELECT NULL AS name, NULL AS customer_state, s.supplierName AS supplier_name, s.address AS supplier_state
FROM supplier s
WHERE s.address = "Vesex";

SELECT *
FROM supplierCustomerState;



-- 10.Nested Query with Intersection 
-- Find every cart that contains at least one product which has reviews under 3.0
CREATE VIEW minReviews AS
SELECT sc.cartId
FROM shoppingCart AS sc
WHERE sc.cartId IN (
    SELECT DISTINCT pc.cartId
    FROM product_shoppingCart AS pc
    WHERE pc.productId IN (
        SELECT DISTINCT p.productId
        FROM product AS p
        JOIN reviews AS r ON p.productId = r.productId
        WHERE r.rating < 3.0
    )
);

SELECT *
FROM minReviews;

-- 11.Nested Query with Left Outer Join.

-- Query to return all the customers address id, state and city, where the cutomers have purchsed the shopping cart

CREATE VIEW purchesedCustomers AS
SELECT a.addressId, a.state, a.city
FROM address as a
WHERE a.customerId IN (
	SELECT c.customerId
	FROM customer c
	LEFT JOIN shoppingCart as sc ON c.customerId = sc.customerId
	WHERE purcheseStatus = 1
);

SELECT *
FROM purchesedCustomers;


-- 12.Nested Query with Set Difference - names of suppliers who do not supply a product with productID = P1007.
CREATE VIEW P1007productSupplier AS
SELECT supplierName
FROM supplier
WHERE supplierId NOT IN (
  SELECT supplierId
  FROM product
  WHERE productId = 'P1007'
);

SELECT *
FROM P1007productSupplier;

-- 13.Division Operation -the names of customers who have purchased all the products listed in the "product" table
INSERT INTO product_shoppingCart(productId,cartId)
VALUES 
("P1001","Cart006"),
("P1003","Cart006"),
("P1004","Cart006"),
("P1005","Cart006"),
("P1007","Cart006"),
("P1008","Cart006");


CREATE VIEW BestCustomers AS
SELECT c.firstName, c.country
FROM customer c
WHERE NOT EXISTS (
  SELECT p.productId
  FROM product p
  WHERE NOT EXISTS (
    SELECT ps.productId
    FROM product_shoppingCart ps
    WHERE ps.cartId IN (
      SELECT o.cartId
      FROM orders o
      WHERE o.customerId = c.customerId
    )
    AND ps.productId = p.productId
  )
);

SELECT *
FROM BestCustomers;


-- Tuning Part

-- Tuning Query 2
EXPLAIN(
	SELECT firstName AS 'Customer Name'
	FROM customer
	WHERE customerId IN (SELECT DISTINCT customerId FROM reviews)
);

CREATE INDEX index_cusId ON customer(customerId);
CREATE INDEX index_cus_Id ON reviews(customerId);
SHOW INDEX FROM customer;
SHOW INDEX FROM reviews;

EXPLAIN(
	SELECT firstName AS 'Customer Name'
	FROM customer
	WHERE customerId IN (SELECT DISTINCT customerId FROM reviews)
);
-- Tuning Query 5

EXPLAIN(
	SELECT productName, supplierName
	FROM ProductSupplierView
);

CREATE INDEX idx_product_productName ON product(productName);
CREATE INDEX idx_supplier_supplierName ON supplier(supplierName);
SHOW INDEX FROM supplier;
SHOW INDEX FROM product;

EXPLAIN(
	SELECT productName, supplierName
	FROM ProductSupplierView
);

-- Tuning Query 6

EXPLAIN(
	SELECT * FROM HighRatingReviews
);

CREATE INDEX idx_rating ON reviews(rating);
SHOW INDEX FROM reviews;

EXPLAIN(
	SELECT * FROM HighRatingReviews
);

-- Tuning Query 7
EXPLAIN(
	SELECT * FROM chicagoSuppliers
);

CREATE INDEX idx_address ON supplier(address);
SHOW INDEX FROM supplier;

EXPLAIN(
	SELECT * FROM chicagoSuppliers
);

-- Tuning Query 8
-- Added indexing to review column on tuning Query 6
-- CREATE INDEX idx_rating ON reviews(rating);
EXPLAIN(
	SELECT *
	FROM reviwers
);

CREATE INDEX idx_cus_id ON customer(customerId);
CREATE INDEX idx_cus_id_review ON reviews(customerId);
SHOW INDEX FROM customer;
SHOW INDEX FROM reviews;

EXPLAIN(
	SELECT *
	FROM reviwers
);

-- Tuning Query 9
EXPLAIN(
	SELECT *
	FROM supplierCustomerState
);

CREATE INDEX idx_state ON address(state);
-- added indexing to this column while tuning Query 6
-- CREATE INDEX idx_address ON supplier(address);

EXPLAIN(
	SELECT *
	FROM supplierCustomerState
);

-- Tuning Query 10
-- Added indexing to review column on tuning Query 6
-- CREATE INDEX idx_rating ON reviews(rating);
EXPLAIN(
SELECT *
FROM minReviews
);

-- Tuning Query 11
CREATE INDEX idx_purches_state ON shoppingCart(purcheseStatus);

EXPLAIN(
	SELECT *
	FROM purchesedCustomers
);

-- Tuning Query 12
CREATE INDEX idx_product_id ON product(productId);

EXPLAIN(
	SELECT *
	FROM P1007productSupplier
);

-- Tuning Query 13
-- Added indexs in previous steps

EXPLAIN(
	SELECT *
	FROM BestCustomers
);
