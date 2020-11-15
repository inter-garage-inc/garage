package garage.core.entity;

import garage.core.EntityBase;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customers")
@EqualsAndHashCode(callSuper = false)
public class Customer extends EntityBase {
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj;

    @NotNull
    @Column(nullable = false)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Vehicle> vehicles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer update(Customer attributes) {
        this.name = attributes.getName();
        this.cpfCnpj = attributes.getCpfCnpj();
        this.phone = attributes.getPhone();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
