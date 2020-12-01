package garage.web.controllers;

import garage.core.entity.parking.ParkingSpace;
import garage.core.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
public class ParkingSpacesController {
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @GetMapping(path = "/parking-spaces", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingSpace> index() {
        return parkingSpaceRepository.findAll();
    }

    @GetMapping(path = "/parking-spaces/vacant", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingSpace> indexVacant() {
        return parkingSpaceRepository.findVacant();
    }

    @PostMapping(path = "/parking-spaces", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingSpace index(@RequestBody ParkingSpace parkingSpace) {
        return parkingSpaceRepository.save(parkingSpace);
    }

    @PutMapping(path = "/parking-spaces/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ParkingSpace parkingSpace) {
        return parkingSpaceRepository.findById(id).map(ps -> {
                    var updated = parkingSpaceRepository.save(ps.update(parkingSpace));
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/parking-spaces/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return parkingSpaceRepository.findById(id).map(ps -> {
                    parkingSpaceRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
