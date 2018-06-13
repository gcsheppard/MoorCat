package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sql.DataSource;

public class OrderManager extends DBManager {
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
            close(statement);
            close(connection);
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
            close(statement);
            close(connection);
        }
    }
    
    public void removeProductFromOrder(Integer order_id, Integer product_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM order_items WHERE order_id = ? AND product_id = ?");
            statement.setInt(1, order_id);
            statement.setInt(2, product_id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
    public void updateOrderedQuantity(Integer order_id, Integer product_id, Integer ordered) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE order_items SET quantity = ? WHERE order_id = ? AND product_id = ?");
            statement.setInt(1, ordered);
            statement.setInt(2, order_id);
            statement.setInt(3, product_id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
    public ArrayList<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM orders WHERE status <> 'Archived' ORDER BY id");
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Order order = orderFromDB(resultSet);
                list.add(order);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        } 
        return list;
    }
    
    public ArrayList<OrderItem> getItemsForOrder(int order_id) {
        ArrayList<OrderItem> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT a.product_id, a.quantity, a.picked, b.name, c.name as category, "
                    + "d.name AS supplier FROM order_items a, products b, categories c, suppliers d WHERE a.order_id = ? "
                    + "AND a.product_id = b.id AND b.category_id = c.id AND b.supplier_id = d.id");
            statement.setInt(1, order_id);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                OrderItem orderItem = orderItemFromDB(resultSet);
                list.add(orderItem);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }         
        return list;
    }
    
    public Order findOrderById(Integer id) {
        Order order = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("select * from orders where id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = orderFromDB(resultSet);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
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
        
    private OrderItem orderItemFromDB(ResultSet resultSet) {
        OrderItem orderItem = new OrderItem();
        try {
            orderItem.setProduct_id(resultSet.getInt("product_id"));
            orderItem.setQuantity(resultSet.getInt("quantity"));
            orderItem.setPicked(resultSet.getInt("picked"));
            orderItem.setName(resultSet.getString("name"));
            orderItem.setCategory(resultSet.getString("category"));
            orderItem.setSupplier(resultSet.getString("supplier"));
            return orderItem;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    
    public HashMap<String,String> validOrder(Order order) {
        HashMap<String,String> errors = new HashMap<>();
        
        if (order.first_name.isEmpty()) errors.put("first_name", "First Name not entered.");
        if (order.last_name.isEmpty()) errors.put("last_name", "Last Name not entered.");
        return errors;
    }
        
    public void updateOrderWithStatus(Integer id, String first_name, String last_name, String status) {
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
            close(statement);
            close(connection);
        }
    }
    
        public void updateOrder(Integer id, String first_name, String last_name) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE orders set first_name = ?, last_name = ? WHERE id = ?");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            //statement.setString(3, status);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
    public void updatePicked(int order_id, int product_id, int picked) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE order_items set picked = ? WHERE order_id = ? and product_id = ?");
            statement.setInt(1, picked);
            statement.setInt(2, order_id);
            statement.setInt(3, product_id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
    public Boolean pickComplete(int order_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT product_id FROM order_items WHERE order_id = ? AND picked <> quantity");
            statement.setInt(1, order_id);
            resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }    
    }
    
    public void updateOrderStatus(Integer id, String status) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE orders set status = ? WHERE id = ?");
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
    public Boolean productInOrder(int order_id, int product_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT product_id FROM order_items WHERE order_id = ? AND product_id = ?");
            statement.setInt(1, order_id);
            statement.setInt(2, product_id);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }    
    }
    
    public void addQuantityToOrderedItem(int order_id, int product_id, int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE order_items SET quantity = quantity + ? WHERE order_id = ? AND product_id = ?");
            statement.setInt(1, quantity);
            statement.setInt(2, order_id);
            statement.setInt(3, product_id);
            statement.executeUpdate();
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }    
    }
}

