-- Create a new user into the database 
CREATE USER 'test'@'%' IDENTIFIED  BY  '***';
 

-- Grant access to the user 
 GRANT  SELECT ,
INSERT ,
 
UPDATE ,
 DELETE ,
 CREATE ,
 DROP ,
 FILE ,
 INDEX ,
 ALTER ,
 CREATE  TEMPORARY  TABLES ,
 CREATE  VIEW ,
EVENT,
TRIGGER,
 SHOW  VIEW ,
 CREATE ROUTINE,
 ALTER ROUTINE,
 EXECUTE  ON  *  .  *  TO  'test'@'%' IDENTIFIED  BY  '***' WITH  MAX_QUERIES_PER_HOUR 0  MAX_CONNECTIONS_PER_HOUR 0  MAX_UPDATES_PER_HOUR 0  MAX_USER_CONNECTIONS 0 ; 

 -- Create the new database and Table .

CREATE  DATABASE  `seamdb` ;


 create table ledger (
 ID INT NOT NULL AUTO_INCREMENT,
 rcv_dt date,
 mbr_nm VARCHAR(100) not null,
 chk_nbr VARCHAR(10) not null, 
 chk_dt date,
 pymt_typ VARCHAR(50) not null,
 dpst_amt double,
 pymt_amt double,
 comments VARCHAR(100),
 PRIMARY KEY (ID)
 );