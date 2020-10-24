LOAD DATA INFILE 'C:\\usi-git\\SoftwareEngineeringTraining\\projects\\src\\main\\java\\com\\usi\\src\\resources\\Shoe.txt'
INTO TABLE SHOE
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 2 ROWS
(PRODUCT_NAME , DEPT_NAME , UNIT_OF_MEASURE , BARCODE , QUANTITY , SIZE , BRAND_NAME , WEIGHT , @PRICE , COLOR , INGREDIENT , NUMBER_UNITS_IN_STOCK , DESCRIPTION_OF_PRODUCT)
SET PRICE = TRIM(@PRICE);