package albumForum.service.user.implementations;

import albumForum.dao.interfaces.UserDao;
import albumForum.model.encoder.interfaces.Encoder;
import albumForum.model.entity.user.Role;
import albumForum.model.entity.user.Status;
import albumForum.model.entity.user.User;
import albumForum.service.factory.AuxiliaryFactory;
import albumForum.service.factory.DaoFactory;
import albumForum.service.user.interfaces.UserRegisterService;

public class UserRegisterServiceImpl implements UserRegisterService {

    private UserDao userDao;
    private Encoder encoder;

    private UserRegisterServiceImpl() {
        userDao = DaoFactory.getUserDao();
        encoder = AuxiliaryFactory.getEncoder();
    }

    public static UserRegisterServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final UserRegisterServiceImpl INSTANCE = new UserRegisterServiceImpl();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return userDao.findByUsername(username).isEmpty();
    }

    @Override
    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(Role.USER);
        user.setStatus(Status.UNBLOCKED);
        userDao.save(user);
    }
}
