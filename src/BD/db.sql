DROP DATABASE db;

CREATE DATABASE db;

USE db;



CREATE TABLE System_User(

 Cod_User INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
User_Name VARCHAR(20) UNIQUE NOT NULL,
User_Password VARCHAR(20) NOT NULL,
U_Name VARCHAR(50) NOT NULL,
Email VARCHAR(50) UNIQUE NOT NULL,
birthdate DATE NOT NULL,
Sex VARCHAR(1) NOT NULL
);



CREATE TABLE Establishment(
Cod_Establishment int AUTO_INCREMENT PRIMARY KEY,
E_Name VARCHAR(50) NOT NULL,

Nature VARCHAR(50) NOT NULL,

City VARCHAR(50) NOT NULL,

State VARCHAR(2) NOT NULL,

Country VARCHAR(50) NOT NULL
);


CREATE TABLE RSS(
Cod_RSS int AUTO_INCREMENT PRIMARY KEY,
RSS_Name VARCHAR(50) NOT NULL,
Category VARCHAR(50) NOT NULL,
URL VARCHAR(2083) NOT NULL,
Profile_Date DATETIME
);



CREATE TABLE Expense(


Cod_Expense int AUTO_INCREMENT PRIMARY KEY,
Cod_User int,
Establishment_Name VARCHAR(50) NOT NULL,
Description VARCHAR(50) NOT NULL,
Price REAL NOT NULL,
Payment_Method VARCHAR(50) NOT NULL,
Frequency VARCHAR(15) NOT NULL,
State VARCHAR(10) NOT NULL,
Category VARCHAR(50) NOT NULL,
City VARCHAR(50) NOT NULL,
Date DATETIME,
Nature VARCHAR(50) NOT NULL,
FOREIGN KEY (Cod_User) REFERENCES Users(Cod_User)
);



CREATE TABLE Profile(

Cod_Profile int AUTO_INCREMENT PRIMARY KEY,
Cod_User int,

Cod_Establishment int,

Cod_Expense int,

Cod_RSS int,

Frequent_Category VARCHAR(50) NOT NULL,

Frequent_Nature VARCHAR(50) NOT NULL,

RSS_Category VARCHAR(50) NOT NULL,

Profile_Date DATETIME,

FOREIGN KEY (Cod_User) REFERENCES Users(Cod_User),

FOREIGN KEY (Cod_Establishment) REFERENCES Establishment(Cod_Establishment),

FOREIGN KEY (Cod_Expense) REFERENCES Expense(Cod_Expense),

FOREIGN KEY (Cod_RSS) REFERENCES RSS(Cod_RSS)
);

INSERT INTO system_user(User_Name, User_password,Email, birthdate, Sex, U_name) values('Mano','slim','pauloeduardomf@gmail.com','1997-11-16','M','Mano');

INSERT INTO expense(Cod_User, Establishment_Name,Description,Price,Payment_Method,Frequency,State,Category,City,Date,Nature) values
('1','Rua','Carona','100.00','Dinheiro','Mensal','SP','Transporte','Boituva','2016-11-20','Outro')
