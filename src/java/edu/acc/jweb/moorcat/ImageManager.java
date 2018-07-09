package edu.acc.jweb.moorcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ImageManager extends DBManager {

    private final DataSource dataSource;

    public ImageManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void copyIO(InputStream contentStream, OutputStream outputStream) throws IOException {
        try {
            byte[] buffer = new byte[32 * 1024];

            while (true) {
                int numread = contentStream.read(buffer);
                if (numread < 1) {
                    break;
                }
                outputStream.write(buffer, 0, numread);
            }
        } finally {
            contentStream.close();
        }
    }

    public boolean copyImageContent(int id, OutputStream out) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT content FROM images where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                copyIO(resultSet.getBinaryStream("content"),out);
            }

        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return false;
    }

    public void saveImage(String fileName, String contentType, InputStream inputStream, int product_id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE products SET filename = ?, content_type = ?, content = ? WHERE id = ?");
            statement.setString(1, fileName);
            statement.setString(2, contentType);
            statement.setBinaryStream(3, inputStream);
            statement.setInt(4, product_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
    }
}