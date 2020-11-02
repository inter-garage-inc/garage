package garage.web.controller;

import garage.controller.web.AuthenticationController;
import garage.core.entity.User;
import garage.core.entity.user.Role;
import garage.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import support.Fixture;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    protected MockMvc mvc;
    @MockBean
    private UserRepository repository;

    @Test
    public void whenAuthenticateUserWithSuccessfuly() throws Exception {
        var user = User.builder().username("foo").password("bar").role(Role.ADMIN).build();
        given(repository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).willReturn(Optional.of(user));
        mvc.perform(post("/authentication/create")
                .content(Fixture.json("login-data"))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenAuthenticateUserAndFailure() throws Exception {
        mvc.perform(post("/authentication/create")
                .content(Fixture.json("login-data"))
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}
