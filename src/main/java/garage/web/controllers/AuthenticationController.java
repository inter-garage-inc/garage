package garage.web.controllers;

import garage.core.repository.UserRepository;
import garage.web.authentication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private AuthUserDetailsService authUserDetailsService;

    @Autowired
    private AuthToken authToken;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/authentication", produces = "application/json")
    public ResponseEntity<AuthResponse> authentication(@RequestBody AuthRequest authRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(authRequest.getUsername());

        String token = authToken.createToken(authUserDetails.getId(), authUserDetails.getUsername(), authUserDetails.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
