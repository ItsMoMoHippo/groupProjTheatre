package DBIntegration.InternalTeamDB;

import java.sql.*;
import java.sql.Connection;

public class InternalMySQLConnector implements InternalMySQLInterface {

  private final String url;
  private final String username;
  private final String password;
  private Connection connection;

  /**
   * JDBC MySQL Connector Constructor for internal team usage
   *
   * @param host
   * @param port
   * @param database
   * @param username
   * @param password
   */
  public InternalMySQLConnector(String host, int port, String database, String username, String password) {
    this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    this.username = username;
    this.password = password;
  }
  
  /**
   * Connects to the database
   * */
  @Override
  public void connect() throws SQLException {
    if (connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection(url, username, password);
      System.out.println("connected to db");
    }
  }

  /**
   * Queries the database
   *
   * @param query
   * @return ResultSet
   * @throws SQLException
   * */
  @Override
  public ResultSet executeQuery(String query) throws SQLException {
    if (connection == null || connection.isClosed()) {
      throw new SQLException("Database not connected.");
    }
    Statement stmt = connection.createStatement();
    return stmt.executeQuery(query);
  }

  /**
   * Updates fields in the database
   *
   * @param query
   * @return NumberOfLineChanges
   * @throws SQLException
   * */
  @Override
  public int executeUpdate(String query) throws SQLException {
    if (connection == null || connection.isClosed()) {
      throw new SQLException("Datbase is not connected.");
    }
    try (Statement stmt = connection.createStatement()) {
      return stmt.executeUpdate(query);
    }
  }

  /**
   * Closes the database
   *
   * @throws SQLException
   * */
  @Override
  public void close() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        System.out.println("Database connection closed");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
