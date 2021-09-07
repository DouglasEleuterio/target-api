package br.com.douglas.target.dtos;

import br.com.douglas.target.models.Product;

public class ItemDto {

    private Product product;
    private Integer quantity;

    public ItemDto() {
    }

    public ItemDto(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
