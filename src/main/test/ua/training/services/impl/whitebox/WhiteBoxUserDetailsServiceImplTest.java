package ua.training.services.impl.whitebox;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import ua.training.entities.Role;
import ua.training.repositories.UserRepository;
import ua.training.services.impl.UserDetailsServiceImpl;

/**
 * White Box Testing is a software testing method in which the
 * internal structure/ design/ implementation of the item being
 * tested is known to the tester.
 *
 * @author Stanislav_Holovachuk, Olena_Patsevko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiteBoxUserDetailsServiceImplTest {

    private static final String NAME_GMAIL_COM = "name@gmail.com";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl serviceUnderTest;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Captor
    private ArgumentCaptor<String> usernameCaptor;

    @Test
    public void loadUserByUsername_captureUsernameParameter() {
        when(userRepository.findByUsername(usernameCaptor.capture()))
                .thenReturn(prepareUser("123"));

        serviceUnderTest.loadUserByUsername(NAME_GMAIL_COM);

        assertThat(usernameCaptor.getValue(), is(NAME_GMAIL_COM));
        verify(userRepository).findByUsername(NAME_GMAIL_COM);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void loadUserByUsername_throwNotFound() {
        //given
        when(userRepository.findByUsername(NAME_GMAIL_COM))
                .thenReturn(null);

        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(NAME_GMAIL_COM);

        //when
        serviceUnderTest.loadUserByUsername(NAME_GMAIL_COM);
    }

    @Test
    public void loadUserByUsername_proceedNormally() {
        when(userRepository.findByUsername(NAME_GMAIL_COM))
                .thenReturn(prepareUser("123"));

        org.springframework.security.core.userdetails.User actual =
                (User) serviceUnderTest.loadUserByUsername(NAME_GMAIL_COM);

        assertThat(actual, is(notNullValue()));
    }


    @Test
    public void withoutPassword_loadUserByUsername_throwNotFound() {
        //given
        when(userRepository.findByUsername(NAME_GMAIL_COM))
                .thenReturn(prepareUser(null));

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot pass null or empty values to constructor");

        //when
        serviceUnderTest.loadUserByUsername(NAME_GMAIL_COM);
    }

    @Test
    public void withoutUsername_loadUserByUsername_throwNotFound() {
        //given
        when(userRepository.findByUsername(NAME_GMAIL_COM))
                .thenReturn(prepareUser(null));

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot pass null or empty values to constructor");

        //when
        serviceUnderTest.loadUserByUsername(NAME_GMAIL_COM);
    }

    private ua.training.entities.User prepareUser(String password) {
        return new ua.training.entities.User() {
            {
                setId(0L);
                setFirstName("Name");
                setPassword(password);
                setUsername(NAME_GMAIL_COM);
                setRoles(Set.of(prepareRole(0L, "INSPECTOR"), prepareRole(1L, "CLIENT")));
            }
        };
    }

    private Role prepareRole(long i, String name) {
        return new Role() {{
            setId(i);
            setName(name);
        }};
    }
}
