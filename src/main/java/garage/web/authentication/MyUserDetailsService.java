package garage.web.authentication;

import garage.core.repository.UserRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user.map(u -> new MyUserDetails(u))
                .orElse(null);
    }
}
