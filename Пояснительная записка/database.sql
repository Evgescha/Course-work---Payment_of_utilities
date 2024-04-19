DROP DATABASE IF EXISTS payment_of_utilities;
CREATE DATABASE payment_of_utilities;
USE payment_of_utilities;

CREATE TABLE service(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(150) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE tenant(
	id INT NOT NULL AUTO_INCREMENT,
	fio VARCHAR(200) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE payment(
	id INT NOT NULL AUTO_INCREMENT,
	dates DATE NOT NULL,
	tenant INT NOT NULL,
	service INT NOT NULL,
	summ FLOAT NOT NULL,
	fine FLOAT,
	PRIMARY KEY(id),
	FOREIGN KEY(tenant) REFERENCES tenant(id),
	FOREIGN KEY(service) REFERENCES service(id)
);