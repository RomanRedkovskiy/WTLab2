package albumForum.web.servlets.comment;

import albumForum.service.comment.interfaces.CommentService;
import albumForum.service.factory.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteCommentServlet extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = ServiceFactory.getCommentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getPathInfo().substring(1));
        commentService.deleteById(id);
        resp.sendRedirect(req.getHeader("referer"));
    }
}
