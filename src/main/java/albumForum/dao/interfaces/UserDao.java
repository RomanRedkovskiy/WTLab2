package albumForum.dao.interfaces;

import albumForum.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<Long, User> {

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
