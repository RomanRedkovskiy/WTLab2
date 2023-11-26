package albumForum.service.factory;

import albumForum.dao.implementations.CommentDaoImplImpl;
import albumForum.dao.implementations.AlbumDaoImplImpl;
import albumForum.dao.implementations.UserDaoImplImpl;
import albumForum.dao.interfaces.CommentDao;
import albumForum.dao.interfaces.AlbumDao;
import albumForum.dao.interfaces.UserDao;

public class DaoFactory {

    private DaoFactory(){}

    public static AlbumDao getAlbumDao(){
        return AlbumDaoImplImpl.getInstance();
    }

    public static CommentDao getCommentDao(){
        return CommentDaoImplImpl.getInstance();
    }

    public static UserDao getUserDao(){
        return UserDaoImplImpl.getInstance();
    }
}
