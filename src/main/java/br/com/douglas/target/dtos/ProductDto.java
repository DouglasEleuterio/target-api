package br.com.douglas.target.dtos;

import br.com.douglas.target.models.Category;
import br.com.douglas.target.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {

    private String name;
    private Double price;
    private List<Category> categories = new ArrayList<>();

    public ProductDto() {
    }

    public ProductDto(String name, Double price, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public static Product toProduct(ProductDto dto){
        return new Product(dto.getName(), dto.getPrice(), dto.categories);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
