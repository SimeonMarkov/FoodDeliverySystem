DROP SCHEMA IF EXISTS fd_db;

CREATE SCHEMA fd_db COLLATE = utf8_general_ci;

USE fd_db;

/* *************************************************************** 
***************************CREATING TABLES************************
**************************************************************** */
CREATE TABLE neighbourhood (
	neighbourhood_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	neighbourhood_name VARCHAR(50),
	PRIMARY KEY (neighbourhood_id)
	);
    


CREATE TABLE users (
	user_id VARCHAR(40) NOT NULL, /* tova e username-a */
    user_pass VARCHAR(40) NOT NULL,
    email VARCHAR(50) NOT NULL,
    secret_question VARCHAR(200) NOT NULL,
    secret_answer VARCHAR(200) NOT NULL,
	PRIMARY KEY (user_id)
	);

CREATE TABLE address (
    address_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	full_address VARCHAR(250),
    neighbourhood_id INT(5) UNSIGNED NOT NULL,
    user_id VARCHAR(40),
	PRIMARY KEY (address_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (neighbourhood_id) REFERENCES neighbourhood(neighbourhood_id)
	);

CREATE TABLE meal_type (
	meal_type_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	meal_type_name VARCHAR(50),
	PRIMARY KEY (meal_type_id)
	);

CREATE TABLE ingredients (
	ingredients_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	ingredients_name VARCHAR(50),
	PRIMARY KEY (ingredients_id)
	);

 CREATE TABLE meal (
	meal_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	meal_name VARCHAR(50),
    price DECIMAL(6,2),
    meal_type_id INT (5) UNSIGNED NOT NULL,
    photo BLOB,
	PRIMARY KEY (meal_id),
    CONSTRAINT c_meal_type_id FOREIGN KEY (meal_type_id) REFERENCES meal_type(meal_type_id)
	);
    
CREATE TABLE meal_ingredients (
	meal_id INT(5) UNSIGNED NOT NULL, 
    ingredients_id INT (5) UNSIGNED NOT NULL,
	CONSTRAINT c_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
    CONSTRAINT c_ingredients_id FOREIGN KEY (ingredients_id) REFERENCES ingredients(ingredients_id),
    index (meal_id)
	);
    
CREATE TABLE restaurant (
	restaurant_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	restaurant_name VARCHAR(50),
    phone_number VARCHAR(20),
    address_id INT (5) UNSIGNED NOT NULL,
    photo BLOB,
    PRIMARY KEY (restaurant_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
    );
    
CREATE TABLE serviced_neighbourhoods (
	restaurant_id INT (5) UNSIGNED NOT NULL,
    neighbourhood_id INT (5) UNSIGNED NOT NULL,
    FOREIGN KEY (neighbourhood_id) REFERENCES neighbourhood(neighbourhood_id),
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id),
    INDEX (neighbourhood_id) 
); 
CREATE TABLE restaurant_type (
	restaurant_type_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	restaurant_type_name VARCHAR(50),
	PRIMARY KEY (restaurant_type_id)
	);  
    
CREATE TABLE restaurant_by_type (
	restaurant_id INT (5) UNSIGNED NOT NULL,
    restaurant_type_id INT (5) UNSIGNED NOT NULL,
    FOREIGN KEY (restaurant_type_id) REFERENCES restaurant_type(restaurant_type_id),
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id),
    INDEX (restaurant_type_id)     
);

CREATE TABLE restaurant_menu (
	restaurant_id INT (5) UNSIGNED NOT NULL,
    meal_id INT (5) UNSIGNED NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id),
    INDEX (restaurant_id)     
);

CREATE TABLE orders (
	order_id INT (5) UNSIGNED NOT NULL AUTO_INCREMENT,
	order_time DATETIME,
    order_finished DATETIME,
    total_price DECIMAL(10,2),
    address_id INT (5) UNSIGNED NOT NULL,
    restaurant_id INT (5) UNSIGNED NOT NULL,
    user_id VARCHAR(40) NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id)
    );

CREATE TABLE ordered_meals (
	order_id INT (5) UNSIGNED NOT NULL,
    meal_id INT (5) UNSIGNED NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
	FOREIGN KEY (order_id) REFERENCES orders(order_id),
    INDEX (order_id)     
);
    

