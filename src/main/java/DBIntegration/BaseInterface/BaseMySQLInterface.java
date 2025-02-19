package DBIntegration.BaseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseMySQLInterface {
  void connect() throws SQLException;

  ResultSet executeQuery(String query) throws SQLException;

  void close();
}
