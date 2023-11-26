package albumForum.service.factory;

import albumForum.service.jdbc.implementations.JdbcConnectionPoolImpl;
import albumForum.service.jdbc.interfaces.JdbcConnectionPool;
import albumForum.service.sql.implementations.EntitySqlExecutorImpl;
import albumForum.service.sql.implementations.SqlExecutorImpl;
import albumForum.service.sql.interfaces.EntitySqlExecutor;
import albumForum.service.sql.interfaces.SqlExecutor;

public class SqlObjectsFactory {
    private SqlObjectsFactory(){}

    public static JdbcConnectionPool getJdbcConnectionPool(){
        return JdbcConnectionPoolImpl.getInstance();
    }

    public static SqlExecutor getSqlExecutor(){
        return SqlExecutorImpl.getInstance();
    }

    public static EntitySqlExecutor getEntitySqlExecutor(){
        return EntitySqlExecutorImpl.getInstance();
    }
}
