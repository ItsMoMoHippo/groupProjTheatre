package DBIntegration;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MySQLInterface {

  void connect() throws SQLException;

  ResultSet executeQuery(String query) throws SQLException;

  int executeUpdate(String query) throws SQLException;

  ArrayNode resultSetToJson(ResultSet rs) throws SQLException;

  void close();
}
