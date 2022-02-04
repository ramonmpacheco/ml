CREATE TABLE purchase (
  id int(11) NOT NULL AUTO_INCREMENT,
  customer_id int(11) NOT NULL,
  nfe varchar(255),
  price decimal(15,2) NOT NULL,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE purchase_book (
  purchase_id int(11) NOT NULL,
  book_id int(11) NOT NULL,
  FOREIGN KEY (purchase_id) REFERENCES purchase(id),
  FOREIGN KEY (book_id) REFERENCES book(id),
  PRIMARY KEY (purchase_id, book_id)
);