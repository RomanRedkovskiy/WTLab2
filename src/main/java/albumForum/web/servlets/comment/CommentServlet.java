package albumForum.web.servlets.comment;

import albumForum.model.attributesholder.implementation.HttpSessionAttributesHolder;
import albumForum.model.entity.comment.Comment;
import albumForum.service.comment.interfaces.CommentService;
import albumForum.service.factory.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CommentServlet extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = ServiceFactory.getCommentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comment comment = new Comment();
        comment.setMessage(req.getParameter("message"));
        comment.setAlbumId(Long.parseLong(req.getParameter("albumId")));
        commentService.saveCurrentUserComment(comment,new HttpSessionAttributesHolder(req.getSession()));
        resp.sendRedirect(req.getHeader("referer"));
    }
}
