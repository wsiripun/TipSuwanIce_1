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

