package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private Connection connection;
    private String connectionURL = "jdbc:mysql://localhost:3306/autoleasedb";

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.connection = DriverManager.getConnection(connectionURL, "root", "");
//            System.out.println("Database connected");
        } catch (Exception ex) {
            System.out.println("Connection Failed");
            ex.printStackTrace();
        }
    }

}
