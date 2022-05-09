package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;
    private String url;

    public DatabaseConnection(String username, String password) throws ClassNotFoundException {

        url = "jdbc:mysql://localhost:3306/CCC";
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            if(connection != null) System.out.println("Database connection established!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() { return connection; }
    public Statement getStatement() { return statement; }
}
