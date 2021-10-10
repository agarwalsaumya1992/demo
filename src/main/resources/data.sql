DROP TABLE TBL_CUSTOMER;

create TABLE TBL_CUSTOMER
(  
id SERIAL PRIMARY KEY,
phoneNo VARCHAR(10),
name VARCHAR(30), 
email VARCHAR(50),
address VARCHAR(50)
);

INSERT INTO TBL_CUSTOMER (phoneNo,name,email,address)
VALUES ( '9474758','saumya','myemail@in.com','667 lko');

select * from TBL_CUSTOMER;
