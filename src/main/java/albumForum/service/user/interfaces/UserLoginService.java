package albumForum.service.user.interfaces;

import albumForum.model.attributesholder.interfaces.AttributesHolder;
import albumForum.model.entity.user.User;
import albumForum.service.user.LoginResult;

public interface UserLoginService {

    boolean isUserLogged(AttributesHolder attributesHolder);

    User receiveLoggedUser(AttributesHolder attributesHolder);

    boolean doesUserExist(String username, String password);

    LoginResult login(String username, String password, AttributesHolder attributesHolder);

    void logout(AttributesHolder attributesHolder);
}
