package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

public class ProductManager extends DBManager {
    private final DataSource dataSource;
    
    public ProductManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addProduct(String name, String price, int category, int supplier) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO products (name, price, category_id, supplier_id) values (?,?,?,?)");
            statement.setString(1, name);
            statement.setString(2, price);
            statement.setInt(3, category);
            statement.setInt(4, supplier);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT b.id, b.name, b.price, c.name as category, "
                    + "d.name AS supplier FROM products b, categories c, suppliers d WHERE b.category_id = c.id AND b.supplier_id = d.id");
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Product product = productAllFromDB(resultSet);
                list.add(product);
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
    
    public ArrayList<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM products ORDER BY id");
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Product product = productFromDB(resultSet);
                list.add(product);
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
    
    public ArrayList<Product> getProductsNotOnOrder(int order_id) {
        ArrayList<Product> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM products WHERE id NOT IN (SELECT product_id "
                    + "FROM order_items WHERE order_id = ?) ORDER BY id");
            statement.setInt(1, order_id);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Product product = productFromDB(resultSet);
                list.add(product);
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

    
    public ArrayList<Product> getProductsPerSQL(String sql) {
        ArrayList<Product> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Product product = productFromDB(resultSet);
                list.add(product);
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
    
    private Product productFromDB(ResultSet resultSet) {
        Product product = new Product();
        try {
            product.setProduct_id(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setCategory_id(resultSet.getInt("category_id"));
            product.setSupplier_id(resultSet.getInt("supplier_id"));
            return product;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    
    public Product findProductByID(int product_id) {
        Product product = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1, product_id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                product = productFromDB(resultSet);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        } 
        return product;
    }

    
    private Product productAllFromDB(ResultSet resultSet) {
        Product product = new Product();
        try {
            product.setProduct_id(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setCategory_name(resultSet.getString("category"));
            product.setSupplier_name(resultSet.getString("supplier"));
            return product;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}