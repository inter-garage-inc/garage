package garage.core.entity;

import garage.core.EntityBase;
import garage.core.entity.user.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends EntityBase {

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public User update(User attributes) {
        this.name = attributes.getName();
        this.username = attributes.getUsername();
        this.password = attributes.getPassword();
        this.role = attributes.getRole();
        this.status = attributes.getStatus();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
