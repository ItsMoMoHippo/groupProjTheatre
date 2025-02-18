package DBIntegration;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MySQLInterface {

  void connect() throws SQLException;

  ResultSet executeQuery(String query) throws SQLException;

  int executeUpdate(String query) throws SQLException;

  void close();
}
