package br.com.douglas.target.controllers;

import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.dtos.ProductDto;
import br.com.douglas.target.models.Category;
import br.com.douglas.target.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static br.com.douglas.target.dtos.ProductDto.toProduct;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<MessageDto> getAllProducts() {
        MessageDto messageDto = service.getAll();
        return ResponseEntity.ok(messageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getCategoryById(@PathVariable String id) {
        try {
            MessageDto byId = service.findById(id);
            return ResponseEntity.ok(service.findById(id));
        } catch (ClassNotFoundException e) {
            MessageDto notFound = new MessageDto(e.getLocalizedMessage(), new Date(), Category.class);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<MessageDto> getProductsByCategory(@PathVariable String categoryId) {
        MessageDto byId = service.findByCategoryId(categoryId);
        return ResponseEntity.ok(byId);
    }

    @PostMapping()
    public ResponseEntity<MessageDto> saveProduct(@RequestBody ProductDto product) {
        MessageDto save = service.save(toProduct(product));
        return ResponseEntity.ok(save);
    }
}
