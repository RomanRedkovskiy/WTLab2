package albumForum.service.comment.interfaces;

import albumForum.model.attributesholder.interfaces.AttributesHolder;
import albumForum.model.entity.comment.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(Long id);

    void deleteById(Long id);

    List<Comment> findAllCommentsForAlbum(Long albumId);

    List<Comment> findCommentsForAlbum(Long albumId, int limit, int offset);

    List<Comment> findAll(int limit, int offset);

    void saveCurrentUserComment(Comment comment, AttributesHolder attributesHolder);

    void update(Comment comment);
}
