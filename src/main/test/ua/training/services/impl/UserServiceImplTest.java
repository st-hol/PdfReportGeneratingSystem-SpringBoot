package ua.training.services.impl;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.entities.Role;
import ua.training.entities.User;
import ua.training.repositories.UserRepository;
import ua.training.services.UserService;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserRepository userRepository;


    @BeforeClass
    public static void setUp() {
    }


    @Test
    public void addUser() {
        User user = new User();
        user.setPassword("11");
        user.setUsername("sometest@mail.ru");
        userService.registerUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }


}