package br.com.douglas.target.controllers;

import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.dtos.MovieDto;
import br.com.douglas.target.services.MovieService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class MovieController {

    @Autowired
    MovieService service;

    @PostMapping()
    public ResponseEntity<MessageDto> saveMovie(@RequestBody MovieDto movie) {
        MessageDto savedMovie = service.save(movie.toMovie());
        return ResponseEntity.ok(savedMovie);
    }

    @GetMapping()
    public ResponseEntity<MessageDto> getAllMovies() {
        MessageDto allMovies = service.getAll();
        return ResponseEntity.ok(allMovies);
    }

    //TODO tratar as respostas adequadas.
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteMovieById(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMovieById(@PathVariable String id) {
        try {
            MessageDto byId = service.findById(id);
            return ResponseEntity.ok(service.findById(id));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/title")
    public ResponseEntity<MessageDto> getMovieByTitle(@RequestParam String title) {
        try {
            MessageDto byTitle = service.findByTitle(title);
            return ResponseEntity.ok(service.findByTitle(title));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
