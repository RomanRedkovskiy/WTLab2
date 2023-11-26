package albumForum.service.jdbc.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface JdbcConnectionPool {

    Connection getConnection() throws SQLException;

    void releaseConnection(Connection connection);
}
