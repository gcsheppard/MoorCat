package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    public ArrayList<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders ORDER BY id");
            ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Order order = orderFromDB(resultSet);
                list.add(order);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } 
        return list;
    }
    
        public Order findOrderById(Integer id) {
        Order order = null;
        String sql = "select * from orders where id = " + id;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                order = orderFromDB(resultSet);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        }
        return order;
    }
    
    private Order orderFromDB(ResultSet resultSet) {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt("id"));
            order.setFirst_name(resultSet.getString("first_name"));
            order.setLast_name(resultSet.getString("last_name"));
            order.setStatus(resultSet.getString("status"));
            return order;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    
    public HashMap<String,String> validOrder(Order order) {
        HashMap<String,String> errors = new HashMap<>();
        
        if (order.first_name.isEmpty()) errors.put("first_name", "First Name not entered.");
        if (order.last_name.isEmpty()) errors.put("last_name", "Last Name not entered.");
        if (order.status.isEmpty()) errors.put("status", "Status not entered.");
        return errors;
    }
        
    public void updateOrder(Integer id, String first_name, String last_name, String status) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE orders set first_name = ?, last_name = ?, status = ? WHERE id = ?");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, status);
            statement.setInt(4, id);
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
    
    public void approveOrder(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE orders set status = 'Approved' WHERE id = ?");
            statement.setInt(1, id);
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

