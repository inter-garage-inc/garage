package garage.web.controllers;

import garage.core.entity.Customer;
import garage.core.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomersController {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping(path = "/customers", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer register(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }



    @GetMapping(path = "/customers", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> index() {
        return customerRepository.findAll();
    }

    @GetMapping(path = "/customers/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        return customerRepository.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/customers/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return customerRepository.findById(id)
                .map(c -> {
                    customerRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/customers/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> update(@PathVariable("id") Long id, @RequestBody Customer customer) {
        return customerRepository.findById(id).map(c -> {
            var updated = customerRepository.save(c);
            return ResponseEntity.ok().body(c.update(updated));
        }).orElse(ResponseEntity.notFound().build());
    }
}
