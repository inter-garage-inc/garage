package garage.web.controllers;

import garage.core.entity.Customer;
import garage.core.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomersController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/customers", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        return customerRepository.findByCpfCnpj(customer.getCpfCnpj())
            .map(c -> ResponseEntity.badRequest().build())
            .orElse(ResponseEntity.status(HttpStatus.CREATED).body(customerRepository.save(customer)));
    }

    @GetMapping(path = "/customers/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return customerRepository.findById(id)
            .map(c -> ResponseEntity.ok().body(c))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/customers/cpf-cnpj/{cpfCnpj}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> findByCpfCnpj(@PathVariable String cpfCnpj) {
        return customerRepository.findByCpfCnpj(cpfCnpj)
            .map(c -> ResponseEntity.ok().body(c))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/customers/license-plate/{licensePlate}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> findByLicensePlate(@PathVariable String licensePlate) {
        return customerRepository.findByLicensePlate(licensePlate)
            .map(c -> ResponseEntity.ok().body(c))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/customers/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        return customerRepository.findById(id).map(c -> {
            var updated = customerRepository.save(c.update(customer));
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/customers/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return customerRepository.findById(id).map(c -> {
            customerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
