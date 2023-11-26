package albumForum.web.filters;

import albumForum.model.attributesholder.implementation.HttpSessionAttributesHolder;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserLoginService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AuthenticationFilter implements Filter {

    private final UserLoginService userLoginService = ServiceFactory.getUserLoginService();
    private final List<String> urlsWithoutRequiredAuthentication = List.of("/login","/register");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String contextPath = httpServletRequest.getContextPath();
        if(urlsWithoutRequiredAuthentication.stream().anyMatch
                (x->httpServletRequest.getRequestURI().startsWith(contextPath+x)) ||
                userLoginService.isUserLogged(new HttpSessionAttributesHolder(httpServletRequest.getSession()))){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
