package br.com.douglas.target.repository;

import br.com.douglas.target.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<List<Movie>> findByTitle(String title);

}
