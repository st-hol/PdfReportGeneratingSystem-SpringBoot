package ua.training;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void When_TryingToAccessUserPrivilegesWithGuestRight_Then_ShouldAskToLogin() throws Exception {
        this.mockMvc.perform(get("/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    @WithUserDetails("client@g.c")
    public void When_TryingToAccessInspectorPrivilegesWithClientRight_Then_Show403() throws Exception {
        this.mockMvc.perform(get("/inspector/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error/403"));
    }

    @Test
    @WithUserDetails("admin@g.c")
    public void When_TryingToAccessClientPrivilegesWithInspectorRight_Then_AllowAccess() throws Exception {
        this.mockMvc.perform(get("/client/personal-cabinet"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(forwardedUrl("/jsp/client/client-base.jsp"));;
    }
}