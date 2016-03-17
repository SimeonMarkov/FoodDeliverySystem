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
	restaurant_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    address_id INT (5) UNSIGNED NOT NULL,
    email VARCHAR(30) NOT NULL,
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

INSERT INTO meal_type values (1,'Пици');
INSERT INTO meal_type values (2,'Салати');
INSERT INTO meal_type values (3,'Паста');
INSERT INTO meal_type values (4,'Аламинути');
INSERT INTO meal_type values (5,'Супи');
INSERT INTO meal_type values (6,'BBQ');
INSERT INTO meal_type values (7,'Рибни ястия');
INSERT INTO meal_type values (8,'Пилешки ястия');
INSERT INTO meal_type values (9,'Свински ястия');
INSERT INTO meal_type values (10,'Десера');

INSERT INTO ingredients values (1,'Сирене');
INSERT INTO ingredients values (2,'Кашкавал');
INSERT INTO ingredients values (3,'Пушено сирене');
INSERT INTO ingredients values (4,'Гъби');
INSERT INTO ingredients values (5,'Лук');
INSERT INTO ingredients values (6,'Сметана');
INSERT INTO ingredients values (7,'Домати');
INSERT INTO ingredients values (8,'Доматен сос');
INSERT INTO ingredients values (9,'Пушено пиле');
INSERT INTO ingredients values (10,'Шунка');
INSERT INTO ingredients values (11,'Царевица');
INSERT INTO ingredients values (12,'Кисели краставички');
INSERT INTO ingredients values (13,'Маслини');
INSERT INTO ingredients values (14,'Бекон');
INSERT INTO ingredients values (15,'Луканка');
INSERT INTO ingredients values (16,'Зеле');
INSERT INTO ingredients values (17,'Ориз');
INSERT INTO ingredients values (18,'Суджук');
INSERT INTO ingredients values (19,'Риба');
INSERT INTO ingredients values (20,'Пуешко');

INSERT INTO meal values (1,'Пица Пеперони',8.50,1,null);
INSERT INTO meal_ingredients values (1,2);
INSERT INTO meal_ingredients values (1,8);
INSERT INTO meal_ingredients values (1,14);
INSERT INTO meal_ingredients values (1,5);
INSERT INTO meal values (2,'Пица Тоскана',8.30,1,null);
INSERT INTO meal_ingredients values (2,2);
INSERT INTO meal_ingredients values (2,8);
INSERT INTO meal_ingredients values (2,14);
INSERT INTO meal_ingredients values (2,11);
INSERT INTO meal_ingredients values (2,12);
INSERT INTO meal values (3,'Пица Милано',9.20,1,null);
INSERT INTO meal_ingredients values (3,2);
INSERT INTO meal_ingredients values (3,8);
INSERT INTO meal_ingredients values (3,9);
INSERT INTO meal_ingredients values (3,11);
INSERT INTO meal_ingredients values (3,12);
INSERT INTO meal values (4,'Пица Маргарита',6.20,1,null);
INSERT INTO meal_ingredients values (4,2);
INSERT INTO meal_ingredients values (4,8);
INSERT INTO meal values (5,'Пица Рома',8.20,1,null);
INSERT INTO meal_ingredients values (5,2);
INSERT INTO meal_ingredients values (5,8);
INSERT INTO meal_ingredients values (5,10);
INSERT INTO meal_ingredients values (5,11);
INSERT INTO meal_ingredients values (5,12);
INSERT INTO meal_ingredients values (5,5);
INSERT INTO meal values (6,'Пица Верди',8.70,1,null);
INSERT INTO meal_ingredients values (6,2);
INSERT INTO meal_ingredients values (6,8);
INSERT INTO meal_ingredients values (6,12);
INSERT INTO meal_ingredients values (6,14);
INSERT INTO meal_ingredients values (6,4);
INSERT INTO meal_ingredients values (6,6);
INSERT INTO meal values (7,'Пица Примавера',7.60,1,null);
INSERT INTO meal_ingredients values (7,2);
INSERT INTO meal_ingredients values (7,8);
INSERT INTO meal_ingredients values (7,12);
INSERT INTO meal_ingredients values (7,16);
INSERT INTO meal_ingredients values (7,14);
INSERT INTO meal values (8,'Спагети Поло',9.20,3,null);
INSERT INTO meal_ingredients values (8,2);
INSERT INTO meal_ingredients values (8,8);
INSERT INTO meal_ingredients values (8,12);
INSERT INTO meal_ingredients values (8,16);
INSERT INTO meal values (9,'Спагети Вегетариана',7.70,3,null);
INSERT INTO meal_ingredients values (9,2);
INSERT INTO meal_ingredients values (9,8);
INSERT INTO meal_ingredients values (9,12);
INSERT INTO meal_ingredients values (9,16);
INSERT INTO meal_ingredients values (9,5);
INSERT INTO meal values (10,'Спагети Болонезе',8.40,3,null);
INSERT INTO meal_ingredients values (10,2);
INSERT INTO meal_ingredients values (10,8);
INSERT INTO meal_ingredients values (10,12);
INSERT INTO meal_ingredients values (10,14);
INSERT INTO meal values (11,'Спагети Корлеоне',8.50,3,null);
INSERT INTO meal_ingredients values (11,2);
INSERT INTO meal_ingredients values (11,8);
INSERT INTO meal_ingredients values (11,12);
INSERT INTO meal_ingredients values (11,18);
INSERT INTO meal values (12,'Салата Снежанка',2.60,2,null);
INSERT INTO meal_ingredients values (12,10);
INSERT INTO meal_ingredients values (12,13);
INSERT INTO meal_ingredients values (12,3);
INSERT INTO meal values (13,'Шопска салата',2.80,2,null);
INSERT INTO meal_ingredients values (13,10);
INSERT INTO meal_ingredients values (13,13);
INSERT INTO meal_ingredients values (13,3);
INSERT INTO meal values (14,'Родопска салата',3.00,2,null);
INSERT INTO meal_ingredients values (14,10);
INSERT INTO meal_ingredients values (14,13);
INSERT INTO meal_ingredients values (14,3);
INSERT INTO meal values (15,'Салата Зеле и Моркови',2.70,2,null);
INSERT INTO meal_ingredients values (15,19);
INSERT INTO meal_ingredients values (15,12);
INSERT INTO meal_ingredients values (15,6);
INSERT INTO meal values (16,'Салата Цезар',3.70,2,null);
INSERT INTO meal_ingredients values (16,1);
INSERT INTO meal_ingredients values (16,12);
INSERT INTO meal_ingredients values (16,7);
INSERT INTO meal_ingredients values (16,14);
INSERT INTO meal values (17,'Таратор',1.80,5,null);
INSERT INTO meal_ingredients values (17,11);
INSERT INTO meal_ingredients values (17,2);
INSERT INTO meal_ingredients values (17,17);
INSERT INTO meal_ingredients values (17,4);
INSERT INTO meal values (18,'Пилешка Супа',2.40,5,null);
INSERT INTO meal_ingredients values (18,17);
INSERT INTO meal_ingredients values (18,4);
INSERT INTO meal values (19,'Картофена Крем Супа',2.20,5,null);
INSERT INTO meal_ingredients values (19,7);
INSERT INTO meal_ingredients values (19,14);
INSERT INTO meal values (20,'Ризото с Пиле',4.30,4,null);
INSERT INTO meal_ingredients values (20,1);
INSERT INTO meal_ingredients values (20,12);
INSERT INTO meal_ingredients values (20,18);
INSERT INTO meal_ingredients values (20,19);
INSERT INTO meal values (21,'Сирене по Шопски',3.20,4,null);
INSERT INTO meal_ingredients values (21,1);
INSERT INTO meal_ingredients values (21,12);
INSERT INTO meal_ingredients values (21,18);
INSERT INTO meal values (22,'Хрупкави пилешки филенца',5.40,4,null);
INSERT INTO meal_ingredients values (22,6);
INSERT INTO meal_ingredients values (22,16);
INSERT INTO meal_ingredients values (22,4);
INSERT INTO meal values (23,'Пастърва',7.50,7,null);
INSERT INTO meal_ingredients values (23,7);
INSERT INTO meal_ingredients values (23,8);
INSERT INTO meal_ingredients values (23,9);
INSERT INTO meal values (24,'Палачинки',1.20,10,null);
INSERT INTO meal_ingredients values (24,20);
INSERT INTO meal_ingredients values (24,2);
INSERT INTO meal_ingredients values (24,1);
INSERT INTO meal values (25,'Запечено свинско бонфиле',8.50,9,null);
INSERT INTO meal_ingredients values (25,14);
INSERT INTO meal_ingredients values (25,12);
INSERT INTO meal_ingredients values (25,17);
INSERT INTO meal values (26,'Запечен свински врат',7.20,9,null);
INSERT INTO meal_ingredients values (26,5);
INSERT INTO meal_ingredients values (26,11);
INSERT INTO meal_ingredients values (26,17);
INSERT INTO meal values (27,'Пиле със зеленчуци и сметана',6.20,8,null);
INSERT INTO meal_ingredients values (27,5);
INSERT INTO meal_ingredients values (27,2);
INSERT INTO meal_ingredients values (27,8);
INSERT INTO meal_ingredients values (27,17);
INSERT INTO meal values (28,'Кебапче',1.20,6,null);
INSERT INTO meal_ingredients values (28,20);
INSERT INTO meal values (29,'Свинско Шишче',2.20,6,null);
INSERT INTO meal_ingredients values (29,20);
INSERT INTO meal values (30,'Кюфте',1.20,6,null);
INSERT INTO meal_ingredients values (30,20);
INSERT INTO meal values (31,'Карначе',1.70,6,null);
INSERT INTO meal_ingredients values (31,20);




insert into users values ('gosho','123456','email@qko.com','koi','az');
insert into users values ('rado','123456','email@basiqko.com','koi','az');
insert into users values ('simo','123456','megaemail@basiqko.com','koi','az');

insert into neighbourhood values (1,'Борово');
insert into neighbourhood values (2,'Гоце Делчев');
insert into neighbourhood values (3,'Бъкстон');
insert into neighbourhood values (4,'Манастирски ливади');
insert into neighbourhood values (5,'Бояна');
insert into neighbourhood values (6,'Бели Брези');
insert into neighbourhood values (7,'Иван Вазов');
insert into neighbourhood values (8,'Хиподрума');
insert into neighbourhood values (9,'Лагера');
insert into neighbourhood values (10,'Красно Село');
insert into neighbourhood values (11,'Красна Поляна');
insert into neighbourhood values (12,'Овча Купел');
insert into neighbourhood values (13,'Лозенец');
insert into neighbourhood values (14,'Оборище');
insert into neighbourhood values (15,'Зона Б5');
insert into neighbourhood values (16,'Зона Б19');
insert into neighbourhood values (17,'Дружбай');


insert into address values (1,'Някакъв тест',1,'rado');
insert into address values (2,'Някакъв тест',2,'simo');
insert into address values (3,'Някакъв тест',3,'gosho');
insert into address values (4,'Някакъв тест',4,null);
insert into address values (5,'Някакъв тест',5,'simo');
insert into address values (6,'Някакъв тест',1,null);
insert into address values (7,'Някакъв тест',2,'gosho');
insert into address values (8,'Някакъв тест',12,'rado');
insert into address values (9,'Някакъв тест',8,null);
insert into address values (10,'Някакъв тест',17,'rado');


insert into restaurant values (1,'Дон Вито','45454',9,'mn@qko.com',null);
insert into serviced_neighbourhoods values(1,4);
insert into serviced_neighbourhoods values(1,1);
insert into serviced_neighbourhoods values(1,2);
insert into serviced_neighbourhoods values(1,7);
insert into serviced_neighbourhoods values(1,8);
insert into restaurant_menu values(1,1);
insert into restaurant_menu values(1,2);
insert into restaurant_menu values(1,3);
insert into restaurant_menu values(1,4);
insert into restaurant_menu values(1,6);
insert into restaurant_menu values(1,7);
insert into restaurant_menu values(1,31);
insert into restaurant_menu values(1,30);
insert into restaurant_menu values(1,21);
insert into restaurant_menu values(1,22);

insert into restaurant values (2,'Тукан','45454',6,'mn@qko.com',null);
insert into serviced_neighbourhoods values(2,1);
insert into serviced_neighbourhoods values(2,2);
insert into serviced_neighbourhoods values(2,3);
insert into serviced_neighbourhoods values(2,5);
insert into serviced_neighbourhoods values(2,8);
insert into serviced_neighbourhoods values(2,9);
insert into serviced_neighbourhoods values(2,10);
insert into restaurant_menu values(2,1);
insert into restaurant_menu values(2,2);
insert into restaurant_menu values(2,4);
insert into restaurant_menu values(2,5);
insert into restaurant_menu values(2,8);
insert into restaurant_menu values(2,9);
insert into restaurant_menu values(2,11);
insert into restaurant_menu values(2,14);
insert into restaurant_menu values(2,17);
insert into restaurant_menu values(2,21);
insert into restaurant_menu values(2,22);
insert into restaurant_menu values(2,27);
insert into restaurant_menu values(2,31);
insert into restaurant_menu values(2,7);
insert into restaurant_menu values(2,16);

insert into restaurant values (3,'Якото','45454',4,'mn@qko.com',null);
insert into serviced_neighbourhoods values(3,8);
insert into serviced_neighbourhoods values(3,1);
insert into serviced_neighbourhoods values(3,6);
insert into serviced_neighbourhoods values(3,5);
insert into serviced_neighbourhoods values(3,7);
insert into serviced_neighbourhoods values(3,8);
insert into restaurant_menu values(3,1);
insert into restaurant_menu values(3,2);
insert into restaurant_menu values(3,5);
insert into restaurant_menu values(3,7);
insert into restaurant_menu values(3,10);
insert into restaurant_menu values(3,9);
insert into restaurant_menu values(3,12);
insert into restaurant_menu values(3,13);
insert into restaurant_menu values(3,19);
insert into restaurant_menu values(3,23);
insert into restaurant_menu values(3,25);
insert into restaurant_menu values(3,29);
insert into restaurant_menu values(3,20);
insert into restaurant_menu values(3,24);


insert into orders values (1,'2016-02-09','2016-03-09',50,1,1,'rado');
insert into ordered_meals value (1,1);
insert into ordered_meals value (1,5);
insert into ordered_meals value (1,8);

insert into orders values (2,'2016-05-09','2016-03-09',50,1,1,'rado');
insert into ordered_meals value (2,2);
insert into ordered_meals value (2,9);

insert into orders values (3,'2016-03-09','2016-03-09',50,1,1,'rado');
insert into ordered_meals value (3,10);
insert into ordered_meals value (3,3);
insert into ordered_meals value (3,30);
insert into ordered_meals value (3,24);

insert into orders values (4,'2016-03-09','2016-03-09',50,1,1,'gosho');
insert into ordered_meals value (4,15);

insert into orders values (5,'2016-01-09','2016-03-09',50,1,1,'simo');
insert into ordered_meals value (5,13);
insert into ordered_meals value (5,18);

insert into orders values (6,'2016-07-09','2016-03-09',50,1,1,'simo');
insert into ordered_meals value (6,21);

insert into orders values (7,'2016-04-09','2016-03-09',50,1,1,'simo');
insert into ordered_meals value (7,25);
insert into ordered_meals value (7,6);
insert into ordered_meals value (7,29);
insert into ordered_meals value (7,31);





INSERT into restaurant_type values(1,'Пицария');
INSERT into restaurant_type values(2,'Скара');
INSERT into restaurant_type values(3,'Китайско');
INSERT into restaurant_type values(4,'Готвено');
INSERT into restaurant_type values(5,'Сладкарница');


INSERT into restaurant_by_type values(1,1);
INSERT into restaurant_by_type values(1,2);
INSERT into restaurant_by_type values(1,4);
INSERT into restaurant_by_type values(2,1);
INSERT into restaurant_by_type values(2,2);
INSERT into restaurant_by_type values(2,4);
INSERT into restaurant_by_type values(3,1);
INSERT into restaurant_by_type values(3,2);
INSERT into restaurant_by_type values(3,4);
INSERT into restaurant_by_type values(3,5);

