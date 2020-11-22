package ua.training.services.impl.moduletesting;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ua.training.entities.User;
import ua.training.repositories.UserRepository;
import ua.training.services.impl.UserServiceImpl;


/**
 * TASK 6: MODULE TESTING
 *
 * @author Stanislav_Holovachuk, Olena_Patsevko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private static final int ENCODED_STRING_LENGTH = 60;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;


    @Test
    public void addUser() {
        User user = new User();
        user.setPassword("11");
        user.setUsername("sometest@mail.ru");
        userService.registerUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void registerUser_encodePassword() {
        //given
        User user = new User();
        user.setPassword("111");
        user.setUsername("sometest@mail.ru");
        when(userRepository.save(userArgumentCaptor.capture())).thenReturn(user);

        //when
        userService.registerUser(user);

        //then
        assertThat(userArgumentCaptor.getValue().getPassword(), is(notNullValue()));
        assertThat(userArgumentCaptor.getValue().getPassword(), is(not("111")));
        assertThat(userArgumentCaptor.getValue().getPassword().length(), is(ENCODED_STRING_LENGTH));
    }


}