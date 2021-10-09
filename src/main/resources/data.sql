create TABLE TBL_CUSTOMER
(  
phoneNo BIGINT PRIMARY KEY,
name VARCHAR(30), 
email VARCHAR(50),
address VARCHAR(15)
);

INSERT INTO TBL_CUSTOMER (phoneNo,name,email,address)
VALUES ( '9474758','saumya','myemail@in.com','667 lko');

select * from TBL_CUSTOMER