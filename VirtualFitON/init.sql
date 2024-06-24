drop  database FashionFitsMe;

create database FashionFitsMe;


use FashionFitsMe;

-- creating brand table
CREATE TABLE brand(
	brand_id varchar(255) NOT NULL,
    brand_name varchar(255) NOT NULL,
    product_media LONGBLOB,
    primary key (brand_id)
);


-- creating brandMeasurement table
CREATE TABLE brand_measurement(
	brand_id varchar(255) NOT NULL,
    measurement_id bigint NOT NULL AUTO_INCREMENT,
    ankle_circumference decimal(38,2) DEFAULT NULL,
    arm_length decimal(38,2) DEFAULT NULL,
    bicep_circumference decimal(38,2) DEFAULT NULL,
    calf_circumference decimal(38,2) DEFAULT NULL,
    category varchar(255) DEFAULT NULL,
    chest_circumference decimal(38,2) DEFAULT NULL,
    forearm_circumference decimal(38,2) DEFAULT NULL,
    head_circumference decimal(38,2) DEFAULT NULL,
    hip_circumference decimal(38,2) DEFAULT NULL,
    inside_leg_length decimal(38,2) DEFAULT NULL,
    item varchar(255) DEFAULT NULL,
    neck_circumference decimal(38,2) DEFAULT NULL,
    shoulder_breadth decimal(38,2) DEFAULT NULL,
    shoulder_to_crotch decimal(38,2) DEFAULT NULL,
    size varchar(255) DEFAULT NULL,
    thigh_circumference decimal(38,2) DEFAULT NULL,
    waist_circumference decimal(38,2) DEFAULT NULL,
    wrist_circumference decimal(38,2) DEFAULT NULL,


    foreign key (brand_id) references brand(brand_id),
	primary key (Measurement_id)
);


-- Creating product table
CREATE TABLE product(
    product_id varchar(255) NOT NULL,
    brand_id varchar(255),
    product_name varchar(50) NOT NULL,
    price decimal(10,2) NOT NULL,
    product_category varchar(255) DEFAULT NULL,
    gender varchar(20) not null,
    description varchar(300) ,

    primary key (product_id),
    foreign key (brand_id) references brand(brand_id)
);


CREATE TABLE product_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id varchar(20) NOT NULL,
    image_data LONGBLOB,
    colour varchar(20) not null,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);


	CREATE TABLE product_color_size(
	product_id varchar(20) NOT NULL,
    color varchar(20) NOT NULL,
    size varchar(20) NOT NULL,
    quantity int NOT NULL,
    PRIMARY KEY(product_id, color, size),
	foreign key (product_id) references product(product_id)
);

CREATE TABLE shopping_cart (
	cart_id int auto_increment,
    total_amount DECIMAL(10,2),
    purchese_status bool NOT NULL,
    discount_amount DECIMAL(10,2),
	PRIMARY KEY (cart_id)
);


-- Creating customer table
CREATE TABLE customer (
	customer_id int AUTO_INCREMENT NOT NULL,
	first_name VARCHAR(150) NOT NULL,
	last_name VARCHAR(150) NOT NULL,
    username VARCHAR(30) NOT NULL,
    cart_id int not null,
    password VARCHAR(300) NOT NULL,
	PRIMARY KEY (customer_id),
    CONSTRAINT FK_cart_id FOREIGN KEY (cart_id) REFERENCES shopping_cart(cart_id)
);





CREATE TABLE customer_measurement(
    id decimal(38,2) NOT NULL,
	customer_id int NOT NULL,
    ankle_circumference decimal(38,2) DEFAULT NULL,
    arm_length decimal(38,2) DEFAULT NULL,
    bicep_circumference decimal(38,2) DEFAULT NULL,
    calf_circumference decimal(38,2) DEFAULT NULL,
    chest_circumference decimal(38,2) DEFAULT NULL,
    forearm_circumference decimal(38,2) DEFAULT NULL,
    head_circumference decimal(38,2) DEFAULT NULL,
    hip_circumference decimal(38,2) DEFAULT NULL,
    inside_leg_length decimal(38,2) DEFAULT NULL,
    neck_circumference decimal(38,2) DEFAULT NULL,
    shoulder_breadth decimal(38,2) DEFAULT NULL,
    shoulder_to_crotch decimal(38,2) DEFAULT NULL,
    thigh_circumference decimal(38,2) DEFAULT NULL,
    waist_circumference decimal(38,2) DEFAULT NULL,
    wrist_circumference decimal(38,2) DEFAULT NULL,

	foreign key (customer_id) references customer(customer_id),
	primary key (id)
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





-- Creating product_shoppingCart table
create table product_shopping_cart(
	product_id VARCHAR(20) NOT NULL,
	cart_id int not null,
    product_color VARCHAR(20) NOT NULL,
    product_size VARCHAR(20) NOT NULL,
    product_quantity  VARCHAR(20) NOT NULL,
	primary key(product_id,cart_id),
	constraint FK_products foreign key (product_id) references product(product_id),
	constraint FK_cart foreign key (cart_id) references shopping_cart(cart_id)
);




-- Creating address table
CREATE TABLE address(
	address_id int AUTO_INCREMENT NOT NULL,
	customer_id int NOT NULL,
    company VARCHAR(40) NOT NULL,
	city VARCHAR(40) NOT NULL,
	street VARCHAR(40) NOT NULL,
	address_name VARCHAR(30) NOT NULL,
    region VARCHAR(30) NOT NULL,
    postal_code VARCHAR(30) NOT NULL,
	PRIMARY KEY (address_id),
    constraint FK_customer_address foreign key (customer_id) references customer(customer_id)
);



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
	address_id VARCHAR(150) NOT NULL,
	shipment_status VARCHAR(150) NOT NULL,
	shipment_date VARCHAR(30) NOT NULL,
	PRIMARY KEY (shipping_id,address_id),
	constraint FK_shipments foreign key (address_id) references address(address_id)
);

CREATE TABLE orders(
	order_id VARCHAR(30) NOT NULL,
	customer_id int,
	cart_id int,
	shipping_id VARCHAR(30),
	order_date VARCHAR(30) NOT NULL,
	total DECIMAL(10, 2) DEFAULT NULL,
    sub_total DECIMAL(10, 2) DEFAULT NULL,
    taxes DECIMAL(10, 2) DEFAULT NULL,
    shipping DECIMAL(10, 2) DEFAULT NULL,
    email VARCHAR(30),
    phone VARCHAR(30),


	PRIMARY KEY (order_id),
	constraint FK_orders foreign key (customer_id) references customer(customer_id) on delete set null on update cascade,
	constraint FK_orders_shopping_cart foreign key (cart_id) references shopping_cart(cart_id) on delete set null on update cascade,
	constraint FK_orders_shipments foreign key (shipping_id) references shipments(shipping_id) on delete set null on update cascade
);




-- Creating payment table
CREATE TABLE payment (
	payment_no VARCHAR(30) NOT NULL,
    order_id VARCHAR(30) NOT NULL,
	payment_method VARCHAR(100) NOT NULL,
	payment_details VARCHAR(150) NOT NULL,
	PRIMARY KEY (payment_no,order_id),
    constraint FK_order_payment foreign key (order_id) references orders(order_id)
);

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





-- Creating product_shoppingCart table
create table product_shopping_cart(
        product_id VARCHAR(20) NOT NULL,
        cart_id int not null,
    product_color VARCHAR(20) NOT NULL,
    product_size VARCHAR(20) NOT NULL,
    product_quantity  VARCHAR(20) NOT NULL,
        primary key(product_id,cart_id),
        constraint FK_products foreign key (product_id) references product(product_id),
        constraint FK_cart foreign key (cart_id) references shopping_cart(cart_id)
);




-- Creating address table
CREATE TABLE address(
        address_id int AUTO_INCREMENT NOT NULL,
        customer_id int NOT NULL,
    company VARCHAR(40) NOT NULL,
        city VARCHAR(40) NOT NULL,
        street VARCHAR(40) NOT NULL,
        address_name VARCHAR(30) NOT NULL,
    region VARCHAR(30) NOT NULL,
    postal_code VARCHAR(30) NOT NULL,
        PRIMARY KEY (address_id),
    constraint FK_customer_address foreign key (customer_id) references customer(customer_id)
);



-- Creating shipper table
CREATE TABLE shipper(
        shipper_id int AUTO_INCREMENT NOT NULL,
        shipper_name VARCHAR(100) NOT NULL,
        contact_no VARCHAR(20) NOT NULL,
        PRIMARY KEY (shipper_id)
);


-- Creating shipment table
CREATE TABLE shipments (
        shipping_id int AUTO_INCREMENT NOT NULL,
        address_id int NOT NULL,
        shipment_status VARCHAR(150) NOT NULL,
        shipment_date VARCHAR(30) NOT NULL,
        PRIMARY KEY (shipping_id,address_id),
        constraint FK_shipments foreign key (address_id) references address(address_id)
);

CREATE TABLE orders(
                       order_id int AUTO_INCREMENT NOT NULL,
                       customer_id int,
                       cart_id int,
                       shipping_id int,
                       order_date VARCHAR(30) NOT NULL,
                       total DECIMAL(10, 2) DEFAULT NULL,
                       sub_total DECIMAL(10, 2) DEFAULT NULL,
                       taxes DECIMAL(10, 2) DEFAULT NULL,
                       shipping DECIMAL(10, 2) DEFAULT NULL,
                       email VARCHAR(30),
                       phone VARCHAR(30),


                       PRIMARY KEY (order_id),
                       constraint FK_orders foreign key (customer_id) references customer(customer_id) on delete set null on update cascade,
                       constraint FK_orders_shopping_cart foreign key (cart_id) references shopping_cart(cart_id) on delete set null on update cascade,
                       constraint FK_orders_shipments foreign key (shipping_id) references shipments(shipping_id) on delete set null on update cascade
);




-- Creating payment table
CREATE TABLE payment (
                         payment_no int AUTO_INCREMENT NOT NULL,
                         order_id int NOT NULL,
                         payment_method VARCHAR(100) NOT NULL,
                         payment_details VARCHAR(150) NOT NULL,
                         PRIMARY KEY (payment_no,order_id),
                         constraint FK_order_payment foreign key (order_id) references orders(order_id)
);

CREATE TABLE shipments (
                           shipping_id int NOT NULL,
                           address_id int NOT NULL,
                           shipment_status VARCHAR(150) NOT NULL,
                           shipment_date VARCHAR(30) NOT NULL,
                           PRIMARY KEY (shipping_id,address_id),
                           constraint FK_shipments foreign key (address_id) references address(address_id)
);