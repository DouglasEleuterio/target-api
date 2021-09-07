package br.com.douglas.target.models;

public class ProductCategory {

    private Category id_category;

    private Product id_product;

    public ProductCategory(Category id_category, Product id_product) {
        this.id_category = id_category;
        this.id_product = id_product;
    }

    public Category getId_category() {
        return id_category;
    }

    public void setId_category(Category id_category) {
        this.id_category = id_category;
    }

    public Product getId_product() {
        return id_product;
    }

    public void setId_product(Product id_product) {
        this.id_product = id_product;
    }
}
