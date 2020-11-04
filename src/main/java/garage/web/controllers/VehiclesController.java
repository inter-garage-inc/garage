package garage.web.controllers;

import garage.core.entity.Customer;
import garage.core.entity.Vehicle;
import garage.core.repository.CustomerRepository;
import garage.core.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehiclesController {
    @Autowired
    private VehicleRepository vehicleRepository;

    public VehiclesController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping(path = "/vehicles", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @GetMapping(path = "/vehicles", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<?> index() {
        return vehicleRepository.findAll();
    }

    @GetMapping(path = "/vehicles/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        return vehicleRepository.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/vehicles/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return vehicleRepository.findById(id)
                .map(c -> {
                    vehicleRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/vehicles/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Vehicle vehicle) {
        return vehicleRepository.findById(id).map(v -> {
            var updated = vehicleRepository.save(v);
            return ResponseEntity.ok().body(v.update(updated));
        }).orElse(ResponseEntity.notFound().build());
    }
}
