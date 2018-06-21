package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class ShipManager extends DBManager {
    private final DataSource dataSource;
    
    public ShipManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addShipMethod(String ship_method) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO ship_methods (ship_method) values (?)");
            statement.setString(1, ship_method);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
    public ArrayList<ShipMethod> getShipMethods() {
        ArrayList<ShipMethod> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM ship_methods ORDER BY id");
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ShipMethod shipMethod = shipMethodFromDB(resultSet);
                list.add(shipMethod);
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
    
    public String getShipMethodName(int ship_method_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String shipMethodName = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT ship_method FROM ship_methods WHERE id = ?");
            statement.setInt(1, ship_method_id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                shipMethodName = resultSet.getString("ship_method");
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        } 
        return shipMethodName;
    }
    
    private ShipMethod shipMethodFromDB(ResultSet resultSet) {
        ShipMethod shipMethod = new ShipMethod();
        try {
            shipMethod.setShip_method_id(resultSet.getInt("id"));
            shipMethod.setShip_method(resultSet.getString("ship_method"));
            return shipMethod;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    
    public void addShipDetail(int order_id, String ship_method, String tracking) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO ship_details (order_id, ship_method, tracking) values (?,?,?)");
            statement.setInt(1, order_id);
            statement.setString(2, ship_method);
            statement.setString(3, tracking);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
}
