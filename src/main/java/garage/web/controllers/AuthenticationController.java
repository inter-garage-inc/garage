package garage.web.controllers;

import garage.core.entity.User;
import garage.core.repository.UserRepository;
import garage.web.authentication.*;
import garage.web.authentication.payload.CredentialsRequest;
import garage.web.authentication.payload.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/authentication", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JwtResponse> authentication(@RequestBody CredentialsRequest credentialsRequest) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(credentialsRequest.getUsername(), credentialsRequest.getPassword()));
        var authUser = (AuthUser) authentication.getPrincipal();
        if(authUser.isEnabled()) {
            var jwt = jwtUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping(value = "/authentication", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public User claimUser(@AuthenticationPrincipal AuthUser user) {
        return userRepository.findByUsername(user.getUsername()).get();
    }
}
