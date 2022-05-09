package model.supplier;

import model.Generator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplierQueryHandler {

    private static Statement stmt;

    public SupplierQueryHandler(Statement stmt) throws SQLException {
        SupplierQueryHandler.stmt = stmt;
    }

    public static void registerSupplier(Supplier supplier) throws SQLException {
        int registerID;

        registerID = SupplierQueryHandler.maxID("Account", "Account_ID");
        registerID++;

        insertAccount(registerID, supplier);
        insertSupplierAccount(registerID, supplier.getSupplierAccount());
        insertUsrType(registerID, supplier);
        insertSupplier(registerID, supplier);
    }

    private static void insertAccount(int accountID, Supplier supplier) throws SQLException {
        String query;

        query = " INSERT INTO Account VALUES (" + accountID + "," + supplier.getSupplierAccount().getIban() + "," + supplier.getSupplierAccount().getExpDate() + "," + true + "," + 0 + ")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertSupplierAccount(int clientAccountID, SupplierAccount supplierAccount) throws SQLException {
        String query;

        query = " INSERT INTO Supplier_account VALUES (" + supplierAccount.getCommission() +
                "," + supplierAccount.getNetIncome() + "," + clientAccountID + ")" + ";" ;
        System.out.println("SQL Query: " + query);

        stmt.executeUpdate(query);
    }

    private static void insertUsrType(int userID, Supplier supplier) throws SQLException {
        String subDate = "temp", query;

        subDate = Generator.expDateGenerate();

        query = " INSERT INTO USR_type VALUES (" + userID + "," + subDate + "," + supplier.getEmail() + "," + supplier.getAddress().getCountry() + ","
                + supplier.getAddress().getCity() + "," + supplier.getAddress().getStreet() + "," + supplier.getAddress().getNumber() + ","
                + supplier.getAddress().getPostalCode() + "," + userID + ")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertSupplier(int supplierID, Supplier supplier) throws SQLException {
        String query;

        query = " INSERT INTO Supplier VALUES (" + supplier.getFirstName() + "," + supplier.getLastName() +
                "," + supplier.getSex() + "," + supplierID + "," + supplierID + ")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static int count(String table) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + table + ";";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    private static int maxID(String table, String column) throws SQLException {
        String query = "select max(" + column + ") from " + table + ";";
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()) return rs.getInt(1);
        return 0;
    }

    public static void setStatement(Statement stmt) { SupplierQueryHandler.stmt = stmt; }
}
