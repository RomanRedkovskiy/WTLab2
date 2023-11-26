package albumForum.service.factory;

import albumForum.service.comment.implementations.CommentServiceImpl;
import albumForum.service.comment.interfaces.CommentService;
import albumForum.service.album.implementations.AlbumServiceImpl;
import albumForum.service.album.interfaces.AlbumService;
import albumForum.service.user.implementations.UserLoginServiceImpl;
import albumForum.service.user.implementations.UserRegisterServiceImpl;
import albumForum.service.user.implementations.UserRepositoryServiceImpl;
import albumForum.service.user.interfaces.UserLoginService;
import albumForum.service.user.interfaces.UserRegisterService;
import albumForum.service.user.interfaces.UserRepositoryService;

public class ServiceFactory {

    private ServiceFactory(){
    }

    public static UserLoginService getUserLoginService(){
        return UserLoginServiceImpl.getInstance();
    }

    public static UserRegisterService getUserRegisterService(){
        return UserRegisterServiceImpl.getInstance();
    }

    public static UserRepositoryService getUserRepositoryService(){
        return UserRepositoryServiceImpl.getInstance();
    }

    public static AlbumService getAlbumService(){
        return AlbumServiceImpl.getInstance();
    }

    public static CommentService getCommentService(){
        return CommentServiceImpl.getInstance();
    }
}
