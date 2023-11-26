package albumForum.dao.implementations;

import albumForum.dao.interfaces.GenericDao;
import albumForum.model.entity.interfaces.Entity;
import albumForum.service.sql.interfaces.EntitySqlExecutor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@Log
public abstract class GenericDaoImpl<I,E extends Entity<I>> implements GenericDao<I,E> {

    private final EntitySqlExecutor entitySqlExecutor;
    private final String table;

    @Override
    public Optional<E> findById(I id) {
        var obj = new Object(){
                public Optional<E> entity;
        };
        Consumer<ResultSet> consumer = (resultSet)->{
            obj.entity = createEntity(resultSet);
        };
        try {
            entitySqlExecutor.select(table, id.toString(), consumer);
        } catch (SQLException sqlException){
            obj.entity = Optional.empty();
        }
        return obj.entity;
    }

    protected abstract Optional<E> createEntity(ResultSet resultSet);

    @Override
    public void update(E entity){
        try {
            entitySqlExecutor.update(table, entity);
        } catch (SQLException e){
            log.warning(e.getMessage());
        }
    }

    @Override
    public void save(E entity) {
        try {
            entitySqlExecutor.insert(table, entity);
        } catch (SQLException e){
            log.warning(e.getMessage());
        }
    }

    @Override
    public List<E> findAll(int limit, int offset) {
        List<E> result = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s LIMIT %d OFFSET %d",table,limit,offset);
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<E> entity = createEntity(resultSet);
            while(entity.isPresent()){
                result.add(entity.get());
                entity = createEntity(resultSet);
            }
        };
        try {
            entitySqlExecutor.executeSql(sql, consumer);
        } catch (SQLException e){
            log.warning(e.getMessage());
        }
        return result;
    }

    @Override
    public void deleteById(I id) {
        try {
            entitySqlExecutor.delete(table, id);
        } catch (SQLException e){
            log.warning(e.getMessage());
        }
    }
}
