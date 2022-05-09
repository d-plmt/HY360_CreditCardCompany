package model;

import model.ccc.CccQueryHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class UserLoginQueryHandler {

    private static Statement stmt;

    public UserLoginQueryHandler(Statement stmt) throws SQLException {
        UserLoginQueryHandler.stmt = stmt;
    }

    public static String[] getAccountIbans() throws SQLException {
        String query = "Select Account_IBAN from Account;";
        String[] data = new String[UserLoginQueryHandler.count("(Select Account_IBAN from Account) a")];

        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("Account_IBAN");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String getUserType(String iban) throws SQLException {

        String query0 = "select Account_ID from Account where Account_IBAN = " + "\"" + iban + "\"";
        int id = -1;
        ResultSet rs = stmt.executeQuery(query0);
        if(rs.next()) {
            id = rs.getInt(1);
        }

        String query1;
        String query2;
        String query3;

        query1 = "select s.F_USR_ID from Supplier s where s.F_USR_ID = " + id + ";";
        query2 = "select p.F_USR_ID from Private_citizen p where p.F_USR_ID = " + id + ";";
        query3 = "select c.F_USR_ID from Company c where c.F_USR_ID = " + id + ";";

        rs = stmt.executeQuery(query1);
        if (rs.next()) {
            if (Objects.equals(rs.getString(1), String.valueOf(id))) {
                return "supplier";
            }
        }

        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), String.valueOf(id))) {
                return "private_citizen";
            }
        }

        rs = stmt.executeQuery(query3);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), String.valueOf(id))) {
                return "company";
            }
        }

        return "something_went_wrong";
    }

    public static String[] getSupplierIds() throws SQLException {
        String query = "Select F_USR_id from Supplier;";
        String[] data = new String[UserLoginQueryHandler.count("(Select F_USR_id from Supplier) a")];

        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("F_USR_id");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[] getTransactionIdsBasedOn(int clientId) throws SQLException {
        String query, query1;

        System.out.println("Get Transactions of " + clientId);
        query  = "select * from Transaction where " +
                "(transaction_transactionID not in (select transaction_transactionID " +
                "from Transaction where (transaction_type = 1)) and F_Client_AccountID = " + clientId + ");";

        query1 = "select * from Transaction where " +
                "(transaction_transactionID not in (select transaction_transactionID " +
                "from Transaction where (transaction_type = 1)) and F_Client_AccountID = " + clientId + ")";

        String[] data = new String[UserLoginQueryHandler.count("(" + query1 + ")a")];

        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("Transaction_transactionID");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static int convertIban2Id(String clientIban) throws SQLException {
        String query;
        int clientId = -1;

        query = "select Account_id from Account where Account_IBAN = " + "\"" + clientIban + "\"" + ";";
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()) clientId = rs.getInt(1);
        return  clientId;
    }

    public static Boolean makeTransaction(String clientIban, String afmEmployee, int supplierId, float price) throws SQLException {
        String query, query1, query2;

        query = "select Account_id from Account where Account_IBAN = " + "\"" + clientIban +  "\";";
        ResultSet rs = stmt.executeQuery(query);

        int clientId = -1;
        if(rs.next()) clientId = rs.getInt(1);

        query = "select Client_remaining from Client_account where F_Account_ID = " + clientId + ";";
        rs = stmt.executeQuery(query);
        int remaining = -1;
        if(rs.next()) {
            remaining = rs.getInt(1);
            if(remaining < price) return false;
        }

        query1 = "select p.F_USR_ID from Private_citizen p where p.F_USR_ID = " + clientId + ";";
        query2 = "select F_USR_ID from Company where F_USR_ID = " + clientId + ";";

        String suppFirstName = "", suppLastName = "";
        query = "select Supp_First_Name, Supp_Last_Name from Supplier where F_USR_ID = " + supplierId + ";";
        rs = stmt.executeQuery(query);
        if(rs.next()) {
            suppFirstName = rs.getString(1);
            suppLastName  = rs.getString(2);
        }

        System.out.println(query1);
        rs = stmt.executeQuery(query1);

        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), clientId)) {
                System.out.println(clientId + " is PrivateCitizen");

                int transcationID = UserLoginQueryHandler.count("Transaction");
                transcationID++;
                String pcFirstName = "", pcLastName = "";
                query = "select PC_First_Name, PC_Last_Name from Private_citizen where F_USR_ID = " + clientId + ";";
                rs = stmt.executeQuery(query);
                if(rs.next()) {
                    pcFirstName = rs.getString(1);
                    pcLastName  = rs.getString(2);
                    System.out.println(pcFirstName + " " + pcLastName);
                }

                query = "INSERT INTO Transaction VALUES(null," + transcationID + ", " + "\"" + pcFirstName + " " + pcLastName + "\"" + "," + "\"" + suppFirstName + " " + suppLastName + "\"" + ", "
                        + "'" + Generator.getCurrentDate() + "'" + ", "
                        + (price < 0) + "," + price + ", " + supplierId + ", " + clientId + ");";

                System.out.println(query);

                stmt.executeUpdate(query);

                query = "UPDATE Client_account SET Client_remaining = Client_remaining - " + price + " where F_Account_ID = " + clientId + ";";
                stmt.executeUpdate(query);

                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC + " + price + " where Account_ID = " + clientId + ";";
                stmt.executeUpdate(query);

                query = "select Supp_commission from Supplier_account where F_Account_ID = " + supplierId + ";";
                rs = stmt.executeQuery(query);
                float commission = 0.0f;
                if(rs.next()) {
                    commission =  rs.getFloat(1);
                }
                float priceCommission = price-(price*commission);
                query = "UPDATE Supplier_account SET Supp_netIncome = Supp_netIncome +" + priceCommission + " where F_Account_ID = " + supplierId + ";";
                stmt.executeUpdate(query);
                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC + " + price*commission + " where Account_ID = " + supplierId+ ";";
                stmt.executeUpdate(query);
            }
        }

        System.out.println(query2);
        rs = stmt.executeQuery(query2);

        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), clientId)) {
                System.out.println(clientId + " is Company");

                String companyName = "";
                query = "select CO_name from Company where F_USR_ID = " + clientId + ";";
                rs = stmt.executeQuery(query);
                if(rs.next()) companyName = rs.getString(1);

                int transcationID = UserLoginQueryHandler.count("Transaction");
                transcationID++;

                query = "INSERT INTO Transaction VALUES(" + afmEmployee +"," + transcationID + ", " + "\"" + companyName + "\"" + "," + "\"" + suppFirstName + " " + suppLastName + "\"" + ", "
                        + "'" + Generator.getCurrentDate() + "'" + ", "
                        + (price < 0) + "," + price + ", " + supplierId + ", " + clientId + ");";

                System.out.println(query);

                stmt.executeUpdate(query);

                query = "UPDATE Client_account SET Client_remaining = Client_remaining - " + price + " where F_Account_ID = " + clientId + ";";
                stmt.executeUpdate(query);

                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC + " + price + " where Account_ID = " + clientId + ";";
                stmt.executeUpdate(query);

                query = "select Supp_commission from Supplier_account where F_Account_ID = " + supplierId + ";";
                rs = stmt.executeQuery(query);
                float commission = 0.0f;
                if(rs.next()) {
                    commission =  rs.getFloat(1);
                }
                float priceCommission = price-(price*commission);
                query = "UPDATE Supplier_account SET Supp_netIncome = Supp_netIncome +" + priceCommission + " where F_Account_ID = " + supplierId + ";";
                stmt.executeUpdate(query);
                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC + " + price*commission + " where Account_ID = " + supplierId+ ";";
                stmt.executeUpdate(query);
            }
        }

        return true;
    }

    public static Boolean refundTransaction(String clientIban, int transactionID) throws SQLException {
        String query, query1, query2;

        query = "select Account_id from Account where Account_IBAN = " + "\"" + clientIban +  "\";";
        ResultSet rs = stmt.executeQuery(query);

        int clientId = -1;
        if(rs.next()) clientId = rs.getInt(1);

        query1 = "select p.F_USR_ID from Private_citizen p where p.F_USR_ID = " + clientId + ";";
        query2 = "select c.F_USR_ID from Company c where c.F_USR_ID = " + clientId + ";";

        int supplierId = -1;

        query = "select F_Supp_AccountID from Transaction where Transaction_transactionID = " + transactionID + ";";
        rs = stmt.executeQuery(query);

        if(rs.next()) supplierId = rs.getInt(1);

        String suppFirstName = "", suppLastName = "";
        query = "select Supp_First_Name, Supp_Last_Name from Supplier where F_USR_ID = " + supplierId + ";";
        rs = stmt.executeQuery(query);
        if(rs.next()) {
            suppFirstName = rs.getString(1);
            suppLastName  = rs.getString(2);
        }

        System.out.println(query1);

        float cost = -1;
        query = "select Transaction_cost from Transaction where Transaction_transactionID = " + transactionID + ";";
        rs = stmt.executeQuery(query);

        if(rs.next()) cost = rs.getFloat(1);
        cost *= -1;

        rs = stmt.executeQuery(query1);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), clientId)) {
                System.out.println(clientId + " is PrivateCitizen");

                String pcFirstName = "", pcLastName = "";
                query = "select PC_First_Name, PC_Last_Name from Private_citizen where F_USR_ID = " + clientId + ";";
                rs = stmt.executeQuery(query);
                if(rs.next()) {
                    pcFirstName = rs.getString(1);
                    pcLastName  = rs.getString(2);
                    System.out.println(pcFirstName + " " + pcLastName);
                }

                query = "INSERT INTO Transaction VALUES(null," + transactionID + ", " + "\"" + pcFirstName + " " + pcLastName + "\"" + "," + "\"" + suppFirstName + " " + suppLastName + "\"" + ", "
                        + "'" + Generator.getCurrentDate() + "'" + ", "
                        + (cost < 0) + "," + cost + ", " + supplierId + ", " + clientId + ");";

                System.out.println(query);

                stmt.executeUpdate(query);

                cost *= -1;
                query = "UPDATE Client_account SET Client_remaining = Client_remaining + " + cost + " where F_Account_ID = "+ clientId +";";
                stmt.executeUpdate(query);

                query = "select Supp_commission from Supplier_account where F_Account_ID = " + supplierId + ";";
                rs = stmt.executeQuery(query);
                float commission = 0.0f;
                if(rs.next()) {
                    commission =  rs.getFloat(1);
                }
                float costCommission = cost-(cost*commission); //100 - (100*0.09) = 100 - (-91) = 100 - 91 = 9

                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC - " + cost*commission + " where Account_ID = "+ supplierId +";";
                stmt.executeUpdate(query);

                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC - " + cost + " where Account_ID = "+ clientId +";";
                stmt.executeUpdate(query);

                query = "UPDATE Supplier_account SET Supp_netIncome = Supp_netIncome - " + costCommission + " where F_Account_ID = "+ supplierId +";";
                stmt.executeUpdate(query);
            }
        }


        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), clientId)) {
                System.out.println(clientId + " is Company");

                String companyName = "";
                query = "select CO_name from Company where F_Client_accountID = " + clientId + ";";
                rs = stmt.executeQuery(query);
                if(rs.next()) {
                    companyName = rs.getString(1);
                    System.out.println(companyName);
                }

                String afm = "";

                query = "select F_ID from Transaction where Transaction_transactionID = " + transactionID + ";";
                rs = stmt.executeQuery(query);
                if(rs.next()) afm = rs.getString(1);

                query = "INSERT INTO Transaction VALUES("+ afm +"," + transactionID + ", " + "\"" + companyName + "\"" + "," + "\"" + suppFirstName + " " + suppLastName + "\"" + ", "
                        + "'" + Generator.getCurrentDate() + "'" + ", "
                        + (cost < 0) + "," + cost + ", " + supplierId + ", " + clientId + ");";

                System.out.println(query);

                stmt.executeUpdate(query);

                cost *= -1;
                query = "UPDATE Client_account SET Client_remaining = Client_remaining + " + cost + " where F_Account_ID = "+ clientId +";";
                stmt.executeUpdate(query);

                query = "select Supp_commission from Supplier_account where F_Account_ID = " + supplierId + ";";
                rs = stmt.executeQuery(query);
                float commission = 0.0f;
                if(rs.next()) {
                    commission =  rs.getFloat(1);
                }
                float costCommission = cost-(cost*commission); //100 - (100*0.09) = 100 - (-91) = 100 - 91 = 9

                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC - " + cost*commission + " where Account_ID = "+ supplierId +";";
                stmt.executeUpdate(query);

                query = "UPDATE Account SET Account_owedToCCC = Account_owedToCCC - " + cost + " where Account_ID = "+ clientId +";";
                stmt.executeUpdate(query);

                query = "UPDATE Supplier_account SET Supp_netIncome = Supp_netIncome - " + costCommission + " where F_Account_ID = "+ supplierId +";";
                stmt.executeUpdate(query);

            }
        }

        return true;
    }

    public static String getAccountRemainingOf(String iban) throws SQLException {

        String query, query1, query2, query3;
        query = "select Account_id from Account where Account_IBAN = " + "\"" + iban +  "\";";
        ResultSet rs = stmt.executeQuery(query);

        int userID = -1;
        if(rs.next()) userID = rs.getInt(1);

        query1 = "select p.F_Account_ID from Client_account p where p.F_Account_ID = " + userID + ";";
        query2 = "select s.F_Account_ID from Supplier_account s where s.F_Account_ID = " + userID + ";";

        rs = stmt.executeQuery(query1);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), userID)) {
                System.out.println(userID + " is Client Account");
                query3 = "select Account_owedToCCC from Account where Account_ID = " + userID + ";";
                rs = stmt.executeQuery(query3);
                if(rs.next()) {
                    return String.valueOf(rs.getFloat(1));
                }
            }
        }

        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), userID)) {
                System.out.println(userID + " Supplier Account");
                query3 = "select Account_owedToCCC from Account where Account_ID = " + userID + ";";
                rs = stmt.executeQuery(query3);
                if(rs.next()) {
                    return String.valueOf(rs.getFloat(1));
                }
            }
        }
        return "something bad happened!";
    }

    public static boolean updateRemaining(String iban, String payDebtAmount) throws SQLException {

        String query1, query2, query3;
        int user_id = convertIban2Id(iban);

        query1 = "select p.F_Account_ID from Client_account p where p.F_Account_ID = " + user_id + ";";
        query2 = "select s.F_Account_ID from Supplier_account s where s.F_Account_ID = " + user_id + ";";

        ResultSet rs = stmt.executeQuery(query1);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), user_id)) {
                System.out.println(user_id + " is Client Account");
                float creditLimit = 0.0f;

                query3 = "select Client_creditLimit from Client_account where F_Account_ID = " + user_id + ";";
                rs = stmt.executeQuery(query3);
                if(rs.next()) {
                    creditLimit = rs.getFloat(1);
                }

                query3 = "select Client_remaining from Client_account where F_Account_ID = " + user_id + ";";
                rs = stmt.executeQuery(query3);
                if(rs.next()) {
                    if(rs.getFloat(1) + Float.parseFloat(payDebtAmount) > creditLimit) {
                        return false;
                    }
                }
                query3 = "update Client_account set Client_remaining  = Client_remaining + " + payDebtAmount + " where F_Account_ID = " + user_id +";";
                stmt.executeUpdate(query3);

                query3 = "update Account set Account_owedToCCC  = Account_owedToCCC - " + payDebtAmount + " where Account_ID = " + user_id +";";
                stmt.executeUpdate(query3);
                return true;
            }
        }

        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), user_id)) {
                System.out.println(user_id + " Supplier Account");
                query3 = "select Account_owedToCCC from Account where Account_ID = " + user_id + ";";
                rs = stmt.executeQuery(query3);
                if(rs.next()) {
                    if(rs.getFloat(1) < Float.parseFloat(payDebtAmount)) {
                        return false;
                    }
                }

                query3 = "update Account set Account_owedToCCC  = Account_owedToCCC - " + payDebtAmount + " where Account_ID = " + user_id +";";
                stmt.executeUpdate(query3);
                return true;
            }
        }
        return false;
    }

    public static String[][] getTransactionHistory(String userIban, String dateFrom, String dateTo) throws SQLException {

        int userID = convertIban2Id(userIban);
        String table;
        String query;

        int numOfTransaction;
        String[][] data = new String[0][];
        ResultSet rs;

        String userType = getUserType(userIban);

        if(userType.equals("private_citizen") || userType.equals("supplier")) {
            String user_check = "F_Supp_AccountID";

            if(userType.equals("private_citizen")) user_check = "F_Client_AccountID";

            table = "(select Transaction_transactionID, Transaction_clientName, Transaction_suppName, Transaction_type, Transaction_cost, Transaction_date\n" +
                    "from Transaction " +
                    "where (((Transaction_date >= \"" + dateFrom + "\") and (Transaction_date <= \"" + dateTo + "\"))  and "+ user_check + " = " + userID + "))a";

            numOfTransaction = UserLoginQueryHandler.count(table);
            data = new String[numOfTransaction][6];

            query = "select Transaction_transactionID, Transaction_clientName, Transaction_suppName, Transaction_type, Transaction_cost, Transaction_date\n" +
                    "from Transaction " +
                    "where (((Transaction_date >= \"" + dateFrom + "\") and (Transaction_date <= \"" + dateTo + "\"))  and " + user_check + " = " + userID + ");";

            rs = stmt.executeQuery(query);
            int user = 0;
            try {
                while (rs.next()) {
                    String tid          = rs.getString("Transaction_transactionID");
                    String clientName   = rs.getString("Transaction_clientName");
                    String supplierName = rs.getString("Transaction_suppName");
                    String type         = rs.getString("Transaction_type");
                    String cost         = rs.getString("Transaction_cost");
                    String date         = rs.getString("Transaction_date");

                    data[user][0] = tid;
                    data[user][1] = clientName;
                    data[user][2] = supplierName;
                    data[user][3] = type;
                    data[user][4] = cost;
                    data[user][5] = date;
                    user++;
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    public static String[][] getTransactionHistoryOfEmployee(String iban, String employeeAfm, String dateFrom, String dateTo) throws SQLException {

        int userID = convertIban2Id(iban);
        String table;
        String query1, query2;

        int numOfTransaction;
        String[][] data;
        ResultSet rs;

        table = "(select Transaction_transactionID, Transaction_clientName, Transaction_suppName, Transaction_type, Transaction_cost, Transaction_date\n" +
                "from Transaction " +
                "where (((Transaction_date >= \"" + dateFrom + "\") and (Transaction_date <= \"" + dateTo + "\"))  and "+ "F_Client_AccountID" + " = " + userID + "))a";

        query1 = "select Transaction_transactionID, F_ID, Transaction_clientName, Transaction_suppName, Transaction_type, Transaction_cost, Transaction_date\n" +
                "from Transaction " +
                "where (((Transaction_date >= \"" + dateFrom + "\") and (Transaction_date <= \"" + dateTo + "\"))  and " + "F_Client_AccountID" + " = " + userID + ");";

        if(employeeAfm.equals("ALL")) {
            numOfTransaction = UserLoginQueryHandler.count(table);
            data = new String[numOfTransaction][7];
            rs = stmt.executeQuery(query1);
        } else {
            table = "(select Transaction_transactionID, F_ID, Transaction_clientName, Transaction_suppName, Transaction_type, Transaction_cost, Transaction_date " +
                    "from (" + query1.substring(0, query1.length()-1) + ")a " + "where F_ID = " + employeeAfm + ")a";

            numOfTransaction = UserLoginQueryHandler.count(table);
            data = new String[numOfTransaction][7];

            query2 = "select Transaction_transactionID, F_ID, Transaction_clientName, Transaction_suppName, Transaction_type, Transaction_cost, Transaction_date " +
                    "from (" + query1.substring(0, query1.length()-1) + ")a " + "where F_ID = " + employeeAfm + ";";
            rs = stmt.executeQuery(query2);
        }

        int user = 0;
        try {
            while (rs.next()) {
                String tid          = rs.getString("Transaction_transactionID");
                String fid          = rs.getString("F_ID");
                String clientName   = rs.getString("Transaction_clientName");
                String supplierName = rs.getString("Transaction_suppName");
                String type         = rs.getString("Transaction_type");
                String cost         = rs.getString("Transaction_cost");
                String date         = rs.getString("Transaction_date");

                data[user][0] = tid;
                data[user][1] = fid;
                data[user][2] = clientName;
                data[user][3] = supplierName;
                data[user][4] = type;
                data[user][5] = cost;
                data[user][6] = date;
                user++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[] getEmployeesOfCompany(String iban) throws SQLException {

        int companyID = convertIban2Id(iban);
        String query, table;
        String[] data;

        table = "(select distinct F_ID from Transaction where F_Client_AccountID = " + companyID + ")a";
        query = "select distinct F_ID from Transaction where F_Client_AccountID = " + companyID + ";";

        data = new String[UserLoginQueryHandler.count(table)];
        ResultSet rs = stmt.executeQuery(query);

        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("F_ID");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;

    }

    public static String getOwedToCCC(String iban) throws SQLException {

        String query = "";

        query = "select Account_owedToCCC from Account where Account_IBAN = \"" + iban + "\";";
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()) {
            return String.valueOf(rs.getFloat(1));
        }

        return "";
    }

    public static String[] getAllEmployeesOfCompany(String iban) throws SQLException {
        String query, table;
        String[] data;
        ResultSet rs;

        table = "(select AFM from Employee where Employee_companyAccount = " + + convertIban2Id(iban) + ")a";
        data  = new String[UserLoginQueryHandler.count(table)];

        query = "select AFM from Employee where Employee_companyAccount = " + convertIban2Id(iban);
        rs = stmt.executeQuery(query);

        int user = 0;
        while(rs.next()) {
            data[user++]  = rs.getString("AFM");
        }

        return data;
    }

    private static int count(String table) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + table + ";";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    public static void setStatement(Statement stmt) { UserLoginQueryHandler.stmt = stmt; }
}
