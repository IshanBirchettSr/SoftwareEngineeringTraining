LOAD DATA INFILE 'C:\\usi-git\\SoftwareEngineeringTraining\\projects\\src\\main\\java\\com\\usi\\src\\resources\\MembershipCard.txt'
INTO TABLE MEMBERSHIP
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 3 ROWS
(first_name, middle_name, last_name, email_address, street_address, city, state, postal_code, phone_number, aarp, @date_of_membership)
SET date_of_membership = str_to_date(@date_of_membership, '%m/%d/%Y');