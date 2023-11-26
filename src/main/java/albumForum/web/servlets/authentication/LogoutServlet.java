package albumForum.web.servlets.authentication;

import albumForum.model.attributesholder.implementation.HttpSessionAttributesHolder;
import albumForum.model.attributesholder.interfaces.AttributesHolder;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserLoginService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    private UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userLoginService = ServiceFactory.getUserLoginService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AttributesHolder attributesHolder = new HttpSessionAttributesHolder(request.getSession());
        userLoginService.logout(attributesHolder);
        response.sendRedirect(String.format("%s/login",request.getContextPath()));
    }
}
