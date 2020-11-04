package garage.web.controller;

import garage.web.controllers.UsersController;
import garage.core.entity.User;
import garage.core.entity.user.Role;
import garage.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import support.Fixture;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    protected MockMvc mvc;
    @MockBean
    private UserRepository repository;

    @Test
    public void whenListAllUser() throws Exception {
        var user = User.builder().username("foo").password("bar").role(Role.ADMIN).build();
        given(repository.findAll()).willReturn(List.of(user));
        mvc.perform(get("/users")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenShowUserSuccessfuly() throws Exception {
        var user = User.builder().username("foo").password("bar").role(Role.ADMIN).build();
        given(repository.findById(1L)).willReturn(Optional.of(user));
        mvc.perform(get("/users/1")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenShowUserButNotfound() throws Exception {
        var user = User.builder().username("foo").password("bar").role(Role.ADMIN).build();
        given(repository.findById(1L)).willReturn(Optional.empty());

        mvc.perform(get("/users/1")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateAUser() throws Exception {
        var user = User.builder().username("foo").password("bar").role(Role.ADMIN).build();
        given(repository.save(user)).willReturn(user);

        mvc.perform(post("/users")
                .content(Fixture.json("users-data"))
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }
}
