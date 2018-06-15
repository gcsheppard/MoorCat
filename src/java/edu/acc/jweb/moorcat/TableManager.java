package edu.acc.jweb.moorcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class TableManager extends DBManager {
    private final DataSource dataSource;


 public TableManager (DataSource dataSource) {
    this.dataSource = dataSource;
}
 
 public void clearTables() {
    clearTable("products");
    clearTable("categories");
    clearTable("suppliers");
    clearTable("orders");
    clearTable("order_items");
    clearTable("users");
    clearTable("department_status");
 }
 
 private void clearTable(String tableName) {
    Connection connection = null;
    Statement statement = null;
    try {
        connection = dataSource.getConnection();
        statement = connection.createStatement();
        String sql = "DELETE FROM " + tableName;
        statement.executeUpdate(sql);
    } catch (SQLException sqle) {
        throw new RuntimeException(sqle);
    } finally {
        close(statement);
        close(connection);
    }
 }
         
}