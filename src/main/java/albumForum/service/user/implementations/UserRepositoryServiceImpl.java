package albumForum.service.user.implementations;

import albumForum.dao.interfaces.UserDao;
import albumForum.model.entity.user.User;
import albumForum.service.factory.DaoFactory;
import albumForum.service.user.interfaces.UserRepositoryService;

import java.util.List;
import java.util.Optional;

public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserDao userDao;

    private UserRepositoryServiceImpl() {
        userDao = DaoFactory.getUserDao();
    }

    private static class Holder {
        private static final UserRepositoryServiceImpl INSTANCE = new UserRepositoryServiceImpl();
    }

    public static UserRepositoryServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
