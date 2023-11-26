package albumForum.web.servlets.authentication;

import albumForum.model.attributesholder.implementation.HttpSessionAttributesHolder;
import albumForum.model.attributesholder.interfaces.AttributesHolder;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserLoginService;
import albumForum.service.user.interfaces.UserRegisterService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private UserRegisterService userRegisterService;
    private UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userLoginService = ServiceFactory.getUserLoginService();
        userRegisterService = ServiceFactory.getUserRegisterService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/authentication/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(userRegisterService.isUsernameUnique(request.getParameter("username"))){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AttributesHolder attributesHolder = new HttpSessionAttributesHolder(request.getSession());
            userRegisterService.register(username, password);
            userLoginService.login(username, password,attributesHolder);
            response.sendRedirect(String.format("%s/login",request.getContextPath()));
        } else{
            request.setAttribute("errorMessage", "Username is not unique");
            doGet(request,response);
        }
    }
}
