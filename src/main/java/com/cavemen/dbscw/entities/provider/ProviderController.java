package com.cavemen.dbscw.entities.provider;

import com.cavemen.dbscw.entities.category.Category;
import com.cavemen.dbscw.entities.category.CategoryController;
import com.cavemen.dbscw.entities.customer.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    private final ProviderService providerService;
    private final CategoryController categoryController;
    public ProviderController(ProviderService providerService, CategoryController categoryController) {
        this.providerService = providerService;
        this.categoryController = categoryController;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Provider>> getAllProviders(){
        return new ResponseEntity<>(providerService.getAllProviders(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Provider>> getProvider(@PathVariable("id") Long id){
        return new ResponseEntity<>(providerService.getProviderById(id), HttpStatus.OK);
    }

    @GetMapping("/getCategories/{prov_id}")
    public ResponseEntity<Set<String>> getCategories(@PathVariable("prov_id") Long providerID) {
        return new ResponseEntity<>(providerService.getCategories(providerID), HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Provider> addProvider(@RequestBody Provider provider) {
        return new ResponseEntity<>(providerService.addProvider(provider), HttpStatus.OK);
    }

    @PostMapping("/connect/{prov_id}/{cat_id}")
    public void connectToCategory(@PathVariable("prov_id") Long providerID, @PathVariable("cat_id") Long categoryID) {
        Provider provider = getProvider(providerID).getBody().get();
        Category category = categoryController.getCategory(categoryID).getBody().get();
        if (!provider.getCategorySet().contains(category))
            providerService.connect(providerID, categoryID);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider){
        return new ResponseEntity<>(providerService.updateProvider(provider), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProvider(@PathVariable("id") Long id){
        providerService.deleteProvider(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
