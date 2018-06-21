package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    
}
