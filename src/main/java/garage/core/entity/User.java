package garage.core.entity;

import garage.core.EntityBase;
import garage.core.entity.user.Role;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends EntityBase {
    @NonNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User update(User attributes) {
        this.username = attributes.getUsername();
        this.password = attributes.getPassword();
        this.role = attributes.getRole();
        return this;
    }
}
