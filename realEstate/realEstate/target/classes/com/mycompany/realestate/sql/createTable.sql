-- Yassine Ibhir

use realestate;

-- create insurance table

create table insurance
	(insurance_id int NOT NULL,
    name varchar(30),
    annual_payment decimal,
    address varchar(50),
    phone varchar(50),
    PRIMARY KEY (insurance_id));
    
-- create property table

create table property
	(property_id int NOT NULL,
    address varchar(50),
    property_type varchar(10),
    rent_amount decimal,
    school_tax decimal,
    property_tax decimal,
    vacant varchar(5),
    insurance_id int NOT NULL,
    unit_number int,
    PRIMARY KEY (property_id),
    FOREIGN KEY (insurance_id) REFERENCES insurance(insurance_id));
    
-- create Contractor table
create table contractor
	(contractor_id int NOT NULL,
    name varchar(20),
    specialization varchar(40),
	address varchar(50),
    phone varchar(50),
    PRIMARY KEY (contractor_id));
    
    
-- create property table
create table repairs
	(repair_id int NOT NULL,
    repair_type varchar(20),
    repair_cost decimal,
    start_date datetime,
    end_date datetime,
    property_id int NOT NULL,
    contractor_id int NOT NULL,
    PRIMARY KEY (repair_id),
    FOREIGN KEY (property_id) REFERENCES property(property_id),
    FOREIGN KEY (contractor_id) REFERENCES contractor(contractor_id));
  -- create Bank table
create table bank
	(bank_id int NOT NULL,
    name varchar(20),
    intrest_rate double,
	address varchar(50),
    phone varchar(50),
    PRIMARY KEY (bank_id));
select repair_id,property_id,contractor_id from repairs
	group by property_id;
-- create mortgage table
create table mortgage
	(mortgage_id int NOT NULL,
    mortgage_term int,
    mortgage_amount decimal,
    start_date datetime,
    down_payment decimal,
    fully_paid varchar(5),
    property_id int NOT NULL,
    bank_id int NOT NULL,
    PRIMARY KEY (mortgage_id),
    FOREIGN KEY (property_id) REFERENCES property(property_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id));
    
   
-- create utility table
create table utility
	(utility_id int NOT NULL,
    utility_amount decimal,
    payment_date datetime,
    property_id int NOT NULL,
    PRIMARY KEY (utility_id),
    FOREIGN KEY (property_id) REFERENCES property(property_id));
   
select * from repairs;
-- create table tenant 
create table tenant
	(tenant_id int NOT NULL,
    driver_licence_id varchar(20),
    full_name varchar(20),
    email_address varchar(50),
    phone varchar(50),
    PRIMARY KEY (tenant_id));

-- create lease table
create table lease
	(lease_id int NOT NULL,
	lease_term int,
    start_date datetime,
    renewal varchar(5),
    property_id int NOT NULL,
    tenant_id int NOT NULL,
    PRIMARY KEY (lease_id),
    FOREIGN KEY (property_id) REFERENCES property(property_id),
    FOREIGN KEY (tenant_id) REFERENCES tenant(tenant_id));    

-- create table rent
create table rent
	(rent_id int NOT NULL,
    amount_paid decimal,
    payment_method varchar(20),
    payment_date datetime,
    fully_paid varchar(5),
    lease_id int NOT NULL,
    PRIMARY KEY (rent_id),
    FOREIGN KEY (lease_id) REFERENCES lease(lease_id));   
    
--    drop table rent; 
--    drop table lease;
--    drop table tenant;
--    drop table mortgage;
--    drop table bank ;
--    drop table repairs;
--    drop table utility;
--    drop table property; 
--    drop table insurance;
--    drop table contractor;
    