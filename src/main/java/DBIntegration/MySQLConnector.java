package DBIntegration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector implements MySQLInterface {

  String url = "jdbc:mysql://https://smcse.city.ac.uk/phpmyadmin/"; // database url
  boolean connected = false;
  Connection con;

  @Override
  public void connectToDatabase(String username, String password) throws SQLException {
    while (!connected) {
      try (con = DriverManager.getConnection(url, username, password)) {
        connected = true;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    System.out.println("connected to db");
  }

  @Override
  public void queryDatabase() throws SQLException {
    connectToDatabase("john", "password123");
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1")) {
      while (rs.next()) {
        int x = rs.getInt("a");
        String s = rs.getString("b");
        float f = rs.getFloat("c");
        System.out.println("a: " + x + ", b: " + s + ", c: " + f);
      }
    }

  }

  @Override
  public void connectToAndQueryDatabase(String username, String password) throws SQLException {

    try (Connection con = DriverManager.getConnection(url, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1")) {

      while (rs.next()) {
        int x = rs.getInt("a");
        String s = rs.getString("b");
        float f = rs.getFloat("c");
        System.out.println("a: " + x + ", b: " + s + ", c: " + f);
      }

    } catch (SQLException e) {
      e.printStackTrace(); // Log the error properly in production
    }
  }
}
