package garage.web.controllers;

import garage.core.entity.Plan;
import garage.core.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @PostMapping(path = "/plans", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Plan create(@RequestBody Plan plan) {
        return planRepository.save(plan);
    }

    @GetMapping(path = "/plans", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Plan> index() {
        return planRepository.findAll();
    }

    @GetMapping(path = "/plan/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Plan> show(@PathVariable("id") Long id) {
        return planRepository.findById(id)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/plan/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return planRepository.findById(id)
                .map(p -> {
                    planRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/plan/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Plan> update(@PathVariable("id") Long id, @RequestBody @Valid Plan plan) {
        return planRepository.findById(id)
                .map(p -> ResponseEntity.ok().body(planRepository.save(p.update(plan))))
                .orElse(ResponseEntity.notFound().build());
    }

}
