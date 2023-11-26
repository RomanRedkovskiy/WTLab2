package albumForum.web.servlets.locale;

import albumForum.config.SessionAttributeNames;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(SessionAttributeNames.LANGUAGE_ATTRIBUTE_NAME,req.getParameter("language"));
        resp.sendRedirect(req.getHeader("referer"));
    }
}
