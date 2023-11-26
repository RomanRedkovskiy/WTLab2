package albumForum.web.servlets.user;

import albumForum.model.entity.user.Status;
import albumForum.model.entity.user.User;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserRepositoryService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class UserStatusChangerServlet extends HttpServlet {

    private UserRepositoryService userRepositoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepositoryService = ServiceFactory.getUserRepositoryService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getPathInfo().substring(1));
        Optional<User> user = userRepositoryService.findById(id);
        if(user.isPresent()){
            user.get().setStatus(Status.valueOf(req.getParameter("status")));
            userRepositoryService.update(user.get());
        }
        resp.sendRedirect(req.getHeader("referer"));
    }
}
