package DBIntegration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface MySQLInterface {
  void connectToDatabase() throws SQLException;
  //TODO: commented out to not give compile errors, to implement
  //void queryDatabase() throws SQLException;
  void connectToAndQueryDatabase() throws SQLException;
}
