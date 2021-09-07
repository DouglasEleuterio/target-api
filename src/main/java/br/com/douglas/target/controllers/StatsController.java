package br.com.douglas.target.controllers;

import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    MovieService service;

    @GetMapping("/totalMovies")
    public ResponseEntity<MessageDto> getTotalMovies() {
        MessageDto allMovies = service.getTotalMovies();
        return ResponseEntity.ok(allMovies);
    }

    @GetMapping("/totalMoviesByGenre")
    public ResponseEntity<MessageDto> getTotalMoviesByGenre() {
        MessageDto allMovies = service.getTotalMoviesByGenre();
        return ResponseEntity.ok(allMovies);
    }
}
