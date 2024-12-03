
CREATE TABLE order_items (
id BIGSERIAL PRIMARY KEY,
book_id BIGINT NOT NULL REFERENCES books,
quantity INT,
price NUMERIC,
order_id BIGINT NOT NULL REFERENCES orders);

INSERT INTO order_items (book_id,quantity,price,order_id) VALUES (5,2,24.67,2)
INSERT INTO order_items (book_id,quantity,price,order_id) VALUES (6,3,20,2)


