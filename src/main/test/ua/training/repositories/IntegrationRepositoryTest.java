package ua.training.repositories;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ua.training.Application;
import ua.training.config.H2TestProfileJPAConfig;
import ua.training.entities.Report;
import ua.training.entities.Role;
import ua.training.entities.User;


/**
 * TASK 7: INTEGRATION TESTING
 *
 * Tests on repository methods.
 * In-memory H2 database is used.
 *
 * @author Stanislav_Holovachuk, Olena_Patsevko
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        Application.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
@Sql(
        scripts = "/testsql/test-user-data.sql",
        config = @SqlConfig(commentPrefix = "#", separator = ";")
)
@Transactional
public class IntegrationRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
    }

    @Test
    public void reports_findAllByPerson() {
        //given
        User user = userRepository.findByUsername("test1@gmail.com");

        //when
        Page<Report> reports = reportRepository.findAllByPerson(user, PageRequest.of(0,1));

        //then
        assertThat(reports, is(notNullValue()));
        assertThat(reports.getTotalElements(), is(1L));
        assertThat(reports.get().collect(Collectors.toList()).get(0).getPerson().getUsername(),
                is("test1@gmail.com"));
    }

    @Test
    public void reports_findAllByNewlyCreatedPerson_returnZero() {
        //given
        User user = new User(){{
            setId(123L);
            setFirstName("new");
            setUsername("new@gmail.com");
        }};

        //when
        Page<Report> reports = reportRepository.findAllByPerson(user, PageRequest.of(0,1));

        //then
        assertThat(reports, is(notNullValue()));
        assertThat(reports.get().collect(Collectors.toList()), is(empty()));
        assertThat(reports.getTotalElements(), is(0L));
    }

    @Test
    public void persons_findByUsername() {
        //given
        final String username = "test1@gmail.com";

        //when
        User user = userRepository.findByUsername(username);

        //then
        assertThat(user, is(notNullValue()));
        assertThat(user.getFirstName(), is("Test1"));
    }

    @Test
    public void roles_findById_clientRole() {
        //given
        final long id = 1L;

        //when
        Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalStateException("ROLE NOT FOUND"));

        //then
        assertThat(role, is(notNullValue()));
        assertThat(role.getName(), is("CLIENT"));
    }

    @Test
    public void roles_findById_inspectorRole() {
        //given
        final long id = 0L;

        //when
        Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalStateException("ROLE NOT FOUND"));

        //then
        assertThat(role, is(notNullValue()));
        assertThat(role.getName(), is("INSPECTOR"));
    }

}