package garage.core.repository;

import garage.core.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.cpfCnpj = :cpfCnpj")
    Optional<Customer> findByCpfCnpj(String cpfCnpj);

    @Query("SELECT c FROM Customer c JOIN c.vehicle v WHERE v.licensePlate = :licensePlate")
    Optional<Customer> findByLicensePlate(String licensePlate);
}