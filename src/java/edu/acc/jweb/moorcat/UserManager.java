package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class UserManager extends DBManager {
    private final DataSource dataSource;
    
    public UserManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addUser(String username, String password, String department) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO users (username, password, department) VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, PasswordHash.hashPassword(password));
            statement.setString(3, department);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }   
    }
    
    public User validateUser(String username, String password) {
        User found = findUser(username);
        if (found != null) {
            if (found.getPassword().equals(PasswordHash.hashPassword(password))) {
                return found;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    public User findUser(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setDepartment(resultSet.getString("department")); 
                return user;
            }
            else {
                return null;
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }            
    }
}