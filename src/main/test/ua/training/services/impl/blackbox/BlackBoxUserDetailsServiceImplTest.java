package ua.training.services.impl.blackbox;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

import ua.training.entities.Role;
import ua.training.repositories.UserRepository;
import ua.training.services.impl.UserDetailsServiceImpl;

/**
 * Black Box Testing is a software testing method
 * in which the internal structure/ design/ implementation
 * of the item being tested is not known to the tester
 *
 * @author Stanislav_Holovachuk, Olena_Patsevko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlackBoxUserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl serviceUnderTest;

    @Before
    public void setUp() {
        when(userRepository.findByUsername(anyString())).thenReturn(prepareUser());
    }

    private ua.training.entities.User prepareUser() {
        return new ua.training.entities.User() {
            {
                setId(0L);
                setFirstName("Name");
                setPassword("123");
                setUsername("name@gmail.com");
                setRoles(Set.of(prepareRole(0L, "INSPECTOR"), prepareRole(1L, "CLIENT")));
            }

            private Role prepareRole(long i, String name) {
                return new Role() {{
                    setId(i);
                    setName(name);
                }};
            }
        };
    }

    @Test
    public void loadUserByUsername() {
        org.springframework.security.core.userdetails.User actual =
                (User) serviceUnderTest.loadUserByUsername("anyStringDoesNotMatter");

        assertThat(actual, is(notNullValue()));
        assertThat(actual.getUsername(), is("name@gmail.com"));
        assertThat(actual.getPassword(), is("123"));

        Collection<GrantedAuthority> authorities = actual.getAuthorities();
        assertThat(authorities, hasSize(2));
        assertThat(authorities.stream().map(GrantedAuthority::getAuthority).collect(toList()),
                containsInAnyOrder("CLIENT", "INSPECTOR"));
//        assertThat(authorities, everyItem(hasProperty("role", is(notNullValue()))));

        Consumer<Integer> assertColumn = (column) -> assertThat(column, Matchers.is(greaterThanOrEqualTo(0)));
        actual.getAuthorities().forEach(authority -> assertColumn.accept(authority.getAuthority().length()));

    }

}
