package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
