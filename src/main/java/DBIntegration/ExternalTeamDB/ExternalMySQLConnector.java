package DBIntegration.ExternalTeamDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.*;

public class ExternalMySQLConnector implements ExternalMySQLInterface {

  private final String url;
  private final String username;
  private final String password;
  private Connection connection;

  public ExternalMySQLConnector(String host, int port, String database, String username, String password) {
    this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    this.username = username;
    this.password = password;
  }

  @Override
  public void connect() throws SQLException {
    if (connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection(url, username, password);
      System.out.println("Connection established.");
    }
  }

  @Override
  public ResultSet executeQuery(String query) throws SQLException {
    if (connection == null || connection.isClosed()) {
      throw new SQLException("Database is not connected");
    }
    Statement statement = connection.createStatement();
    return statement.executeQuery(query);
  }

  @Override
  public ArrayNode resultSetToJSON(ResultSet rs) throws SQLException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayNode arrayNode = objectMapper.createArrayNode();
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    while (rs.next()) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            objectNode.putPOJO(columnName, rs.getObject(i));
        }
        arrayNode.add(objectNode);
    }
    return arrayNode;
  }

  @Override
  public void close() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        System.out.println("Connection closed.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
