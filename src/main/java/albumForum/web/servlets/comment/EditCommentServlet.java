package albumForum.web.servlets.comment;

import albumForum.model.entity.comment.Comment;
import albumForum.service.comment.interfaces.CommentService;
import albumForum.service.factory.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class EditCommentServlet extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = ServiceFactory.getCommentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getPathInfo().substring(1));
        String message = req.getParameter("message");
        Optional<Comment> commentOptional = commentService.findById(id);
        if(commentOptional.isPresent()){
            commentOptional.get().setMessage(message);
            commentService.update(commentOptional.get());
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
