package albumForum.service.sql.interfaces;

import albumForum.model.entity.interfaces.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.function.Consumer;

public interface EntitySqlExecutor {

    void update(String table, Entity<?> obj) throws SQLException;

    void insert(String table, Entity<?> obj) throws SQLException;

    void select(String table, String id, Consumer<ResultSet> resultSetConsumer) throws SQLException;

    void select(String table, Collection<String> columns, Collection<String> values, Consumer<ResultSet> resultSetConsumer) throws SQLException;

    void delete(String table, Object id) throws SQLException;

    void selectAll(String table,Consumer<ResultSet> resultSetConsumer) throws  SQLException;

    void select(String table, Collection<String> columns, Collection<String> values,int limit, int offset, Consumer<ResultSet> resultSetConsumer) throws SQLException;

    void executeSql(String sql, Consumer<ResultSet> resultSetConsumer) throws SQLException;
}
