package albumForum.service.user.interfaces;

public interface UserRegisterService {

    boolean isUsernameUnique(String username);

    void register(String username, String password);
}
