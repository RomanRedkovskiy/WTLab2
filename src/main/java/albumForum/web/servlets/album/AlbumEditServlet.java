package albumForum.web.servlets.album;

import albumForum.model.entity.album.Album;
import albumForum.service.factory.ServiceFactory;
import albumForum.service.album.interfaces.AlbumService;
import albumForum.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

public class AlbumEditServlet extends HttpServlet {

    private AlbumService albumService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        albumService = ServiceFactory.getAlbumService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Optional<Album> albumOptional = albumService.findById(optionalId.get());
            if (albumOptional.isPresent()) {
                req.setAttribute("album", albumOptional.get());
                req.getRequestDispatcher("/WEB-INF/pages/album/albumEdit.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Long id = optionalId.get();
            Optional<Album> albumOptional = albumService.findById(id);
            if (albumOptional.isPresent()) {
                String title = req.getParameter("title");
                String description = req.getParameter("description");
                String imageUrl = req.getParameter("imageUrl");
                Date releaseDate = Date.valueOf(req.getParameter("releaseDate"));
                Album album = albumOptional.get();
                album.setDescription(description);
                album.setTitle(title);
                album.setImageUrl(imageUrl);
                album.setReleaseDate(releaseDate);
                albumService.update(album);
                resp.sendRedirect(String.format("%s/album/edit/%d", req.getContextPath(), id));
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
