 create database FashionFitsMe;
-- drop database  FashionFitsMe;
use FashionFitsMe;



-- creating brand table
CREATE TABLE brand(
	brandId varchar(20) NOT NULL,
    brandName varchar(50) NOT NULL,
    
    primary key (brandId)
);


-- creating brandMeasurement table
CREATE TABLE brandMeasurement(
	brandId varchar(20) NOT NULL,
    MeasurementType varchar(50) NOT NULL,
	Measurement varchar(50) NOT NULL,

	foreign key (brandId) references brand(brandId),
	primary key (MeasurementType,brandId)
);

INSERT INTO brand (brandId, brandName) VALUES
('BR001', 'Nike'),
('BR002', 'Adidas'),
('BR003', 'Puma'),
('BR004', 'Carnage'),
('BR005', 'Odel');



-- Creating product table
CREATE TABLE product(
	productId varchar(20) NOT NULL,
    brandId varchar(20),
    productName varchar(50) NOT NULL,
    price decimal(10,2) NOT NULL,
    primary key (productId),
    
    foreign key (brandId) references brand(brandId)
);


INSERT INTO Product (ProductId, brandId, ProductName, Price)
VALUES ('P1001', 'BR001', 'Smartphone', 599.99),
       ('P1002', 'BR001', 'Plaster', 999.99),
       ('P1003', 'BR005', 'Nails', 299.99),
       ('P1004', 'BR005', 'Meats', 79.99),
       ('P1005', 'BR001', 'Tube', 499.99),
       ('P1006', 'BR003', 'Rose', 199.99),
       ('P1007', 'BR002', 'Car', 11499.99),
       ('P1008', 'BR001', 'Tube', 23.99),
       ('P1009', 'BR002', 'Shampoo', 23.99);
       
INSERT INTO Product (ProductId, brandId, ProductName, Price)
VALUES ('P10011', 'BR001', 'Smartphone', 599.99),
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
	customerId VARCHAR(30) NOT NULL,
	firstName VARCHAR(150) NOT NULL,
	lastName VARCHAR(150) NOT NULL,
	country VARCHAR(30) NOT NULL,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
	PRIMARY KEY (customerId)
);

INSERT INTO customer (customerId, firstName, lastName, country, username, password) VALUES
('CUST001', 'John', 'Doe', 'USA', 'john_doe', 'password123'),
('CUST002', 'Jane', 'Smith', 'Canada', 'jane_smith', 'securepass'),
('CUST003', 'Michael', 'Johnson', 'UK', 'michael_j', 'myp@ssw0rd');


CREATE TABLE customerMeasurement(
	customerId varchar(20) NOT NULL,
    MeasurementType varchar(50) NOT NULL,
	Measurement varchar(50) NOT NULL,

	foreign key (customerId) references customer(customerId),
	primary key (MeasurementType)
);
INSERT INTO customerMeasurement (customerId, MeasurementType, Measurement) VALUES
('CUST001', 'Height', '175 cm'),
('CUST002', 'Waist', '68 cm'),
('CUST003', 'Hips', '23.5');


CREATE TABLE customerBrand(
	customerId varchar(20) NOT NULL,
    brandId varchar(50) NOT NULL,
	
	primary key (customerId,brandId),
	foreign key (customerId) references customer(customerId),
	foreign key (brandId) references brand(brandId)

);

INSERT INTO customerBrand (customerId, brandId) VALUES
('CUST001', 'BR001'),
('CUST001', 'BR002'),
('CUST002', 'BR001'),
('CUST003', 'BR003');


-- Creating customer contact table
CREATE TABLE customerContact(
	customerId VARCHAR(30) NOT NULL,
    contactNo VARCHAR(20) NOT NULL,
    PRIMARY KEY (customerId, contactNo),
    foreign key (customerId) references customer(customerId)
);

INSERT INTO customerContact (customerId, contactNo)
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
CREATE TABLE shoppingCart (
	cartId VARCHAR(30) NOT NULL,
    totalAmount DECIMAL(10,2),
    customerId VARCHAR(30) NOT NULL,
    purcheseStatus bool NOT NULL,
    discountAmount DECIMAL(10,2),
	PRIMARY KEY (cartId),
    constraint FK_cusId foreign key(customerId) references customer(customerId)
);


INSERT INTO shoppingCart (cartId,  customerId, purcheseStatus, discountAmount) 
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
create table product_shoppingCart(
	product_Id_ VARCHAR(20) NOT NULL,
	cart_Id_ VARCHAR(30) NOT NULL,
	primary key(product_Id_,cart_Id_),
	constraint FK_products foreign key (product_Id_) references product(productId),
	constraint FK_cart foreign key (cart_Id_) references shoppingCart(cartId)
);

INSERT INTO product_shoppingCart (product_Id_, cart_Id_)
VALUES
    ('P1001', 'Cart001'),
    ('P1002', 'Cart002'),
    ('P1003', 'Cart003'),
    ('P1004', 'Cart004'),
    ('P1005', 'Cart005'),
    ('P1006', 'Cart006'),
    ('P1007', 'Cart007');
    
INSERT INTO product_shoppingCart (product_Id_, cart_Id_)
VALUES
    ('P10011', 'Cart001'),
    ('P10012', 'Cart001'),
    ('P10013', 'Cart002'),
    ('P10014', 'Cart002'),
    ('P10015', 'Cart001'),
    ('P10016', 'Cart002'),
    ('P10017', 'Cart001');

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
	paymentNo VARCHAR(30) NOT NULL,
    orderId VARCHAR(30) NOT NULL,
	paymentMethod VARCHAR(100) NOT NULL,
	paymentDetails VARCHAR(150) NOT NULL,
	PRIMARY KEY (paymentNo,orderId),
    constraint FK_order_payment foreign key (orderId) references orders(orderId)
);

INSERT INTO payment (paymentNo, orderId, paymentMethod, paymentDetails)
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
    INNER JOIN product p ON scp.product_Id_ = p.productId
    WHERE scp.cart_Id_ = NEW.cart_Id_;
    
    -- Update the order table with the new total
    UPDATE orders
    SET total = order_total
    WHERE cartId = NEW.cart_Id_;
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
    INNER JOIN product p ON scp.product_Id_ = p.productId
    WHERE scp.cart_Id_ = NEW.cart_Id_;
    
    -- Update the order table with the new total
    UPDATE orders
    SET total = order_total
    WHERE cartId = NEW.cart_Id_;
END;
//
       
CREATE INDEX idx_customer_id ON customer(customer_id);

CREATE INDEX idx_brand_id ON brand(brand_id);
CREATE INDEX idx_shipping_id ON shipments(shipping_id);
CREATE INDEX idx_order_id ON orders(order_id);
CREATE INDEX idx_product_id ON product(product_id);
CREATE INDEX idx_address_id ON address(address_id);
CREATE INDEX idx_shipper_id ON shipper(shipper_id);






