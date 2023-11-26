package albumForum.web.servlets.user;

import albumForum.model.attributesholder.implementation.HttpSessionAttributesHolder;
import albumForum.model.entity.user.User;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserLoginService;
import albumForum.service.user.interfaces.UserRepositoryService;
import albumForum.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProfileServlet extends HttpServlet {

    private UserLoginService userLoginService;
    private UserRepositoryService userRepositoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userLoginService = ServiceFactory.getUserLoginService();
        userRepositoryService = ServiceFactory.getUserRepositoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession()));
        req.setAttribute("user", currentUser);
        req.getRequestDispatcher("/WEB-INF/pages/user/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession()));
        currentUser.setEmail(req.getParameter("email"));
        currentUser.setBio(req.getParameter("bio"));
        userRepositoryService.update(currentUser);
        req.setAttribute("user", currentUser);
        RequestUtility.sendRedirect(resp, req, "profile");
    }
}
