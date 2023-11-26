package albumForum.service.album.interfaces;

import albumForum.model.entity.album.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    void save(Album album);

    void deleteById(Long id);

    void update(Album album);

    List<Album> findByQuery(String query, int limit, int offset);

    List<Album> findAll(int limit, int offset);

    Optional<Album> findById(Long id);
}
