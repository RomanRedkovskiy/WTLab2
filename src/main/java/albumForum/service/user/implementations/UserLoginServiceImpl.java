package albumForum.service.user.implementations;

import albumForum.config.SessionAttributeNames;
import albumForum.dao.interfaces.UserDao;
import albumForum.exception.UserNotLoggedException;
import albumForum.model.attributesholder.interfaces.AttributesHolder;
import albumForum.model.encoder.interfaces.Encoder;
import albumForum.model.entity.user.Role;
import albumForum.model.entity.user.Status;
import albumForum.model.entity.user.User;
import albumForum.service.factory.AuxiliaryFactory;
import albumForum.service.factory.DaoFactory;
import albumForum.service.user.LoginResult;
import albumForum.service.user.interfaces.UserLoginService;
import lombok.extern.java.Log;

import java.util.Optional;

@Log
public class UserLoginServiceImpl implements UserLoginService {

    private final UserDao userDao;
    private final Encoder encoder;

    private UserLoginServiceImpl() {
        userDao = DaoFactory.getUserDao();
        encoder = AuxiliaryFactory.getEncoder();
    }

    public static UserLoginServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final UserLoginServiceImpl INSTANCE = new UserLoginServiceImpl();
    }

    @Override
    public boolean isUserLogged(AttributesHolder attributesHolder) {
        return attributesHolder.containsAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
    }

    @Override
    public User receiveLoggedUser(AttributesHolder attributesHolder) throws UserNotLoggedException {
        User user = (User) attributesHolder.getAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
        if (user == null) {
            log.severe("User is not logged");
            throw new UserNotLoggedException();
        }
        return user;
    }

    @Override
    public boolean doesUserExist(String username, String password) {
        Optional<User> user = userDao.findByUsername(username);
        String encodedPassword = encoder.encode(password);
        return user.isPresent() && user.get().getPassword().equals(encodedPassword);
    }

    @Override
    public LoginResult login(String username, String password, AttributesHolder attributesHolder) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty() || !isPasswordCorrect(user.get(),password)) {
            return LoginResult.WRONG_USERNAME_OR_PASSWORD;
        } else if(user.get().getStatus().equals(Status.BLOCKED)){
            return LoginResult.USER_IS_BLOCKED;
        }
        attributesHolder.setAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME, user.get());
        attributesHolder.setAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME,user.get().getRole().equals(Role.ADMIN));
        return LoginResult.SUCCESS;
    }

    private boolean isPasswordCorrect(User user,String password){
        return encoder.encode(password).equals(user.getPassword());
    }

    @Override
    public void logout(AttributesHolder attributesHolder) {
        attributesHolder.deleteAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
    }
}
