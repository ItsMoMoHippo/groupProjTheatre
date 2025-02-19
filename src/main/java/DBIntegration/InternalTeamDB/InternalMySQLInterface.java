package DBIntegration.InternalTeamDB;

import DBIntegration.BaseInterface.BaseMySQLInterface;

import java.sql.SQLException;

public interface InternalMySQLInterface extends BaseMySQLInterface {
  int executeUpdate(String query) throws SQLException;
}
