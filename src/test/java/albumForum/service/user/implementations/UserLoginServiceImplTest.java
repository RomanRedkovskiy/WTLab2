package albumForum.service.user.implementations;


import albumForum.config.SessionAttributeNames;
import albumForum.dao.interfaces.UserDao;
import albumForum.model.attributesholder.interfaces.AttributesHolder;
import albumForum.model.encoder.interfaces.Encoder;
import albumForum.model.entity.user.Role;
import albumForum.model.entity.user.Status;
import albumForum.model.entity.user.User;
import albumForum.service.user.LoginResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserLoginServiceImplTest {

    @Mock
    private UserDao userDao;
    @Mock
    private Encoder encoder;
    @Mock
    private AttributesHolder attributesHolder;
    @InjectMocks
    private UserLoginServiceImpl userLoginService = UserLoginServiceImpl.getInstance();


    @Test
    void givenUserIsLogged_whenIsUserLogged_thenReturnTrue() {
        when(attributesHolder.containsAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME)).thenReturn(true);

        boolean actual = userLoginService.isUserLogged(attributesHolder);

        Assertions.assertTrue(actual);
    }

    @Test
    void givenUserIsNotLogged_whenIsUserLogged_thenReturnFalse() {
        boolean actual = userLoginService.isUserLogged(attributesHolder);

        Assertions.assertFalse(actual);
    }

    @Test
    void givenUserIsLogged_whenReceiveLoggedUser_thenReturnUser() {
        User expected = new User();

        when(attributesHolder.getAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME)).thenReturn(expected);

        User actual = userLoginService.receiveLoggedUser(attributesHolder);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    void givenUserDoesNotExist_whenLogin_thenReturnLoginResult() {
        String username = "user";
        String password = "pass";

        when(userDao.findByUsername(username)).thenReturn(Optional.empty());

        LoginResult actual = userLoginService.login(username,password,attributesHolder);

        Assertions.assertEquals(LoginResult.WRONG_USERNAME_OR_PASSWORD,actual);
    }

    @Test
    void givenUserExists_whenLogin_thenReturnLoginResult() {
        User user = new User();
        user.setUsername("user");
        user.setStatus(Status.UNBLOCKED);
        user.setPassword("encoded");
        user.setRole(Role.USER);
        String password = "not encoded";

        when(userDao.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(encoder.encode(password)).thenReturn(user.getPassword());

        LoginResult actual = userLoginService.login(user.getUsername(),password,attributesHolder);

        Assertions.assertEquals(LoginResult.SUCCESS,actual);
    }
}