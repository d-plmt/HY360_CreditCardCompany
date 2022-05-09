package model.ccc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class CccQueryHandler {

    private static Statement stmt;

    public CccQueryHandler(Statement stmt) {
        CccQueryHandler.stmt = stmt;
    }

    public static void setStatement(Statement stmt) {
        CccQueryHandler.stmt = stmt;
    }

    public static String[][] getGoodUsers() throws SQLException {
        String table = "(Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC = 0)) aa;";
        int numberOfGoodUsers = CccQueryHandler.count(table);
        String[][] data = new String[numberOfGoodUsers][3];

        String query = " Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC = 0); ";
        ResultSet rs = stmt.executeQuery(query);

        int user = 0;
        try {
            while (rs.next()) {
                String id        = rs.getString("USR_id");
                String email     = rs.getString("USR_email");
                String iban      = rs.getString("Account_IBAN");

                data[user][0] = id;
                data[user][1] = email;
                data[user][2] = iban;
                user++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[][] getBadUsers() throws SQLException {
        String table = "(Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC > 0)) aa;";
        int numberOfGoodUsers = CccQueryHandler.count(table);
        String[][] data = new String[numberOfGoodUsers][3];

        String query    = " Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC > 0); ";
        ResultSet rs    = stmt.executeQuery(query);

        int user = 0;
        try {
            while (rs.next()) {
                String id        = rs.getString("USR_id");
                String email     = rs.getString("USR_email");
                String iban      = rs.getString("Account_IBAN");

                data[user][0] = id;
                data[user][1] = email;
                data[user][2] = iban;
                user++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[][] getMostActiveEmployee() throws SQLException {
        String table = "(SELECT F_Supp_AccountID,COUNT(F_Supp_AccountID) AS cnt " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "HAVING cnt=(SELECT COUNT(F_Supp_AccountID) as count " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "ORDER BY count desc limit 1)) aaa;";
        int numberOfGoodUsers = CccQueryHandler.count(table);
        String[][] data = new String[numberOfGoodUsers][3];

        String query = "SELECT F_Supp_AccountID,COUNT(F_Supp_AccountID) AS cnt " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "HAVING cnt=(SELECT COUNT(F_Supp_AccountID) as count " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "ORDER BY count desc limit 1);";

        ResultSet rs    = stmt.executeQuery(query);

        int user = 0;
        try {
            while (rs.next()) {
                String id        = rs.getString("F_Supp_AccountID");
                String cnt       = rs.getString("cnt");

                data[user][0] = id;
                data[user][1] = cnt;
                user++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < data.length; i++) {
            query = "UPDATE Supplier_account " +
                    "SET Supp_commission = Supp_commission - Supp_commission * 0.05 " +
                    "WHERE F_Account_ID = " + data[i][0] + ";";
            stmt.executeUpdate(query);
        }
        return data;
    }

    public static void deleteAccount(String id) throws SQLException {
        String query1 = "select s.F_USR_ID from Supplier s where s.F_USR_ID = " + id + ";";
        String query2 = "select p.F_USR_ID from Private_citizen p where p.F_USR_ID = " + id + ";";
        String query3 = "select c.F_USR_ID from Company c where c.F_USR_ID = " + id + ";";

        ResultSet rs = stmt.executeQuery(query1);
        if (rs.next()) {
            if (Objects.equals(rs.getString(1), id)) {
                System.out.println(id + " is Supplier");
                String deleteSupplierQuery = "delete from Supplier where F_Supp_AccountID = " + id + ";";
                String deleteUser          = "delete from USR_type where USR_id = " + id + ";";
                String deleteSuppAccount   = "delete from Supplier_account where f_account_id = " + id + ";";
                String deleteAccount       = "delete from Account where Account_id = " + id + ";";
                stmt.executeUpdate(deleteSupplierQuery);
                stmt.executeUpdate(deleteUser);
                stmt.executeUpdate(deleteSuppAccount);
                stmt.executeUpdate(deleteAccount);
            }
        }

        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), id)) {
                System.out.println(id + " is PrivateCitizen");
                String deletePrivateCitizenQuery = "delete from Private_citizen where F_USR_id = " + id + ";";
                String deleteClientQuery         = "delete from Client where F_USR_id = " + id + ";";
                String deleteUser                = "delete from USR_type where USR_id = " + id + ";";
                String deleteClientAccount       = "delete from Client_account where f_account_id = " + id + ";";
                String deleteAccount             = "delete from Account where Account_id = " + id + ";";
                stmt.executeUpdate(deletePrivateCitizenQuery);
                stmt.executeUpdate(deleteClientQuery);
                stmt.executeUpdate(deleteUser);
                stmt.executeUpdate(deleteClientAccount);
                stmt.executeUpdate(deleteAccount);
            }
        }

        rs = stmt.executeQuery(query3);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), id)) {
                System.out.println(id + " is Company");
                String deleteEmployee      = "delete from Employee where Employee_companyAccount = " + id + ";";
                String deleteCompanyQuery  = "delete from Company where F_USR_id = " + id + ";";
                String deleteClientQuery   = "delete from Client where F_USR_id = " + id + ";";
                String deleteUser          = "delete from USR_type where USR_id = " + id + ";";
                String deleteClientAccount = "delete from Client_account where f_account_id = " + id + ";";
                String deleteAccount       = "delete from Account where Account_id = " + id + ";";
                stmt.executeUpdate(deleteEmployee);
                stmt.executeUpdate(deleteCompanyQuery);
                stmt.executeUpdate(deleteClientQuery);
                stmt.executeUpdate(deleteUser);
                stmt.executeUpdate(deleteClientAccount);
                stmt.executeUpdate(deleteAccount);
            }
        }
    }

    public static String[] getAccountIds() throws SQLException {
        String query = "Select Account_ID from Account;";
        String[] data = new String[CccQueryHandler.count("(Select Account_ID from Account) a")];

        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("Account_ID");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private static int count(String table) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + table + ";";
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()) return rs.getInt(1);
        return 0;
    }
}
