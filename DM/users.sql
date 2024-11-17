
CREATE TABLE roles (
id BIGSERIAL PRIMARY KEY,
value VARCHAR(50));

INSERT INTO roles (value) VALUES
('ADMIN'),
('USER'),
('MANAGER');

CREATE TABLE users (
id BIGSERIAL PRIMARY KEY,
surName VARCHAR(255) ,
name VARCHAR(255) ,
lastName VARCHAR(255) ,
email VARCHAR(75) UNIQUE NOT NULL,
password VARCHAR(75) NOT NULL,
role BIGINT NOT NULL REFERENCES roles);

INSERT INTO users (surName,name,lastName,email,password,role) VALUES
('surName1','name1','lastName1','email1@domein.com','password1',(SELECT id FROM roles WHERE value = 'ADMIN')),
('surName2','name2','lastName3','email2@domein.com','password2',(SELECT id FROM roles WHERE value = 'USER')),
('surName3','name3','lastName2','email3@domein.com','password3',(SELECT id FROM roles WHERE value = 'USER')),
('surName4','name3','lastName3','email4@domein.com','password4',(SELECT id FROM roles WHERE value = 'MANAGER')),
('surName5','name5','lastName4','email5@domein.com','password5',(SELECT id FROM roles WHERE value = 'MANAGER')),
('surName6','name4','lastName6','email6@domein.com','password6',(SELECT id FROM roles WHERE value = 'USER')),
('surName7','name5','lastName7','email7@domein.com','password7',(SELECT id FROM roles WHERE value = 'MANAGER')),
('surName5','name7','lastName1','email8@domein.com','password8',(SELECT id FROM roles WHERE value = 'USER')),
('surName3','name3','lastName2','email9@domein.com','password9',(SELECT id FROM roles WHERE value = 'USER')),
('surName2','name4','lastName3','email10@domein.com','password10',(SELECT id FROM roles WHERE value = 'USER')),
('surName1','name1','lastName4','email11@domein.com','password11',(SELECT id FROM roles WHERE value = 'USER'));
