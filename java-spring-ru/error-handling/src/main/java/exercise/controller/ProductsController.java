package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public Product show(@PathVariable String id) {
        Product product = productRepository
                .findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found.", id)));
        return product;
    }

    @PutMapping(path = "/{id}")
    public Product update(@PathVariable String id, @RequestBody Product product) {
        Product searchedProduct = productRepository
                .findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found.", id)));
        searchedProduct.setTitle(product.getTitle());
        searchedProduct.setPrice(product.getPrice());
        productRepository.save(searchedProduct);
        return searchedProduct;
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
