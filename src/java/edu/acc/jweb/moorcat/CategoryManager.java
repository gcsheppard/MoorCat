package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class CategoryManager extends DBManager {
    
    private final DataSource dataSource;
    
    public CategoryManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addCategory(String category) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO categories (name) values (?)");
            statement.setString(1, category);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            close(statement);
            close(connection);
        }
    }
    
}
