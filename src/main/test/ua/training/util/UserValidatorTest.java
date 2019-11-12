package ua.training.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ua.training.entities.User;
import ua.training.repositories.UserRepository;
import ua.training.services.UserService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;

    @MockBean
    private UserService userService;


    @Test
    public void Given_CorrectInputData_WhenCheckingWithValidator_Then_ShouldBeNoErrors() {

        UserValidator userValidator = new UserValidator();
        userValidator.setUserService(userService); //it must work without setter. why it is not autowired

        //everything to make it valid
        User validUser = new User();
        validUser.setPassword("123");
        validUser.setPasswordConfirm("123");
        validUser.setUsername("testusername");

        Mockito.when(userService.findByUsername("testusername"))
                .thenReturn(null);

        Errors errors = new BeanPropertyBindingResult(validUser, "validUser");
        userValidator.validate(validUser, errors);

        assertFalse(errors.hasErrors());
    }


    @Test
    public void Given_InvalidUsername_WhenCheckingWithValidator_Then_ShouldBeUsernameError() {
        UserValidator userValidator = new UserValidator();
        userValidator.setUserService(userService); //it must work without setter. why it is not autowired

        //everything to make it valid except the username
        User userWithInvalidUsername = new User();
        userWithInvalidUsername.setPassword("123");
        userWithInvalidUsername.setPasswordConfirm("123");
        userWithInvalidUsername.setUsername("1");

        Mockito.when(userService.findByUsername("1"))
                .thenReturn(null);

        Errors errors = new BeanPropertyBindingResult(userWithInvalidUsername, "userWithInvalidUsername");
        userValidator.validate(userWithInvalidUsername, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("username"));
    }

    @Test
    public void Given_ExistingUsername_WhenCheckingWithValidator_Then_ShouldBeUsernameError() {
        UserValidator userValidator = new UserValidator();
        userValidator.setUserService(userService); //it must work without setter. why it is not autowired

        //everything to make it valid except the username
        User userWithExistingUsername = new User();
        userWithExistingUsername.setPassword("123");
        userWithExistingUsername.setPasswordConfirm("123");
        userWithExistingUsername.setUsername("testusername");

        Mockito.when(userService.findByUsername("testusername"))
                .thenReturn(new User());

        Errors errors = new BeanPropertyBindingResult(userWithExistingUsername, "userWithExistingUsername");
        userValidator.validate(userWithExistingUsername, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("username"));
    }

    @Test
    public void Given_InvalidPassword_WhenCheckingWithValidator_Then_ShouldBePasswordError() {
        UserValidator userValidator = new UserValidator();
        userValidator.setUserService(userService); //it must work without setter. why it is not autowired

        //everything to make it valid except the username
        User userWithInvalidPassword = new User();
        userWithInvalidPassword.setPassword("#");
        userWithInvalidPassword.setPasswordConfirm("#");
        userWithInvalidPassword.setUsername("testusername");

        Mockito.when(userService.findByUsername("testusername"))
                .thenReturn(null);

        Errors errors = new BeanPropertyBindingResult(userWithInvalidPassword, "userWithInvalidPassword");
        userValidator.validate(userWithInvalidPassword, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("password"));
    }

    @Test
    public void Given_DifferentPasswords_WhenCheckingWithValidator_Then_ShouldBePasswordError() {
        UserValidator userValidator = new UserValidator();
        userValidator.setUserService(userService); //it must work without setter. why it is not autowired

        //everything to make it valid except the username
        User userWithDifferentPasswords = new User();
        userWithDifferentPasswords.setPassword("password_itself");
        userWithDifferentPasswords.setPasswordConfirm("password_confirmation");
        userWithDifferentPasswords.setUsername("testusername");

        Mockito.when(userService.findByUsername("testusername"))
                .thenReturn(null);

        Errors errors = new BeanPropertyBindingResult(userWithDifferentPasswords, "userWithDifferentPasswords");
        userValidator.validate(userWithDifferentPasswords, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passwordConfirm"));
    }
}