package albumForum.service.comment.implementations;

import albumForum.dao.interfaces.CommentDao;
import albumForum.model.attributesholder.interfaces.AttributesHolder;
import albumForum.model.entity.comment.Comment;
import albumForum.service.comment.interfaces.CommentService;
import albumForum.service.factory.DaoFactory;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserLoginService;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final UserLoginService userLoginService;

    private CommentServiceImpl() {
        commentDao = DaoFactory.getCommentDao();
        userLoginService = ServiceFactory.getUserLoginService();
    }

    public static CommentServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CommentServiceImpl INSTANCE = new CommentServiceImpl();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<Comment> findAllCommentsForAlbum(Long albumId) {
        return commentDao.findByAlbumId(albumId);
    }

    @Override
    public List<Comment> findCommentsForAlbum(Long albumId, int limit, int offset) {
        return commentDao.findByAlbumId(albumId,limit,offset);
    }

    @Override
    public List<Comment> findAll(int limit, int offset) {
        return commentDao.findAll(limit,offset);
    }

    @Override
    public void saveCurrentUserComment(Comment comment, AttributesHolder attributesHolder) {
        comment.setPublisherUsername(userLoginService.receiveLoggedUser(attributesHolder).getUsername());
        commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }
}
