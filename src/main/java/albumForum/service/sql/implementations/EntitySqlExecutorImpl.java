package albumForum.service.sql.implementations;

import albumForum.model.entity.interfaces.Entity;
import albumForum.model.pair.Pair;
import albumForum.service.factory.SqlObjectsFactory;
import albumForum.service.reflection.ReflectionFieldValuesGetter;
import albumForum.service.sql.interfaces.EntitySqlExecutor;
import albumForum.service.sql.interfaces.SqlExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EntitySqlExecutorImpl implements EntitySqlExecutor {

    private static final String ID_FIELD = "id";
    private final SqlExecutor sqlExecutor;
    private final ReflectionFieldValuesGetter reflectionFieldValuesGetter;

    private EntitySqlExecutorImpl() {
        sqlExecutor = SqlObjectsFactory.getSqlExecutor();
        reflectionFieldValuesGetter = new ReflectionFieldValuesGetter();
    }

    public static EntitySqlExecutorImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final EntitySqlExecutorImpl INSTANCE = new EntitySqlExecutorImpl();
    }

    @Override
    public void update(String table, Entity<?> obj) throws SQLException {
        Map<String,String> fields = reflectionFieldValuesGetter.receiveFieldValues(obj);
        Pair<String,String> id = new Pair<>(ID_FIELD,obj.getId().toString());
        sqlExecutor.update(table,fields.keySet(), id,fields.values());
    }

    @Override
    public void insert(String table, Entity<?> obj) throws SQLException {
        Map<String,String> fields = reflectionFieldValuesGetter.receiveFieldValues(obj);
        sqlExecutor.insert(table,fields.keySet(),fields.values());
    }

    @Override
    public void select(String table, String id, Consumer<ResultSet> resultSetConsumer) throws SQLException {
        sqlExecutor.select(table,List.of(ID_FIELD),List.of(id),resultSetConsumer);
    }

    @Override
    public void select(String table, Collection<String> columns, Collection<String> values, Consumer<ResultSet> resultSetConsumer) throws SQLException {
        sqlExecutor.select(table,columns,values,resultSetConsumer);
    }

    @Override
    public void delete(String table, Object id) throws SQLException {
        sqlExecutor.delete(table, List.of("id"),List.of(id.toString()));
    }

    @Override
    public void selectAll(String table, Consumer<ResultSet> resultSetConsumer) throws SQLException {
        String sql = String.format("SELECT * FROM %s",table);
        sqlExecutor.executeSql(sql,resultSetConsumer);
    }

    @Override
    public void select(String table, Collection<String> columns, Collection<String> values, int limit, int offset, Consumer<ResultSet> resultSetConsumer) throws SQLException {
        sqlExecutor.select(table,columns,values,limit,offset,resultSetConsumer);
    }

    @Override
    public void executeSql(String sql, Consumer<ResultSet> resultSetConsumer) throws SQLException{
        sqlExecutor.executeSql(sql,resultSetConsumer);
    }
}
