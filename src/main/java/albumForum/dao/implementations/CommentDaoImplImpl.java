package albumForum.dao.implementations;

import albumForum.config.TableNamesConfiguration;
import albumForum.dao.interfaces.CommentDao;
import albumForum.model.entity.comment.Comment;
import albumForum.service.factory.SqlObjectsFactory;
import lombok.extern.java.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Log
public class CommentDaoImplImpl extends GenericDaoImpl<Long, Comment> implements CommentDao {

    private CommentDaoImplImpl() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.COMMENT_TABLE_NAME);
    }

    public static CommentDaoImplImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CommentDaoImplImpl INSTANCE = new CommentDaoImplImpl();
    }

    @Override
    protected Optional<Comment> createEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setMessage(resultSet.getString("message"));
                comment.setAlbumId(resultSet.getLong("albumId"));
                comment.setId(resultSet.getLong("id"));
                comment.setPublisherUsername(resultSet.getString("publisherUsername"));
                return Optional.of(comment);
            }
        } catch (SQLException e){
            log.warning(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findByAlbumId(Long id) {
        List<Comment> result = new ArrayList<>();
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Comment> comment = createEntity(resultSet);
            while(comment.isPresent()){
                result.add(comment.get());
                comment = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().select(getTable(), List.of("albumId"), List.of(id.toString()), consumer);
        } catch (SQLException sqlException){
            log.warning(sqlException.getMessage());
        }
        return result;
    }

    @Override
    public List<Comment> findByAlbumId(Long id, int limit, int offset) {
        List<Comment> result = new ArrayList<>();
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Comment> comment = createEntity(resultSet);
            while(comment.isPresent()){
                result.add(comment.get());
                comment = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().select(getTable(), List.of("albumId"), List.of(id.toString()), limit,offset,consumer);
        } catch (SQLException sqlException){
            log.warning(sqlException.getMessage());
        }
        return result;
    }
}
