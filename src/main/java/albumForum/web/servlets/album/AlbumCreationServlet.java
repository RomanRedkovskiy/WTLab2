package albumForum.web.servlets.album;

import albumForum.model.entity.album.Album;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.album.interfaces.AlbumService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

public class AlbumCreationServlet extends HttpServlet {

    private AlbumService albumService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        albumService = ServiceFactory.getAlbumService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/album/albumCreation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String imageUrl = req.getParameter("imageUrl");
        Date releaseDate = Date.valueOf(req.getParameter("releaseDate"));
        Album album = new Album();
        album.setDescription(description);
        album.setTitle(title);
        album.setImageUrl(imageUrl);
        album.setReleaseDate(releaseDate);
        albumService.save(album);
        resp.sendRedirect(String.format("%s/album/create", req.getContextPath()));
    }
}
