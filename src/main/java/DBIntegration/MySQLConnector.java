package DBIntegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.*;

public class MySQLConnector implements MySQLInterface {

  private String url;
  private String username;
  private String password;
  private Connection connection;

  /**
   * JDBC mySQL connector Constructor
   * 
   * @param host     the name of the host
   * @param port     the port the database is open to
   * @param database the database to be connected to
   * @param username the username for database access
   * @param password the password for database access
   */
  public MySQLConnector(String host, int port, String database, String username, String password) {
    this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    this.username = username;
    this.password = password;
  }

  /**
   * Tries to connect to the server
   */
  @Override
  public void connect() throws SQLException {
    if (connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection(url, username, password);
      System.out.println("Connected to databse.");
    }
  }

  /**
   * Executes a given query
   *
   * @param query      the query sent to the database
   * @return ResultSet the resulting data returned
   */
  @Override
  public ResultSet executeQuery(String query) throws SQLException {
    if (connection == null || connection.isClosed()) {
      throw new SQLException("Database not connected.");
    }
    Statement stmt = connection.createStatement();
    return stmt.executeQuery(query);
  }

  /**
   * Updates the database
   *
   * @param query the update to the database
   * @return int the number of affected rows
   */
  @Override
  public int executeUpdate(String query) throws SQLException {
    if (connection == null || connection.isClosed()) {
      throw new SQLException("Database not connected.");
    }
    try (Statement stmt = connection.createStatement()) {
      return stmt.executeUpdate(query);
    }
  }

  /**
   * Returns query as a JSON
   *
   * @param rs query result
   * @return JSON
   */
  @Override
  public ArrayNode resultSetToJson(ResultSet rs) throws SQLException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayNode arrayNode = objectMapper.createArrayNode(); // Use instance to create array
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


  /**
   * Closes the database connection
   */
  @Override
  public void close() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        System.out.println("Database connection closed.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
