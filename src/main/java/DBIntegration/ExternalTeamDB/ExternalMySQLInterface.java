package DBIntegration.ExternalTeamDB;

import DBIntegration.BaseInterface.BaseMySQLInterface;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ExternalMySQLInterface extends BaseMySQLInterface {
    public ArrayNode resultSetToJSON(ResultSet rs) throws SQLException;
}
