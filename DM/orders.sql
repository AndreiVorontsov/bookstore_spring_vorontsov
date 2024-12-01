
CREATE TABLE statuses (
id BIGSERIAL PRIMARY KEY,
value VARCHAR(50));

INSERT INTO statuses (value) VALUES
('PANDING'),
('PAID'),
('DELIVERD'),
('CANCELED');

CREATE TABLE orders (
id BIGSERIAL PRIMARY KEY,
date TIMESTAMP,
user_id BIGINT NOT NULL REFERENCES users,
status BIGINT NOT NULL REFERENCES statuses,
price DECIMAL);


INSERT INTO orders (date,user_id,status,price) VALUES ('2024-12-01T18:46:05.967682',5,(SELECT id FROM statuses WHERE value = 'PANDING'),44.67)

UPDATE orders SET date = '2024-12-01T18:48:05.967682', user_id = 5, status = (SELECT id FROM statuses WHERE value = 'DELIVERD'), price = 60 where id = 1