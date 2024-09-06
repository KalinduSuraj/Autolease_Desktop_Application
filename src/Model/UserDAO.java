package Model;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private DBConnection dbConnection;

    public UserDAO() {
        dbConnection = new DBConnection();
    }

    public boolean checkUserName(User objUser){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM user where username = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, objUser.getUserName());

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean AddUser(User objUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "INSERT INTO user (username, name, contact, password) VALUES (?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, objUser.getUserName());
            preparedStatement.setString(2, objUser.getName());
            preparedStatement.setString(3, objUser.getContact());
            preparedStatement.setString(4, objUser.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean UpdateUser(User objUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "UPDATE user SET contact = ? , password = ?  WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, objUser.getContact());
            preparedStatement.setString(2, objUser.getPassword());
            preparedStatement.setString(3, objUser.getUserName());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean DeleteUser(User objUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "DELETE FROM user  WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql1);
            // Set the parameters
            preparedStatement.setString(1, objUser.getUserName());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public DefaultTableModel DisplayUser(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"User Name","Name","Contact","Password"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM user";
            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Object[] rowdata ={
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("password")
                };
                table.addRow(rowdata);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return table;
    }

    public DefaultTableModel SearchUser(String txtSearch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"User Name","Name","Contact","Password"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM user WHERE username LIKE ? OR name LIKE ?";
            preparedStatement=connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, "%" + txtSearch + "%");
            preparedStatement.setString(2, "%" + txtSearch + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Object[] rowdata ={
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("password")
                };
                table.addRow(rowdata);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return table;
    }

    public boolean userLogin(User logedUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM user where username = ? AND password = ? ";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, logedUser.getUserName());
            preparedStatement.setString(2, logedUser.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){

                logedUser.setUserName(rs.getString("username"));
                logedUser.setName(rs.getString("name"));
                logedUser.setContact(rs.getString("contact"));
                logedUser.setPassword(rs.getString("password"));

                return true;
            }
            else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
