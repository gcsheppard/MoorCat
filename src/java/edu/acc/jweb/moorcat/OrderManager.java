package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class OrderManager {
    private final DataSource dataSource;
    
    public OrderManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addOrder(String firstName, String lastName, String status) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO orders (first_name, last_name, status) values (?,?,?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, status);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            try {
                if (statement != null) { 
                    statement.close();
                } 
                if (connection != null) { 
                    connection.close();
                } 
            } catch (SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        }   
    }
    
    public void addProductToOrder(int order_id, int product_id, int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO order_items (order_id, product_id, quantity) values (?,?,?)");
            statement.setInt(1, order_id);
            statement.setInt(2, product_id);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            try {
                if (statement != null) { 
                    statement.close();
                } 
                if (connection != null) { 
                    connection.close();
                } 
            } catch (SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        }   
    }
}
