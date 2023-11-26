package albumForum.web.servlets.album;

import albumForum.config.FetchingConfiguration;
import albumForum.config.SessionAttributeNames;
import albumForum.model.entity.album.Album;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.album.interfaces.AlbumService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public class AlbumListServlet extends HttpServlet {

    private AlbumService albumService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        albumService = ServiceFactory.getAlbumService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        String pageString = req.getParameter("page");
        int page = pageString==null?1:Integer.parseInt(pageString);
        List<Album> albums = findAlbums(query,page);
        req.setAttribute("albums", albums);
        req.setAttribute("page",page);
        req.setAttribute("isAdmin",req.getSession().getAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME));
        req.getRequestDispatcher("/WEB-INF/pages/album/albumList.jsp").forward(req,resp);
    }

    private List<Album> findAlbums(String query, int page){
        int offset = (page-1)* FetchingConfiguration.ENTITIES_SELECT_COUNT;
        if(query == null){
            return albumService.findAll(FetchingConfiguration.ENTITIES_SELECT_COUNT,offset);
        } else{
            return albumService.findByQuery(query,FetchingConfiguration.ENTITIES_SELECT_COUNT,offset);
        }
    }
}
