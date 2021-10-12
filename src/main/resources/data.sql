DROP TABLE TBL_CUSTOMER;

create TABLE TBL_CUSTOMER
(  
id SERIAL PRIMARY KEY,
phoneNo VARCHAR(10),
name VARCHAR(30), 
email VARCHAR(50),
address VARCHAR(50),
photo VARCHAR(64)
);

INSERT INTO TBL_CUSTOMER (phoneNo,name,email,address,photo)
VALUES ( '8888222212','saumya','myemail@in.com','667 lko','photo.jpg');

select * from TBL_CUSTOMER;

create table TBL_FILE(
filename VARCHAR(64),
filedata bytea
);

select * from TBL_FILE;
