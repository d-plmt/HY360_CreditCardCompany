DROP DATABASE if Exists CCC;
create Database CCC;
use CCC;

CREATE TABLE Account (
    Account_ID        INT,
    Account_IBAN 	  VARCHAR(32),
    Account_expDate   DATE,
    Account_isActive  BOOL,
    Account_owedToCCC FLOAT,
    PRIMARY KEY(Account_ID, Account_IBAN)
);

CREATE TABLE Supplier_account ( 
    Supp_commission FLOAT,
    Supp_netIncome  FLOAT,
    F_Account_ID    INT,
    FOREIGN KEY (F_Account_ID) REFERENCES Account (Account_ID) -- Supplier_account ISA Account
);

CREATE TABLE Client_account (
	Client_creditLimit 		FLOAT,
    Client_remaining        FLOAT,
	F_Account_ID 			INT,
    FOREIGN KEY (F_Account_ID) REFERENCES Account (Account_ID) -- Client_account ISA Account
);

CREATE TABLE USR_type (
	USR_id      	 INT,
    USR_subDate 	 DATE,
    USR_email		 VARCHAR(255) NOT NULL,
    USR_country 	 VARCHAR(255),
	USR_city         VARCHAR(255),
    USR_street       VARCHAR(255), 
    USR_streetNumber INT,
    USR_postalCode 	 INT,
    F_Account_ID     INT,
    PRIMARY KEY(USR_id),
    FOREIGN KEY(F_Account_ID) REFERENCES Account(Account_ID)
);

CREATE TABLE Supplier (
	Supp_First_Name  VARCHAR(255),
	Supp_Last_Name   VARCHAR(255),
    Supp_sex		 VARCHAR(12),
	F_Supp_AccountID INT, 
	F_USR_id 		 INT,     	  
	FOREIGN KEY (F_Supp_AccountID) REFERENCES Supplier_account (F_Account_ID),  -- Supplier HAS Supplier_account
	FOREIGN KEY (F_USR_id)	 	   REFERENCES USR_type (USR_id) 				-- Supplier ISA USR_type
);

CREATE TABLE Client (
		F_Client_AccountID INT,
        F_USR_id 		   INT,
        FOREIGN KEY (F_Client_AccountID) REFERENCES Client_account (F_Account_ID),  -- Client HAS Supplier_account
        FOREIGN KEY (F_USR_id) 			 REFERENCES USR_type (USR_id) 				-- Client ISA USR_type
);

CREATE TABLE Private_citizen (
	PC_First_Name  	   VARCHAR(255),
	PC_Last_Name   	   VARCHAR(255),
    PC_sex 		 	   VARCHAR(12),
    F_Client_accountID INT,
    F_USR_id 		   INT,
    FOREIGN KEY (F_Client_accountID) REFERENCES Client_account(F_Account_ID), -- Private_citizen HAS Client_account
    FOREIGN KEY (F_USR_id) 			 REFERENCES Client(F_USR_id)			  -- Private_citizen ISA Client
);

CREATE TABLE Company (
	CO_name			   VARCHAR(255),
    CO_ceo 			   VARCHAR(255),
    CO_numOfEmployees  INT,
    F_Client_accountID INT,
    F_USR_id 		   INT,
    FOREIGN KEY (F_Client_accountID) REFERENCES Client_account(F_Account_ID), -- Company HAS Client_account
    FOREIGN KEY (F_USR_id) 			 REFERENCES Client(F_USR_id)			  -- Compamy ISA Client
);

-- :(
CREATE TABLE Employee (
	AFM 					  VARCHAR(9),
    Employee_birthdate 		  DATE,
    Employee_telephone 		  VARCHAR(10),
    Employee_companyAccount   INT,
    PRIMARY KEY (AFM),
    FOREIGN KEY (Employee_companyAccount) REFERENCES Client_account(F_Account_ID) -- company's account id. as foreign key from company.
);

CREATE TABLE Transaction (
	F_ID   							VARCHAR(9),
    Transaction_transactionID 		INT,
    Transaction_clientName       	VARCHAR(255),
    Transaction_suppName       		VARCHAR(255),
    Transaction_date          		DATE,
    Transaction_type           		BOOL,
    Transaction_cost				FLOAT,
    F_Supp_AccountID 				INT,
    F_Client_AccountID 				INT,
    PRIMARY KEY(Transaction_transactionID, Transaction_type),
);
