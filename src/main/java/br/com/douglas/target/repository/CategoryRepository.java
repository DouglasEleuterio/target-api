package br.com.douglas.target.repository;

import br.com.douglas.target.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
    Optional<List<Category>> findByName(String title);
}
