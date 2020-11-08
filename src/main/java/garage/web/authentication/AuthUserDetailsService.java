package garage.web.authentication;

import garage.core.repository.UserRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthUserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user.map(u -> new AuthUserDetails(
                u.getId(),
                u.getUsername(),
                u.getPassword()
        )).orElse(null);
    }
}
