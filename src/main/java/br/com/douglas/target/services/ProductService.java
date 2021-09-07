package br.com.douglas.target.services;

import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.models.Category;
import br.com.douglas.target.models.Product;
import br.com.douglas.target.repository.ProductRepository;
import br.com.douglas.target.security.jwt.AuthEntryPointJwt;
import br.com.douglas.target.services.Exceptions.CategoryNotFoundException;
import br.com.douglas.target.services.Exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.douglas.target.services.Utils.validateId;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    public MessageDto getAll() {
        return new MessageDto(
                "List of Products: ",
                new Date(),
                productRepository.findAll());
    }

    public MessageDto findById(String id) throws ClassNotFoundException {
        MessageDto messageDto = null;
        try {
            Integer identifier = validateId(id);
            Product category = productRepository.findById(identifier).orElseThrow(ClassNotFoundException::new);
            messageDto = new MessageDto("Product located: ", new Date(), category);
        } catch (ClassNotFoundException e) {
            logger.info("Product not Found: {}", id);
            throw new ClassNotFoundException("Product with id: " + id + ", not found");
        }
        return messageDto;
    }

    public MessageDto findByCategoryId(String categoryId) {
        Integer identifier = validateId(categoryId);
        Category category = new Category();
        try {
            category = (Category) categoryService.findById(categoryId).getObj();
            List<Product> productByCategoriesId = productRepository.getProductByCategoriesId(identifier)
                    .orElseThrow(ProductNotFoundException::new);
            return new MessageDto("List of products in Category: " + category.getName(), new Date(), productByCategoriesId);
        } catch (ProductNotFoundException e) {
            logger.info("Products not Found with Category: {}", category.getName());
            return new MessageDto("Product with Category: " + category.getName() + ", Not found");
        } catch (CategoryNotFoundException e) {
            logger.info("Category with id {} not found ", categoryId);
            return new MessageDto("Category with id: " + categoryId + ", Not found");
        }
    }

    public MessageDto save(Product product) {
        validateName(product.getName());
        validatePrice(product.getPrice());
        List<Category> categories = new ArrayList<>();
        product.getCategories().forEach(category -> categories.add((Category) categoryService.findById(category.getId().toString()).getObj()));

        Product prod = new Product(product.getName(), product.getPrice(), categories);

        Product saved = productRepository.save(prod);

        return new MessageDto("Product saved: ", new Date(), saved);
    }

    private void validateName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name of product required and greater then 3 character");
        }
    }

    private void validatePrice(Double price) {
        if (price.compareTo(new Double("0.00")) <= 0) {
            throw new IllegalArgumentException("Price of product not valid");
        }
    }
}
