package ua.training.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import ua.training.Application;
import ua.training.config.H2TestProfileJPAConfig;


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
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenTryingToAccessUserPrivileges_withGuestRight_thenAskToLogin() throws Exception {
        this.mockMvc.perform(get("/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    @WithUserDetails("client@gmail.com")
    public void whenTryingToAccessInspectorPrivileges_withClientRight_thenShow403ErrorPage() throws Exception {
        this.mockMvc.perform(get("/inspector/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error/403"));
    }

    @Test
    @WithUserDetails("admin@gmail.com")
    public void whenTryingToAccessClientPrivileges_withInspectorRight_thenShow403ErrorPage() throws Exception {
        this.mockMvc.perform(get("/client/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error/403"));
    }

    @Test
    @WithUserDetails("client@gmail.com")
    public void whenTryingToAccessClientPrivileges_withCorrespondingRight_thenSuccess() throws Exception {
        this.mockMvc.perform(get("/client/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(forwardedUrl("/jsp/client/client-base.jsp"));
    }

    @Test
    @WithUserDetails("admin@gmail.com")
    public void whenTryingToAccessInspectorPrivileges_withCorrespondingRight_thenSuccess() throws Exception {
        this.mockMvc.perform(get("/inspector/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/inspector/upload-template"));
    }

}