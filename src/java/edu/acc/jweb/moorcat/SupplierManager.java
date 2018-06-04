package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class SupplierManager extends DBManager {
    
    private final DataSource dataSource;
    
    public SupplierManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addSupplier(String supplier) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO suppliers (name) values (?)");
            statement.setString(1, supplier);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
}
