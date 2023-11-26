package albumForum.dao.interfaces;

import albumForum.model.entity.album.Album;

import java.util.List;

public interface AlbumDao extends GenericDao<Long, Album>{

    List<Album> findByQuery(String query, int limit, int offset);
}
