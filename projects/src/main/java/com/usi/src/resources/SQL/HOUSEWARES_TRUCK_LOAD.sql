LOAD DATA INFILE 'C:\\usi-git\\SoftwareEngineeringTraining\\projects\\src\\main\\java\\com\\usi\\src\\resources\\Housewares.txt'
INTO TABLE HOUSEWARES
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 4 ROWS
(PRODUCT_NAME , DEPT_NAME , UNIT_OF_MEASURE , BARCODE , QUANTITY , SIZE , BRAND_NAME , WEIGHT , PRICE , COLOR , INGREDIENT , NUMBER_UNITS_IN_STOCK , DESCRIPTION_OF_PRODUCT);