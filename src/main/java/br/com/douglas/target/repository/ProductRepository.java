package br.com.douglas.target.repository;

import br.com.douglas.target.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> getProductByCategoriesId(Integer categories_id);
}
