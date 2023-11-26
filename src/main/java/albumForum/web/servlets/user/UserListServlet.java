package albumForum.web.servlets.user;

import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserRepositoryService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserListServlet extends HttpServlet {

    private UserRepositoryService userRepositoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepositoryService = ServiceFactory.getUserRepositoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userRepositoryService.findAll());
        req.getRequestDispatcher("/WEB-INF/pages/user/userList.jsp").forward(req,resp);
    }
}
