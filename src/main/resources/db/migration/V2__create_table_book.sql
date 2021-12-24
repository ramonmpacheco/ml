CREATE TABLE book (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  price decimal(10,2) NOT NULL,
  status varchar(255) NOT NULL,
  id_customer int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_customer) REFERENCES customer(id)
);