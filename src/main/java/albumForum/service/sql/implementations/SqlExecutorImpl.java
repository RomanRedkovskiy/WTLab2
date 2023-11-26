package albumForum.service.sql.implementations;

import albumForum.model.pair.Pair;
import albumForum.service.factory.SqlObjectsFactory;
import albumForum.service.jdbc.interfaces.JdbcConnectionPool;
import albumForum.service.sql.interfaces.SqlExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SqlExecutorImpl implements SqlExecutor {

    private final JdbcConnectionPool jdbcConnectionPool;

    private SqlExecutorImpl() {
        jdbcConnectionPool = SqlObjectsFactory.getJdbcConnectionPool();
    }

    public static SqlExecutorImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final SqlExecutorImpl INSTANCE = new SqlExecutorImpl();
    }

    @Override
    public void update(String table, Collection<String> columns, Pair<String,String> id, Collection<String> newValues) throws SQLException {
        String whereQuery = String.format("%s = %s",id.first(),id.second());
        String setQuery = createEqualsPartOfQuery(newValues,columns);
        String sql = String.format("UPDATE %s SET %s WHERE %s",table,setQuery,whereQuery);
        executeNonReturn(sql);
    }

    @Override
    public void insert(String table, Collection<String> columns, Collection<String> values) throws SQLException {
        String columnsString = String.join(",",columns);
        String valuesString = String.join(",",values);
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",table,columnsString,valuesString);
        executeNonReturn(sql);
    }

    @Override
    public void select(String table, Collection<String> columns, Collection<String> values, Consumer<ResultSet> resultSetConsumer) throws SQLException {
        String columnsString = String.join(",",columns);
        String whereQuery = createEqualsPartOfQuery(values,columns);
        String sql = String.format("SELECT * FROM %s WHERE %s",table,whereQuery) ;
        executeSql(sql,resultSetConsumer);
    }

    @Override
    public void select(String table, Collection<String> columns, Collection<String> values, int limit, int offset, Consumer<ResultSet> resultSetConsumer) throws SQLException {
        String columnsString = String.join(",",columns);
        String whereQuery = createEqualsPartOfQuery(values,columns);
        String sql = String.format("SELECT * FROM %s WHERE %s LIMIT %d OFFSET %d",table,whereQuery,limit,offset) ;
        executeSql(sql,resultSetConsumer);
    }

    @Override
    public void delete(String table, Collection<String> columns, Collection<String> values) throws SQLException {
        String whereQuery = createEqualsPartOfQuery(values,columns);
        String sql = String.format("DELETE FROM %s WHERE %s",table,whereQuery);
        executeNonReturn(sql);
    }

    //fix this please
    private String createEqualsPartOfQuery(Collection<String> values,Collection<String> columns){
        List<String> valuesList = new ArrayList<>(values);
        List<String> columnList = new ArrayList<>(columns);
        List<String> comparedValues = IntStream.range(0,values.size()).
                mapToObj(index->String.format("%s = %s",columnList.get(index),valuesList.get(index))).toList();
        return String.join(",", comparedValues);
    }

    @Override
    public void executeNonReturn(String sql) throws SQLException {
        try(Statement statement = jdbcConnectionPool.getConnection().createStatement()){
            statement.execute(sql);
        }
    }

    @Override
    public void executeSql(String sql, Consumer<ResultSet> resultSetConsumer) throws SQLException{
        try(Statement statement = jdbcConnectionPool.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            resultSetConsumer.accept(resultSet);
        }
    }

}
