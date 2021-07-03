
-- insert random data into insurance table
insert into insurance values(1,'posix',1000,'5430 rue laval montreal','514,6795174');
insert into insurance values(2,'hoxaw',5000,'54333 rue laval montreal','514,6795174');
insert into insurance values(3,'posix',1000,'5430 rue laval montreal','514,6795174');
insert into insurance values(4,'hoxaw',5000,'54333 rue laval montreal','514,6795174');
insert into insurance values(5,'EXPENSIVE',10000,'54333 rue laval montreal','514,6795174');


-- insert random data in property table
insert into property 
values(1,'5430 rue property montreal','Plex',
null,2000,900,'true',1,null,null,4,null,null);

INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (6, 600, 'true',2,100,1); 
INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (7, 600, 'true',2,101,1);
INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (8, 600, 'true',2,102,1); 
INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (9, 600, 'true',2,103,1);

select * from property ;


insert into property 
values(2,'5430 rue property montreal','Condo',
1000.80,2000,900,'true',2,5,76.9,null,345,null);

insert into property 
values(3,'00000 rue property montreal','House',
1000.80,2000,900,'true',1,4,null,null,null,null);

insert into property 
values(4,'2299 rue property montreal','Plex',
null,2000,900,'true',1,null,null,4,null,null);
select * from property;
delete from property where property_id = 14;

INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (10, 800, 'true',2,100,4); 
INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (11, 800, 'true',2,101,4);
INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (12, 800, 'true',2,102,4); 
INSERT INTO property (property_id, rent_amount, vacant, rooms,appartment_number,plex_id)
VALUES (13, 800, 'true',2,103,4);

insert into property 
values(5,'1111 rue property montreal','House',
1000.80,2000,900,'true',1,4,null,null,null,null);

-- insert random data into plex_property table
-- insert into plex_property values(1,3,4,'false',1);
-- insert into plex_property values(2,5,3,'false',4);
-- insert into plex_property values(2,5,2,'false',5);


-- insert  random data into contractor table
insert into contractor values(1,'lunaInc','plomberie','2920 rue laval industrial','514,6795174'); 
insert into contractor values(2,'waterInc','Electricity','2920 rue laval decarie','514,6793241'); 
insert into contractor values(3,'fullInc','all','2920 rue broadcast','514,67911111');
insert into contractor values(4,'forrest','carpentry','2920 rue france','514,6795174');
insert into contractor values(5,'blueLife','painting','2920 rue france','514,6795174'); 
-- update contractor set name = 'forrest' where contractor_id = 4;   

-- insert  random data into repair table
insert into repairs
		values(1,'painting',500,'21/04/22','21/04/23',2,5);
insert into repairs
		values(2,'water leak',50,'21/04/22','21/04/23',2,1); 
insert into repairs
		values(3,'water leak',50,'21/04/22','21/04/23',1,2);
insert into repairs
		values(4,'floor',5000,'21/04/22','21/04/23',3,4);
insert into repairs
		values(5,'floor,painting',8000,'21/04/20','21/04/23',4,3);
insert into repairs
		values(6,'water leak,painting',8000,'21/04/20','21/04/23',5,3); 
                   
-- INSERT sample data into bank
insert into bank
		values(1,'TD',1.25,'2345 rue la fontaine','5146523214');
insert into bank
		values(2,'BMO',1.05,'2345 rue la gray','5146520023');
insert into bank
		values(3,'BN',1.22,'2345 rue la earnsclif','5146520014');
insert into bank
		values(4,'RBC',1.05,'2345 rue mansfield','5146522323');
insert into bank
		values(5,'LAURENTIENNE',1.77,'2345 rue MULTICAST','5146522323');

-- insert sample mortgages
insert into mortgage
		values(1,5,100000,'21/04/22',40000,'false',1,2);
insert into mortgage
		values(2,5,200000,'21/04/20',50000,'false',2,1);
insert into mortgage
		values(3,4,60000,'21/04/12',10000,'false',3,4);
insert into mortgage
		values(4,5,80000,'21/04/12',10200,'false',4,3);
insert into mortgage
		values(5,3,8000,'21/04/12',0,'false',5,5);

-- sample utility
    insert into utility
		values(1,100,'21/04/12',1);
    insert into utility
		values(2,50,'21/04/22',2);
    insert into utility
		values(3,50,'21/04/23',3);
    insert into utility
		values(4,80,'21/04/22',4);
    insert into utility
		values(5,60.99,'21/04/22',5);  

-- sample tenant
insert into tenant
		values(1,'ibgsb39293','Ibrahim loxi','iLoxi@gmail.com','4389038273');

insert into tenant
		values(2,'brgsb39293','bradley cooper','cooper@gmail.com','5149038273');
insert into tenant
		values(3,'ligsb39293','lise pointe','ploint@gmail.com','4389000273');
insert into tenant
		values(4,'dagsb39293','daniel ly','lyd@gmail.com','4380000273');
insert into tenant
		values(5,'cogsb39293','coley land','land@gmail.com','4389090273'); 
        
-- sample lease
 insert into lease
		values(1,12,'21/04/20','21/04/20','false','2021_customerName.pdf',1,5); 
    insert into lease
		values(2,12,'21/04/20','21/04/20','false','2021_customerName.pdf',2,4);
    insert into lease
		values(3,12,'21/04/20','21/04/20','false','2021_customerName.pdf',3,2);
    insert into lease
		values(4,6,'21/04/20','21/04/20','false','2021_customerName.pdf',4,3);
	insert into lease
		values(5,12,'21/04/20','21/04/20','false','2021_customerName.pdf',5,1);
        
    insert into lease
		values(6,12,'18/04/20','18/04/20','false','2021_customerName.pdf',5,1);    
	insert into lease
		values(7,12,'20/04/20','20/04/20','false','2021_customerName.pdf',5,1);  

-- sample rent
insert into rent
		values(1,800,'Cash','21/04/20','true',1);
insert into rent
		values(2,500,'Credit','21/04/20','false',2);
insert into rent
		values(3,800,'Debit','21/04/20','true',3);
insert into rent
		values(4,200,'Cheque','21/04/20','false',4);
insert into rent
		values(5,1000,'Cash','21/04/20','true',5);
 
  -- select * from rent;
--  select * from lease where property_id = 5 order by end_date DESC  ;
-- select * from tenant;
--  select * from mortgage;
-- select * from repairs;
--  select * from insurance;
-- select * from contractor;
--  select * from utility;
-- select * from property;
-- select * from bank;
