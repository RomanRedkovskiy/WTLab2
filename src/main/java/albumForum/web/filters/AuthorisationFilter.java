package albumForum.web.filters;

import albumForum.model.attributesholder.implementation.HttpSessionAttributesHolder;
import albumForum.model.entity.user.Role;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.user.interfaces.UserLoginService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AuthorisationFilter implements Filter {

    private final UserLoginService userLoginService = ServiceFactory.getUserLoginService();
    private final List<String> albumUrls = List.of("/album/create","/album/edit");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String contextPath = httpServletRequest.getContextPath();
        if(albumUrls.stream().noneMatch(x->httpServletRequest.getRequestURI().startsWith(contextPath+x)) || isAdmin(httpServletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean isAdmin(HttpServletRequest request){
        return userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(request.getSession())).getRole().equals(Role.ADMIN);
    }
}
