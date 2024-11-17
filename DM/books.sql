CREATE TABLE books (
id SERIAL PRIMARY KEY,
name VARCHAR(255) ,
author VARCHAR(255),
isbn VARCHAR(13) UNIQUE NOT NULL,
cover VARCHAR(10),
price DECIMAL(10, 2),
year_publication INT);


INSERT INTO books ( name, author, isbn, cover, price, year_publication) VALUES
('Harry Potter and the Sorcerers Stone' ,'J. K. Rowling', '2349845739385', 'HARD', 9.45, 1985),
('Harry Potter and the Chamber of Secrets' ,'J. K. Rowling', '2349846729385', 'SPECIAL', 34.56, 1999),
('The Woman in Me' ,'Britney Spears', '2349841729385', 'SOFT', 10.99, 2015),
('Atomic Habits' ,'James Clear', '2349845829385', 'SPECIAL', 45.99, 2013),
('Ugly Love' ,'Colleen Hoover', '2549845829385', 'HARD', 65.01, 2021);