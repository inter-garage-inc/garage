package garage.web.controllers;

import garage.core.entity.Order;
import garage.core.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersController {
    @Autowired
    private OrderRepository orderRepository;

    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping(path = "/orders", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping(path = "/orders", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> index() {
        return orderRepository.findAll();
    }

    @GetMapping(path = "/orders/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        return orderRepository.findById(id)
                .map(o -> ResponseEntity.ok().body(o))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/orders/{id}/items", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> items(@PathVariable("id") Long id) {
        return orderRepository.findById(id)
                .map(o -> ResponseEntity.ok().body(o.getItems()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/orders/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return orderRepository.findById(id)
                .map(o -> {
                    orderRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/orders/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> update(@PathVariable("id") Long id, @RequestBody Order order) {
        return orderRepository.findById(id).map(o -> {
            order.setId(o.getId());
            var updated = orderRepository.save(order);
            return ResponseEntity.ok().body(o.update(updated));
        }).orElse(ResponseEntity.notFound().build());
    }
}
