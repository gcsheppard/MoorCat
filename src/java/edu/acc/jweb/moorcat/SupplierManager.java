package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM suppliers ORDER BY id");
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Supplier supplier = supplierFromDB(resultSet);
                list.add(supplier);
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
    
    private Supplier supplierFromDB(ResultSet resultSet) {
        Supplier supplier = new Supplier();
        try {
            supplier.setSupplier_id(resultSet.getInt("id"));
            supplier.setSupplier_name(resultSet.getString("name"));
            return supplier;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    
}
