DROP DATABASE IF EXISTS db_tipsuwan;
CREATE DATABASE db_tipsuwan;
USE db_tipsuwan;





CREATE TABLE Employees (
    employeeID  INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    active bit NOT NULL,
    password VARCHAR(50) NOT NULL,
    --
    -- Six userRole:  
    --   1. "FrontDesk"  Create, View, Print Invoice (can not update or delete)
    --   2. "BackRoom"   Update and View Ice Inventory
    --   3. "Accounting" CURD Expenses and View Invoices
    --   4. "Admin" CURD Invoices. Update (and view) Inventory. Print (and View) Invoice Report (but not Expense and Driver Report)
    --   5. "SuperAdmin"  Perform ALL operations including changing user role. Adding and deleting users.
    --   6. "Driver"   Can not even login (hence cannot perform any operations)
    userRole VARCHAR(20) NOT NULL,			
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    phoneNumber VARCHAR(20)
    );
    
-- TBD: Invoice, Inventory, Customer Expense Table    

  
   
insert into Employees (ACTIVE, PASSWORD, userRole, firstName, lastName) values (1, '123', 'FrontDesk', 'Beer', 'BeerLastName');
insert into Employees (ACTIVE, PASSWORD, userRole, firstName, lastName) values (1, '123', 'Admin', 'B', 'BLastName');






CREATE TABLE tomcat_users (
	user_name varchar(20) NOT NULL PRIMARY KEY,
	password varchar(32) NOT NULL
);
CREATE TABLE tomcat_roles (
	role_name varchar(20) NOT NULL PRIMARY KEY
);
CREATE TABLE tomcat_users_roles (
	user_name varchar(20) NOT NULL,
	role_name varchar(20) NOT NULL,
	PRIMARY KEY (user_name, role_name),
	CONSTRAINT tomcat_users_roles_foreign_key_1 FOREIGN KEY (user_name) REFERENCES tomcat_users (user_name),
	CONSTRAINT tomcat_users_roles_foreign_key_2 FOREIGN KEY (role_name) REFERENCES tomcat_roles (role_name)
);
INSERT INTO tomcat_users (user_name, password) VALUES ('deron', 'deronpass');
INSERT INTO tomcat_users (user_name, password) VALUES ('larry', 'larrypass');
INSERT INTO tomcat_roles (role_name) VALUES ('dude');
INSERT INTO tomcat_roles (role_name) VALUES ('manager');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('deron', 'dude');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('deron', 'manager');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('larry', 'manager');

