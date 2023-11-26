package albumForum.service.user.implementations;

import albumForum.dao.interfaces.UserDao;
import albumForum.model.entity.user.User;
import albumForum.service.user.interfaces.UserRegisterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegisterServiceImplTest {

    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserRegisterService userRegisterService = UserRegisterServiceImpl.getInstance();

    @Test
    void givenUsernameIsUnique_whenIsUsernameUnique_thenReturnTrue() {
        String username = "user";

        when(userDao.findByUsername(username)).thenReturn(Optional.empty());

        boolean actual = userRegisterService.isUsernameUnique(username);

        Assertions.assertTrue(actual);
    }

    @Test
    void givenUsernameIsNotUnique_whenIsUsernameUnique_thenReturnFalse() {
        String username = "user";

        when(userDao.findByUsername(username)).thenReturn(Optional.of(new User()));

        boolean actual = userRegisterService.isUsernameUnique(username);

        Assertions.assertFalse(actual);
    }

    @Test
    void givenValidUser_whenRegister_thenReturn() {
        String username = "user";
        String password = "password";

        userRegisterService.register(username,password);
        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);
        verify(userDao).save(user.capture());

        Assertions.assertEquals(username,user.getValue().getUsername());
    }
}