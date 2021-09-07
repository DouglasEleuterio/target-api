package br.com.douglas.target.controllers;

import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.models.Category;
import br.com.douglas.target.services.CategoryService;
import br.com.douglas.target.services.Exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService service;

    @PostMapping()
    public ResponseEntity<MessageDto> saveCategory(@RequestBody Category category) {
        MessageDto savedMovie = service.save(category);
        return ResponseEntity.ok(savedMovie);
    }

    @GetMapping
    public ResponseEntity<MessageDto> getAllCategories(){
        MessageDto messageDto = service.getAll();
        return ResponseEntity.ok(messageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getCategoryById(@PathVariable String id) {
        try {
            MessageDto byId = service.findById(id);
            return ResponseEntity.ok(service.findById(id));
        } catch (CategoryNotFoundException e) {
            MessageDto notFound = new MessageDto(e.getLocalizedMessage(), new Date(), Category.class);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }
    }
}
