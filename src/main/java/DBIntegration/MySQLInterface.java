package DBIntegration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface MySQLInterface {
  void connectToDatabase(String username, String password) throws SQLException;
  void queryDatabase() throws SQLException;
  void connectToAndQueryDatabase(String username, String password) throws SQLException;
}
