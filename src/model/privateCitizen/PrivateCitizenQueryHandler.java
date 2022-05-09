package model.privateCitizen;

import model.Generator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrivateCitizenQueryHandler {

    private static Statement stmt;

    public PrivateCitizenQueryHandler(Statement stmt) throws SQLException {
        PrivateCitizenQueryHandler.stmt = stmt;
    }

    public static void registerPrivateCitizen(PrivateCitizen privateCitizen) throws SQLException {
        int registerID;

        registerID = PrivateCitizenQueryHandler.maxID("Account", "Account_ID");
        registerID++;

        insertAccount(registerID, privateCitizen);
        insertClientAccount(registerID);
        insertUsrType(registerID, privateCitizen);
        insertClient(registerID);
        insertPrivateCitizen(registerID, privateCitizen);
    }

    private static void insertAccount(int accountID, PrivateCitizen privateCitizen) throws SQLException {
        String query;

        query = " INSERT INTO Account VALUES (" + accountID + "," + privateCitizen.getPrivateCitizenAccount().getIban() + ","
                + privateCitizen.getPrivateCitizenAccount().getExpDate() + "," + true + "," + 0 + ")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertClientAccount(int clientAccountID) throws SQLException {
        String query;

        query = " INSERT INTO Client_account VALUES (" + "5000" + "," + "5000" + "," + clientAccountID + ")" + ";" ;
        System.out.println("SQL Query: " + query);

        stmt.executeUpdate(query);
    }

    private static void insertUsrType(int userID, PrivateCitizen privateCitizen) throws SQLException {
        String subDate = "temp", query;

        subDate = Generator.expDateGenerate();

        query = " INSERT INTO USR_type VALUES (" + userID + "," + subDate + "," + privateCitizen.getEmail() + "," + privateCitizen.getAddress().getCountry() + ","
                + privateCitizen.getAddress().getCity() + "," + privateCitizen.getAddress().getStreet() + "," + privateCitizen.getAddress().getNumber() + ","
                + privateCitizen.getAddress().getPostalCode() + "," + userID + ")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertClient(int userID) throws SQLException {
        String query;

        query = " INSERT INTO Client VALUES (" + userID + "," + userID +")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertPrivateCitizen(int privateCitizenID, PrivateCitizen privateCitizen) throws SQLException {
        String query;

        query = " INSERT INTO Private_citizen VALUES (" + privateCitizen.getFirstName() + "," + privateCitizen.getLastName() +
                "," + privateCitizen.getSex() + "," + privateCitizenID + "," + privateCitizenID +")" + ";" ;

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

    public static void setStatement(Statement stmt) { PrivateCitizenQueryHandler.stmt = stmt; }
}
