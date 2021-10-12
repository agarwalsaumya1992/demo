DROP TABLE TBL_PRODUCT;

create TABLE TBL_PRODUCT
(  
id SERIAL PRIMARY KEY,
category VARCHAR(30),
name VARCHAR(50), 
price DECIMAL,
quantity INTEGER,
photo VARCHAR(64)
);

INSERT INTO TBL_PRODUCT (category,name,price,quantity)
VALUES ( 'women','shirt',459.00,5);

select * from TBL_PRODUCT;

DROP TABLE TBL_FILE;

create table TBL_FILE(
filename VARCHAR(64),
filedata bytea
);

select * from TBL_FILE;
