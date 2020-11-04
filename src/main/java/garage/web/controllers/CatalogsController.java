package garage.controller.web;

import garage.core.entity.Catalog;
import garage.core.entity.Customer;
import garage.core.repository.CatalogRepository;
import garage.core.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogsController {
    @Autowired
    private CatalogRepository catalogRepository;

    public CatalogsController(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @PostMapping(path = "/catalogs", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Catalog create(@RequestBody Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @GetMapping(path = "/catalogs", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Catalog> index() {
        return catalogRepository.findAll();
    }

    @GetMapping(path = "/catalogs/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        return catalogRepository.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/catalogs/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return catalogRepository.findById(id)
                .map(c -> {
                    catalogRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/catalogs/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Catalog> update(@PathVariable("id") Long id, @RequestBody Catalog catalog) {
        return catalogRepository.findById(id).map(c -> {
            var updated = catalogRepository.save(c);
            return ResponseEntity.ok().body(c.update(catalog));
        }).orElse(ResponseEntity.notFound().build());
    }
}
