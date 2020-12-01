package garage.web.controllers;

import garage.core.entity.Order;
import garage.core.entity.Parking;
import garage.core.entity.catalog.Type;
import garage.core.entity.order.Item;
import garage.core.entity.order.Status;
import garage.core.entity.parking.SpaceStatus;
import garage.core.repository.*;
import garage.web.authentication.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrdersController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping(path = "/orders", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order, @AuthenticationPrincipal AuthUser authUser) {
        var user = userRepository.findByUsername(authUser.getUsername()).get();
        var customer = customerRepository.findByLicensePlate(order.getLicensePlate());
        var catalogs = catalogRepository.findAllById(() -> order.getItems().stream().map(i -> i.getCatalog().getId()).iterator());
        var parkingSpace = parkingSpaceRepository.findVacant().stream().findFirst();
        order.setUser(user);
        customer.ifPresent(order::setCustomer);
        order.setItems(Item.listOf(catalogs));
        order.getItems().stream().filter(i -> i.getCatalog().getType() == Type.PARKING)
            .forEach(item -> {
                parkingSpace.ifPresent(ps -> {
                    ps.setStatus(SpaceStatus.OCCUPIED);
                    var parking = Parking
                            .builder()
                            .parkingSpace(ps)
                            .checkInAt(LocalDateTime.now())
                            .build();
                    item.setParking(parking);
                });
            });
        order.setStatus(Status.OPEN);
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

    @GetMapping(path = "/orders/open/license-plate/{licensePlate}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> findOpenByLicensePlate(@PathVariable String licensePlate) {
        return orderRepository.findOpenByLicensePlate(licensePlate)
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
