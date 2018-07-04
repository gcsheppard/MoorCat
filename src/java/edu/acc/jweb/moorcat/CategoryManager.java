package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public ArrayList<Category> getCategories() {
        ArrayList<Category> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM categories ORDER BY id");
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Category category = categoryFromDB(resultSet);
                list.add(category);
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

    private Category categoryFromDB(ResultSet resultSet) {
        Category category = new Category();
        try {
            category.setCategory_id(resultSet.getInt("id"));
            category.setCategory_name(resultSet.getString("name"));
            return category;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

}
