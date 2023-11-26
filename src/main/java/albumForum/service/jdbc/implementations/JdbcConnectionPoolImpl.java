package albumForum.service.jdbc.implementations;

import albumForum.model.jdbc.JdbcConnectionPoolInfo;
import albumForum.service.configReader.ConfigReader;
import albumForum.service.jdbc.interfaces.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnectionPoolImpl implements JdbcConnectionPool {

    private final List<Connection> availableConnections;
    private final JdbcConnectionPoolInfo info;

    private JdbcConnectionPoolImpl() {
        info = ConfigReader.readConnectionPoolInfo();
        availableConnections = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            initializeConnections(info);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static JdbcConnectionPoolImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final JdbcConnectionPoolImpl INSTANCE = new JdbcConnectionPoolImpl();
    }


    private void initializeConnections(JdbcConnectionPoolInfo info) throws SQLException {
        for (int count = 0; count < info.initialConnections(); count++) {
            availableConnections.add(DriverManager.getConnection(info.url(), info.user(), info.password()));
        }
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            availableConnections.add(DriverManager.getConnection(info.url(), info.user(), info.password()));
        }
        return availableConnections.remove(availableConnections.size() - 1);
    }

    @Override
    public synchronized void releaseConnection(Connection connection) {
        if(info.initialConnections() < availableConnections.size()){
            availableConnections.add(connection);
        } else{
            try {
                connection.close();
            } catch (SQLException sqlException){}
        }
    }
}
