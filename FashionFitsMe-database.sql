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
    product_category varchar(100) not null,
    primary key (product_id),
    
    foreign key (brand_id) references brand(brand_id)
);
alter table product
add column gender varchar(20);

CREATE TABLE product_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id varchar(20) NOT NULL,
    image_data LONGBLOB,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);



	CREATE TABLE product_color_size(
	product_id varchar(20) NOT NULL,
    color varchar(20) NOT NULL,
    size varchar(20) NOT NULL,
    quantity int NOT NULL,
    PRIMARY KEY(product_id, color, size),
	foreign key (product_id) references Product(product_id)
);
  


    


-- Creating customer table
CREATE TABLE customer (
	customer_id int AUTO_INCREMENT NOT NULL,
	first_name VARCHAR(150) NOT NULL,
	last_name VARCHAR(150) NOT NULL,
	country VARCHAR(30) NOT NULL,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(300) NOT NULL,
	PRIMARY KEY (customer_id)
);





CREATE TABLE customer_measurement(
	customer_id int NOT NULL,
    measurement_type varchar(50) NOT NULL,
	measurement varchar(50) NOT NULL,

	foreign key (customer_id) references customer(customer_id),
	primary key (measurement_type)
);



CREATE TABLE customer_brand(
	customer_id int NOT NULL,
    brand_id varchar(50) NOT NULL,
	
	primary key (customer_id,brand_id),
	foreign key (customer_id) references customer(customer_id),
	foreign key (brand_id) references brand(brand_id)

);



-- Creating customer contact table
CREATE TABLE customer_contact(
	customer_id int NOT NULL,
    contact_no VARCHAR(20) NOT NULL,
    PRIMARY KEY (customer_id, contact_no),
    foreign key (customer_id) references customer(customer_id)
);

    


-- creating reviews table
CREATE TABLE reviews (
	review_id VARCHAR(30) NOT NULL,
	customer_id int,
	product_id VARCHAR(20),
	rating DECIMAL(3, 1) NOT NULL,
	review_description VARCHAR(300) NOT NULL,
	PRIMARY KEY (review_Id),
	constraint FK_reviews foreign key(product_id) references product(product_id) on delete set null on update cascade,
	constraint FK_reviews_gives foreign key(customer_id) references customer(customer_id) on delete set null on update cascade
);


-- INSERT INTO reviews (review_Id, customer_Id, product_Id, rating, review_description)
-- VALUES
--     (1011, 'CUST001', 'P1001', 4.5, 'Good'),
--     (2011, 'CUST001', 'P1002', 3.0, 'Average'),
--     (3011, 'CUST002', 'P1003', 5.0, 'Recommended'),
--     (4011, 'CUST002', 'P1004', 4.0, 'Good'),
--     (8011, 'CUST002', 'P1003', 1.5,'Bad'),
--     (9011, 'CUST001', 'P1001', 4.5,'Good');
 


-- Creating shopping cart table
CREATE TABLE shopping_cart (
	cart_id VARCHAR(30) NOT NULL,
    total_amount DECIMAL(10,2),
    customer_id int NOT NULL,
    purchese_status bool NOT NULL,
    discount_amount DECIMAL(10,2),
	PRIMARY KEY (cart_id),
    constraint FK_cusId foreign key(customer_id) references customer(customer_id)
);


-- INSERT INTO shopping_Cart (cart_Id,  customer_Id, purchese_Status, discount_Amount) 
-- VALUES ('Cart001',  'CUST001',TRUE, 25.50),
-- ('Cart002',  'CUST001',FALSE, NULL),
-- ('Cart003',  'CUST001',TRUE, 10.00),
-- ('Cart004',  'CUST002',TRUE, 5.25),
-- ('Cart005',  'CUST003',FALSE, NULL),
-- ('Cart006',  'CUST001',TRUE, 15.75),
-- ('Cart007', 'CUST002',TRUE, 7.50),
-- ('Cart008',  'CUST002',FALSE, 6.50),
-- ('Cart009',  'CUST003',TRUE, NULL);



-- Creating product_shoppingCart table
create table product_shopping_cart(
	product_id VARCHAR(20) NOT NULL,
	cart_id VARCHAR(30) NOT NULL,
	primary key(product_id,cart_id),
	constraint FK_products foreign key (product_id) references product(product_id),
	constraint FK_cart foreign key (cart_id) references shopping_cart(cart_id)
);




-- drop table product_shopping_cart;

select * from product;
-- Creating address table
CREATE TABLE address(
	address_id VARCHAR(150) NOT NULL,
	customer_id int NOT NULL,
    state VARCHAR(40) NOT NULL,
	city VARCHAR(40) NOT NULL,
	street VARCHAR(40) NOT NULL,
	zip VARCHAR(30) NOT NULL,
	PRIMARY KEY (address_id),
    constraint FK_customer_address foreign key (customer_id) references customer(customer_id)
);


-- INSERT INTO address (address_Id, customer_Id, state, city, street, zip)
-- VALUES (11111, 'CUST001', 'Male01', 'Male', '123MainSt', '10001'),
-- (33333, 'CUST001', 'Delhi', 'NewDelhi', '456ElmSt', '30003'),
-- (44444, 'CUST002', 'NSW', 'Sydney', '789OakAve', '40004'),
-- (22222, 'CUST003', 'ON', 'Ottwa', '567PineRd', '20002'),
-- (55555, 'CUST001', 'Berlin05', 'Berlin', '890BirchLn', '50005'),
-- (66666, 'CUST002', 'Paris06', 'Paris', '101CedarRd', '60006'),
-- (77777, 'CUST003', 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),

-- (88888, 'CUST003', 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),
-- (99999, 'CUST001', 'Valladolid07', 'Valladolid', '222RedwoodAve', '70007'),
-- (99991, 'CUST002', 'Bermingham', 'London', '222RedwoodAve', '70008'),
-- (99992, 'CUST001', 'North-West', 'Ruswells', '222RedwoodAve', '70009');



-- Creating shipper table
CREATE TABLE shipper(
	shipper_id VARCHAR(30) NOT NULL,
	shipper_name VARCHAR(100) NOT NULL,
	contact_no VARCHAR(20) NOT NULL,
	PRIMARY KEY (shipper_id)
);


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



-- Creating orders table
CREATE TABLE orders(
	order_id VARCHAR(30) NOT NULL,
	customer_id int,
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



-- Creating payment table
CREATE TABLE payment (
	payment_no VARCHAR(30) NOT NULL,
    order_id VARCHAR(30) NOT NULL,
	payment_method VARCHAR(100) NOT NULL,
	payment_details VARCHAR(150) NOT NULL,
	PRIMARY KEY (payment_no,order_id),
    constraint FK_order_payment foreign key (order_id) references orders(order_id)
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

INSERT INTO product_shopping_cart (product_Id, cart_Id)
VALUES
    ('P1001', 'Cart001'),
    ('P1002', 'Cart001'),
    ('P1003', 'Cart001'),
    ('P1004', 'Cart001');

select * from shopping_Cart;
SELECT * from product;
select * from customer;
select * from product_shopping_cart WHERE cart_Id = 'Cart001';
select * from product_color_size;
UPDATE product
SET price = 10005
WHERE product_id = 'p1005';


