CREATE TABLE MEMBERSHIP (
	FIRST_NAME VARCHAR(300) NOT NULL,
    MIDDLE_NAME CHAR(5),
    LAST_NAME VARCHAR(30)NOT NULL,
    EMAIL_ADDRESS VARCHAR(50) UNIQUE NOT NULL,
    STREET_ADDRESS VARCHAR(50) NOT NULL,
    CITY VARCHAR(20) NOT NULL,
    STATE VARCHAR(20) NOT NULL,
    POSTAL_CODE VARCHAR(10) NOT NULL,
    PHONE_NUMBER VARCHAR(15) PRIMARY KEY UNIQUE,
    AARP VARCHAR (5),
    DATE_OF_MEMBERSHIP DATE NOT NULL
    );