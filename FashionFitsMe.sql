 create database FashionFitsMe;
-- drop database  FashionFitsMe;
use FashionFitsMe;



-- creating brand table
CREATE TABLE brand(
	brand_id varchar(20) NOT NULL,
    brand_name varchar(50) NOT NULL,
    
    primary key (brand_id)
);
INSERT INTO brand (brand_id, brand_name) VALUES
('BR001', 'Nike'),
('BR002', 'Adidas'),
('BR003', 'Puma'),
('BR004', 'Carnage'),
('BR005', 'Odel');

-- creating brandMeasurement table
CREATE TABLE brand_measurement(
	brand_id varchar(20) NOT NULL,
    Measurement_type varchar(50) NOT NULL,
	Measurement varchar(50) NOT NULL,

	foreign key (brand_id) references brand(brand_id),
	primary key (Measurement_type,brand_id)
);





-- Creating product table
CREATE TABLE product(
	product_id varchar(20) NOT NULL,
    brand_id varchar(20),
    product_name varchar(50) NOT NULL,
    price decimal(10,2) NOT NULL,
    primary key (product_id),
    
    foreign key (brand_id) references brand(brand_id)
);


INSERT INTO Product (product_id, brand_id, product_name, price)
VALUES ('P1001', 'BR001', 'Smartphone', 599.99),
       ('P1002', 'BR001', 'Plaster', 999.99),
       ('P1003', 'BR005', 'Nails', 299.99),
       ('P1004', 'BR005', 'Meats', 79.99),
       ('P1005', 'BR001', 'Tube', 499.99),
       ('P1006', 'BR003', 'Rose', 199.99),
       ('P1007', 'BR002', 'Car', 11499.99),
       ('P1008', 'BR001', 'Tube', 23.99),
       ('P1009', 'BR002', 'Shampoo', 23.99),
	  ('P10011', 'BR001', 'Smartphone', 599.99),
       ('P10012', 'BR001', 'Plaster', 999.99),
       ('P10013', 'BR005', 'Nails', 299.99),
       ('P10014', 'BR005', 'Meats', 79.99),
       ('P10015', 'BR001', 'Tube', 499.99),
       ('P10016', 'BR003', 'Rose', 199.99),
       ('P10017', 'BR002', 'Car', 11499.99),
       ('P10018', 'BR001', 'Tube', 23.99),
       ('P10019', 'BR002', 'Shampoo', 23.99);
       

       

-- Creating customer table
CREATE TABLE customer (
	customer_id VARCHAR(30) NOT NULL,
	first_name VARCHAR(150) NOT NULL,
	last_name VARCHAR(150) NOT NULL,
	country VARCHAR(30) NOT NULL,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
	PRIMARY KEY (customer_id)
);

INSERT INTO customer (customer_id, first_name, last_name, country, username, password) VALUES
('CUST001', 'John', 'Doe', 'USA', 'john_doe', 'password123'),
('CUST002', 'Jane', 'Smith', 'Canada', 'jane_smith', 'securepass'),
('CUST003', 'Michael', 'Johnson', 'UK', 'michael_j', 'myp@ssw0rd');


CREATE TABLE customer_measurement(
	customer_id varchar(20) NOT NULL,
    measurement_type varchar(50) NOT NULL,
	measurement varchar(50) NOT NULL,

	foreign key (customer_id) references customer(customer_id),
	primary key (measurement_type)
);
INSERT INTO customer_measurement (customer_id, Measurement_type, Measurement) VALUES
('CUST001', 'Height', '175 cm'),
('CUST002', 'Waist', '68 cm'),
('CUST003', 'Hips', '23.5');


CREATE TABLE customer_brand(
	customer_id varchar(20) NOT NULL,
    brand_id varchar(50) NOT NULL,
	
	primary key (customer_id,brand_id),
	foreign key (customer_id) references customer(customer_id),
	foreign key (brand_id) references brand(brand_id)

);

INSERT INTO customer_Brand (customer_id, brand_id) VALUES
('CUST001', 'BR001'),
('CUST001', 'BR002'),
('CUST002', 'BR001'),
('CUST003', 'BR003');


-- Creating customer contact table
CREATE TABLE customer_contact(
	customer_id VARCHAR(30) NOT NULL,
    contact_no VARCHAR(20) NOT NULL,
    PRIMARY KEY (customer_id, contact_no),
    foreign key (customer_id) references customer(customer_id)
);

INSERT INTO customer_contact (customer_Id, contact_No)
VALUES
    ('CUST001', '123-456-7890'),
    ('CUST003', '987-654-3210'),
    ('CUST002', '555-123-4567'),
    ('CUST001', '888-555-9999'),
	('CUST003', '777-111-2222'),
    ('CUST001', '555-777-8888'),
    ('CUST002', '999-333-4444'),
    ('CUST002', '934-333-4444');
    
    


-- creating reviews table
CREATE TABLE reviews (
	review_id VARCHAR(30) NOT NULL,
	customer_id VARCHAR(30),
	product_id VARCHAR(20),
	rating DECIMAL(3, 1) NOT NULL,
	review_description VARCHAR(300) NOT NULL,
	PRIMARY KEY (review_Id),
	constraint FK_reviews foreign key(product_id) references product(product_id) on delete set null on update cascade,
	constraint FK_reviews_gives foreign key(customer_id) references customer(customer_id) on delete set null on update cascade
);


INSERT INTO reviews (review_Id, customer_Id, product_Id, rating, review_description)
VALUES
    (1011, 'CUST001', 'P1001', 4.5, 'Good'),
    (2011, 'CUST001', 'P1002', 3.0, 'Average'),
    (3011, 'CUST002', 'P1003', 5.0, 'Recommended'),
    (4011, 'CUST002', 'P1004', 4.0, 'Good'),
    (5011, 'CUST003', 'P1005', 2.5, 'NotSatisfied'),
    (6011, 'CUST003', 'P1006', 4.0, 'Good'),
    (7011, 'CUST001', 'P1007', 2.5,'Bad'),
    (8011, 'CUST002', 'P1003', 1.5,'Bad'),
    (9011, 'CUST001', 'P1001', 4.5,'Good');
 


-- Creating shopping cart table
CREATE TABLE shopping_cart (
	cart_id VARCHAR(30) NOT NULL,
    total_amount DECIMAL(10,2),
    customer_id VARCHAR(30) NOT NULL,
    purchese_status bool NOT NULL,
    discount_amount DECIMAL(10,2),
	PRIMARY KEY (cart_id),
    constraint FK_cusId foreign key(customer_id) references customer(customer_id)
);


INSERT INTO shopping_Cart (cart_Id,  customer_Id, purchese_Status, discount_Amount) 
VALUES ('Cart001',  'CUST001',TRUE, 25.50),
('Cart002',  'CUST001',FALSE, NULL),
('Cart003',  'CUST001',TRUE, 10.00),
('Cart004',  'CUST002',TRUE, 5.25),
('Cart005',  'CUST003',FALSE, NULL),
('Cart006',  'CUST001',TRUE, 15.75),
('Cart007', 'CUST002',TRUE, 7.50),
('Cart008',  'CUST002',FALSE, 6.50),
('Cart009',  'CUST003',TRUE, NULL);



-- Creating product_shoppingCart table
create table product_shopping_cart(
	product_id VARCHAR(20) NOT NULL,
	cart_id VARCHAR(30) NOT NULL,
	primary key(product_id,cart_id),
	constraint FK_products foreign key (product_id) references product(product_id),
	constraint FK_cart foreign key (cart_id) references shopping_cart(cart_id)
);

INSERT INTO product_shopping_cart (product_Id, cart_Id)
VALUES
    ('P1001', 'Cart002'),
    ('P1002', 'Cart002'),
    ('P1003', 'Cart003'),
    ('P1004', 'Cart004'),
    ('P1005', 'Cart005'),
    ('P1006', 'Cart006'),
    ('P1007', 'Cart007'),
    ('P10011', 'Cart002'),
    ('P10012', 'Cart002'),
    ('P10013', 'Cart002'),
    ('P10014', 'Cart002'),
    ('P10015', 'Cart002'),
    ('P10016', 'Cart002'),
    ('P10017', 'Cart002');
INSERT INTO product_shopping_cart (product_Id, cart_Id)
VALUES('P17', 'Cart002');

drop table product_shopping_cart;

select * from product;
-- Creating address table
CREATE TABLE address(
	address_id VARCHAR(150) NOT NULL,
	customer_id varchar(30) NOT NULL,
    state VARCHAR(40) NOT NULL,
	city VARCHAR(40) NOT NULL,
	street VARCHAR(40) NOT NULL,
	zip VARCHAR(30) NOT NULL,
	PRIMARY KEY (address_id),
    constraint FK_customer_address foreign key (customer_id) references customer(customer_id)
);


INSERT INTO address (address_Id, customer_Id, state, city, street, zip)
VALUES (11111, 'CUST001', 'Male01', 'Male', '123MainSt', '10001'),
(33333, 'CUST001', 'Delhi', 'NewDelhi', '456ElmSt', '30003'),
(44444, 'CUST002', 'NSW', 'Sydney', '789OakAve', '40004'),
(22222, 'CUST003', 'ON', 'Ottwa', '567PineRd', '20002'),
(55555, 'CUST001', 'Berlin05', 'Berlin', '890BirchLn', '50005'),
(66666, 'CUST002', 'Paris06', 'Paris', '101CedarRd', '60006'),
(77777, 'CUST003', 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),

(88888, 'CUST003', 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),
(99999, 'CUST001', 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),
(99991, 'CUST002', 'Bermingham', 'London', '222RedwoodAve', '70008'),
(99992, 'CUST001', 'North-West', 'Ruswells', '222RedwoodAve', '70009');



-- Creating shipper table
CREATE TABLE shipper(
	shipper_id VARCHAR(30) NOT NULL,
	shipper_name VARCHAR(100) NOT NULL,
	contact_no VARCHAR(20) NOT NULL,
	PRIMARY KEY (shipper_id)
);

INSERT INTO shipper (shipper_id, shipper_name, contact_no)
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



-- Creating shipment table
CREATE TABLE shipments (
	shipping_id VARCHAR(30) NOT NULL,
	shipper_id VARCHAR(30),
	address_id VARCHAR(150) NOT NULL,
	shipment_status VARCHAR(150) NOT NULL,
	shipment_date DATE NOT NULL,
	PRIMARY KEY (shipping_id,address_id),
	constraint FK_shipments foreign key (address_id) references address(address_id),
	constraint FK_shipments_shipper foreign key (shipper_id) references shipper(shipper_id) on delete set null on update cascade
);


INSERT INTO shipments (shipping_Id, shipper_Id, address_Id, shipment_Status, shipment_Date)
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



-- Creating orders table
CREATE TABLE orders(
	order_id VARCHAR(30) NOT NULL,
	customer_id VARCHAR(30),
	cart_id VARCHAR(30),
	shipping_id VARCHAR(30),
	order_date DATE NOT NULL,
	total INT DEFAULT NULL,
	PRIMARY KEY (order_id),
	constraint FK_orders foreign key (customer_id) references customer(customer_id) on delete set null on update cascade,
	constraint FK_orders_shopping_cart foreign key (cart_id) references shopping_cart(cart_id) on delete set null on update cascade,
	constraint FK_orders_shipments foreign key (shipping_id) references shipments(shipping_id) on delete set null on update cascade
);

ALTER TABLE orders
MODIFY total DECIMAL(10, 2);


INSERT INTO orders (order_Id, customer_Id, cart_Id, shipping_Id, order_Date, total)
VALUES
    ('ORDER001', 'CUST001', 'Cart001', 'SHIP001', '2023-09-10', 250),
    ('ORDER002', 'CUST002', 'Cart002', 'SHIP002', '2023-09-11', 150),
    ('ORDER003', 'CUST003', 'Cart003', 'SHIP003', '2023-09-12', 300),
    ('ORDER004', 'CUST002', 'Cart004', 'SHIP004', '2023-09-13', 175),
    ('ORDER005', 'CUST003', 'Cart005', 'SHIP005', '2023-09-14', 200),
    ('ORDER006', 'CUST002','Cart006', 'SHIP006', '2023-09-15', 180),
    ('ORDER007', 'CUST001', 'Cart007', 'SHIP007', '2023-09-16', 210),
    ('ORDER009', 'CUST002', 'Cart008', 'SHIP005', '2023-09-16', 410),
    ('ORDER010', 'CUST001', 'Cart008', 'SHIP006', '2023-09-16', 310);



-- Creating payment table
CREATE TABLE payment (
	payment_no VARCHAR(30) NOT NULL,
    order_id VARCHAR(30) NOT NULL,
	payment_method VARCHAR(100) NOT NULL,
	payment_details VARCHAR(150) NOT NULL,
	PRIMARY KEY (payment_no,order_id),
    constraint FK_order_payment foreign key (order_id) references orders(order_id)
);

INSERT INTO payment (payment_no, order_Id, payment_Method, payment_Details)
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

CREATE TABLE product_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id varchar(20) NOT NULL,
    image_data LONGBLOB,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Trigers to update total in order when a new item added to the cart
DELIMITER //

CREATE TRIGGER insert_order_total
AFTER INSERT ON product_shopping_cart
FOR EACH ROW
BEGIN
    DECLARE order_total DECIMAL(10, 2);
    
    -- Calculate the total for the given cartId
    SELECT SUM(p.price)
    INTO order_total
    FROM product_shopping_cart scp
    INNER JOIN product p ON scp.product_Id = p.product_Id
    WHERE scp.cart_Id = NEW.cart_Id;
    
    -- Update the product_shopping_cart table with the new total
    UPDATE shopping_Cart
    SET total_amount=order_total
    WHERE cart_Id = NEW.cart_Id;
    
    -- Update the order table with the new total
    UPDATE orders
    SET total = order_total
    WHERE cart_Id = NEW.cart_Id;
END;
//


DELIMITER //

CREATE TRIGGER update_order_total
AFTER UPDATE ON product_shopping_cart
FOR EACH ROW
BEGIN
    DECLARE order_total DECIMAL(10, 2);
    
    -- Calculate the total for the given cartId
    SELECT SUM(p.price)
    INTO order_total
    FROM shopping_cart scp
    INNER JOIN product p ON scp.product_id = p.product_id
    WHERE scp.cart_Id = NEW.cart_Id;
    
     -- Update the product_shopping_cart table with the new total
      UPDATE shopping_Cart
    SET total_amount=order_total
    WHERE cart_Id = NEW.cart_Id;
    
    -- Update the order table with the new total
    UPDATE orders
    SET total = order_total
    WHERE cartId = NEW.cart_Id;
END;
//


drop trigger insert_order_total;
select * from shopping_Cart;
SELECT * from product;
select * from orders;
select * from product_shopping_cart;
WHERE cart_Id = 'Cart001';

SELECT * FROM Product p WHERE p.productId = 'p1001'



