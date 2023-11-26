package albumForum.web.servlets.album;

import albumForum.config.FetchingConfiguration;
import albumForum.config.SessionAttributeNames;
import albumForum.model.attributesholder.implementation.HttpSessionAttributesHolder;
import albumForum.model.entity.comment.CommentDto;
import albumForum.model.entity.album.Album;
import albumForum.service.comment.interfaces.CommentService;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.album.interfaces.AlbumService;
import albumForum.service.user.interfaces.UserLoginService;
import albumForum.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AlbumServlet extends HttpServlet {

    private AlbumService albumService;
    private CommentService commentService;
    private UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        albumService = ServiceFactory.getAlbumService();
        commentService = ServiceFactory.getCommentService();
        userLoginService = ServiceFactory.getUserLoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Long id = optionalId.get();
            Optional<Album> album = albumService.findById(id);
            String pageStr = req.getParameter("page");
            if (album.isPresent()) {
                req.setAttribute("album", album.get());
                int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
                int offset = (page - 1) * FetchingConfiguration.ENTITIES_SELECT_COUNT;
                String username = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession())).getUsername();
                List<CommentDto> comments = commentService.findCommentsForAlbum(id, FetchingConfiguration.ENTITIES_SELECT_COUNT, offset).stream().map(x -> new CommentDto(x, x.getPublisherUsername().equals(username))).toList();
                req.setAttribute("comments", comments);
                req.setAttribute("page", page);
                req.setAttribute("isAdmin", req.getSession().getAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME));
                req.getRequestDispatcher("/WEB-INF/pages/album/album.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
