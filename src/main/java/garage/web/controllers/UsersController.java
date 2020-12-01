package garage.web.controllers;

import garage.core.entity.User;
import garage.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/users", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping(path = "/users", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<User> index() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> show(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .map(u -> ResponseEntity.ok().body(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/users/find_by/{username}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username) {
        return userRepository.findUserByUsername(username)
                .map(u -> ResponseEntity.ok().body(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/users/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .map(u -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/users/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody @Valid User user) {
        return userRepository.findById(id).map(u -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            var updated = userRepository.save(u.update(user));
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
}
