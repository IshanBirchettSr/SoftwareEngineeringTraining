CREATE TABLE SEAFOOD(
PRODUCT_NAME VARCHAR(40) NOT NULL , 
DEPT_NAME VARCHAR(30) NOT NULL,
UNIT_OF_MEASURE VARCHAR(10) NOT NULL,
BARCODE VARCHAR(10) NOT NULL,
QUANTITY INT ,
SIZE VARCHAR(50) NOT NULL ,
BRAND_NAME VARCHAR(30)  ,
WEIGHT VARCHAR(20) NOT NULL,
PRICE DOUBLE(6,2) , 
COLOR VARCHAR(10)NOT NULL ,
INGREDIENT VARCHAR(50) NOT NULL,
NUMBER_UNITS_IN_STOCK INT ,
DESCRIPTION_OF_PRODUCT VARCHAR(250) NOT NULL ,
PRIMARY KEY (PRODUCT_NAME, BRAND_NAME, WEIGHT)
);
