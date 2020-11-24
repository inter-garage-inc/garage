package garage.web.controller;

import garage.core.entity.User;
import garage.core.entity.user.Role;
import garage.core.repository.UserRepository;
import garage.web.controllers.AuthenticationController;
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
    public void whenAuthenticateUserWithSuccessfully() throws Exception {
        var user = User.builder().username("foo").password("$2y$12$fPqQBe.GDMBsuruFAYNApOJ3nIJ6k8WSI4urNcAK8lFJMuwNLNA1u").role(Role.ADMIN).build();

        given(repository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
        mvc.perform(post("/authentication")
                .content(Fixture.json("credentials-data"))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenAuthenticateUserAndFailure() throws Exception {
        mvc.perform(post("/authentication")
                .content(Fixture.json("credentials-data"))
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}
