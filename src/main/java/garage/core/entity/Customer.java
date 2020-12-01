package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import garage.core.EntityBase;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

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

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicles_id")
    private List<Vehicle> vehicles;

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private List<Order> orders;

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer update(Customer attributes) {
        this.name = attributes.getName();
        this.cpfCnpj = attributes.getCpfCnpj();
        this.phone = attributes.getPhone();
        this.address = attributes.getAddress();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
