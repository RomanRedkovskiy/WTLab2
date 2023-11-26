package albumForum.dao.interfaces;

import albumForum.model.entity.comment.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Long, Comment> {

    List<Comment> findByAlbumId(Long id);

    List<Comment> findByAlbumId(Long id, int limit, int offset);
}
