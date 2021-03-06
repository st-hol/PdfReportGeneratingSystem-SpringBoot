package ua.training.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import ua.training.entities.User;
import ua.training.services.UserService;


/**
 * @author Stanislav_Holovachuk, Olena_Patsevko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserValidator userValidator;

    @Test
    public void givenCorrectInputData_whenCheckingWithValidator_thenShouldBeNoErrors() {
        //everything to make it valid
        User validUser = new User();
        validUser.setPassword("123");
        validUser.setPasswordConfirm("123");
        validUser.setUsername("testusername@gmail.com");

        Mockito.when(userService.findByUsername("testusername@gmail.com"))
                .thenReturn(null);

        Errors errors = new BeanPropertyBindingResult(validUser, "validUser");
        userValidator.validate(validUser, errors);

        assertFalse(errors.hasErrors());
    }


    @Test
    public void givenInvalidUsername_whenCheckingWithValidator_thenShouldBeUsernameError() {
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
    public void givenExistingUsername_whenCheckingWithValidator_thenShouldBeUsernameError() {
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
    public void givenInvalidPassword_whenCheckingWithValidator_thenShouldBePasswordError() {
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
    public void givenDifferentPasswords_whenCheckingWithValidator_thenShouldBePasswordError() {
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