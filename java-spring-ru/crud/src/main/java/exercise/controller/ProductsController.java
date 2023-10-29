package exercise.controller;

import java.lang.annotation.Target;
import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
//    Реализуйте полный CRUD сущности товара. Создайте обработчики для просмотра списка всех задач и конкретной задачи, создания, обновления и удаления товара:
//
//    GET /products – просмотр списка всех товаров
//    GET /products/{id} – просмотр конкретного товара
//    POST /products – добавление нового товара. При указании id несуществующей категории должен вернуться ответ с кодом 400 Bad request
//    PUT /products/{id} – редактирование товара. При редактировании мы должны иметь возможность поменять название, цену и категорию товара. При указании id несуществующей категории также должен вернуться ответ с кодом 400 Bad request
//    DELETE /products – удаление товара

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> index() {
        List<ProductDTO> products = productRepository.findAll().stream().map(productMapper::map).toList();
        return products;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO show(@PathVariable Long id) {
        return productMapper.map(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found!")));

    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO productData) {
        Product product = productMapper.map(productData);
        if ( product.getCategory() == null ){
            throw new NullPointerException("Category Not Found!");
        }
        productRepository.save(product);
        return productMapper.map(product);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@Valid @RequestBody ProductUpdateDTO productData, @PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product with id " + id + " not found!"));
        if ( product.getCategory() == null ){
            throw new NullPointerException("Category Not Found!");
        }
        productMapper.update(productData,product);
        productRepository.save(product);
        return productMapper.map(product);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    // END
}
