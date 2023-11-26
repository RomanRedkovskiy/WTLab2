package albumForum.dao.implementations;

import albumForum.config.TableNamesConfiguration;
import albumForum.dao.interfaces.AlbumDao;
import albumForum.model.entity.album.Album;
import albumForum.service.factory.SqlObjectsFactory;
import lombok.extern.java.Log;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Log
public class AlbumDaoImplImpl extends GenericDaoImpl<Long, Album> implements AlbumDao {

    private AlbumDaoImplImpl() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.ALBUM_TABLE_NAME);
    }

    public static AlbumDaoImplImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Album> findByQuery(String query, int limit, int offset) {
        List<Album> result = new ArrayList<>();
        query = "%"+query+"%";
        query = String.format("'%s'",query);
        String sql = String.format("SELECT * FROM %s WHERE title LIKE %s OR description LIKE %s LIMIT %d OFFSET %d",getTable(),query,query,limit,offset);
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Album> entity = createEntity(resultSet);
            while(entity.isPresent()){
                result.add(entity.get());
                entity = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().executeSql(sql, consumer);
        } catch (SQLException exception){
            log.warning(exception.getMessage());
        }
        return result;
    }

    private static class Holder {
        private static final AlbumDaoImplImpl INSTANCE = new AlbumDaoImplImpl();
    }

    @Override
    protected Optional<Album> createEntity(ResultSet resultSet) {
        try{
            if (resultSet.next()) {
                Album album = new Album();
                album.setReleaseDate(Date.valueOf(resultSet.getString("releaseDate")));
                album.setTitle(resultSet.getString("title"));
                album.setImageUrl(resultSet.getString("imageUrl"));
                album.setDescription(resultSet.getString("description"));
                album.setId(Long.valueOf(resultSet.getString("id")));
                return Optional.of(album);
            }
        } catch (SQLException e){
            log.warning(e.getMessage());
        }

        return Optional.empty();
    }
}
