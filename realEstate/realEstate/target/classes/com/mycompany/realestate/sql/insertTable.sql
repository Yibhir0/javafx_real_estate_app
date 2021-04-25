-- Yaasine Ibhir

-- insert random data into insurance table
insert into insurance values(1,'posix',1000,'5430 rue laval montreal','514,6795174');
insert into insurance values(2,'hoxaw',5000,'54333 rue laval montreal','514,6795174');
insert into insurance values(3,'posix',1000,'5430 rue laval montreal','514,6795174');
insert into insurance values(4,'hoxaw',5000,'54333 rue laval montreal','514,6795174');
insert into insurance values(5,'EXPENSIVE',10000,'54333 rue laval montreal','514,6795174');

-- insert random data in property table
    
insert into property 
values(1,'5430 rue property montreal','plex',
1000.80,2000,900,'true',1,4);

insert into property 
values(2,'5430 rue property montreal','plex',
1000.80,2000,900,'true',2,5);

insert into property 
values(3,'00000 rue property montreal','plex',
1000.80,2000,900,'true',1,4);

insert into property 
values(4,'2299 rue property montreal','plex',
1000.80,2000,900,'true',1,4);

insert into property 
values(5,'1111 rue property montreal','plex',
1000.80,2000,900,'true',1,4);


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
		values(1,12,'21/04/20','false',1,5); 
    insert into lease
		values(2,12,'21/04/21','false',2,4);
    insert into lease
		values(3,12,'21/04/20','false',3,2);
    insert into lease
		values(4,6,'21/04/20','false',4,3);
	insert into lease
		values(5,12,'21/04/20','false',5,1); 

-- sample rent
insert into rent
		values(1,800,'cash','21/04/20','true',1);
insert into rent
		values(2,500,'cash','21/04/20','false',2);
insert into rent
		values(3,800,'cash','21/04/20','true',3);
insert into rent
		values(4,200,'credit card','21/04/20','false',4);
insert into rent
		values(5,1000,'cash','21/04/20','true',5);
 
 --  select * from rent;
--  select * from lease;
--   select * from tenant;
--   select * from mortgage;
--  select * from repairs;
--  select * from insurance;
--   select * from contractor;
--  select * from utility;
--  select * from property;
--  select * from bank;
