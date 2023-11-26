package albumForum.service.user.interfaces;

import albumForum.exception.EntityNotFoundException;
import albumForum.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryService {

    Optional<User> findById(Long id) throws EntityNotFoundException;

    void save(User user);

    void deleteById(Long id);

    void update(User user);

    List<User> findAll();
}
