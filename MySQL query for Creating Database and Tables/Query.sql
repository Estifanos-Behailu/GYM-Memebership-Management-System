Create Database GYM;

use GYM;

Create Table AdminAccount(
UserName varchar(20) not null,
Password varchar(20) not null
);

Create Table Members(
CID int primary key auto_increment,
firstName varchar(20) not null,
lastName varchar(20) not null,
gender varchar(6) not null,
mobileNumber varchar(20),
age int
);

Create Table Trainer(
TID int primary key auto_increment,
firstName varchar(20) not null,
lastName varchar(20) not null,
gender varchar(6) not null,
mobileNumber varchar(20),
age int
);

Create Table payment(
ID int,
month varchar(20),
amount int
);

Create Table trains(
CID int,
TID int
);

Alter table trains
add constraint fk_customer
foreign key (CID) references Members(CID);

alter table trains
add constraint fk_trainer
foreign key (TID) references Trainer(TID);




