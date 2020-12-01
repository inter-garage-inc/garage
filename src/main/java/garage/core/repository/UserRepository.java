package garage.core.repository;

import garage.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDetailsService {
    @Query("select u from User u where u.username = :username")
    Optional<User> findUserByUsername(String username);

    @Query("select u from User u where u.username = :username")
    User loadUserByUsername(String username);
}
