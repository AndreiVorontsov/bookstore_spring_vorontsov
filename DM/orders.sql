
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

