package DBIntegration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector implements MySQLInterface {

  @Override
  public void connectToAndQueryDatabase(String username, String password) throws SQLException {
    String url = "jdbc:mysql://localhost:3306/myDatabase"; // Replace with your actual DB URL

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
