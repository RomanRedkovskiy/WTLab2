package albumForum.service.album.implementations;

import albumForum.dao.interfaces.AlbumDao;
import albumForum.model.entity.album.Album;
import albumForum.service.factory.DaoFactory;
import albumForum.service.album.interfaces.AlbumService;

import java.util.List;
import java.util.Optional;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumDao albumDao;

    private AlbumServiceImpl() {
        albumDao = DaoFactory.getAlbumDao();
    }

    public static AlbumServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final AlbumServiceImpl INSTANCE = new AlbumServiceImpl();
    }

    @Override
    public void save(Album album) {
        albumDao.save(album);
    }

    @Override
    public void deleteById(Long id) {
        albumDao.deleteById(id);
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

    @Override
    public List<Album> findByQuery(String query, int limit, int offset) {
        return albumDao.findByQuery(query,limit,offset);
    }

    @Override
    public List<Album> findAll(int limit, int offset) {
        return albumDao.findAll(limit,offset);
    }


    @Override
    public Optional<Album> findById(Long id) {
        return albumDao.findById(id);
    }
}
