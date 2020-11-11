package garage.web.controllers;

import garage.core.repository.UserRepository;
import garage.web.authentication.*;
import garage.web.authentication.data.Credentials;
import garage.web.authentication.data.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/authentication", produces = "application/json")
    public ResponseEntity<Jwt> authentication(@RequestBody Credentials authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        var authUserDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());
        var token = jwtUtil.createToken(authUserDetails);
        return ResponseEntity.ok(new Jwt(token));
    }
}
