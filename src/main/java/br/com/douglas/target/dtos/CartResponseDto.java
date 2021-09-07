package br.com.douglas.target.dtos;

import br.com.douglas.target.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartResponseDto {

    private List<Product> products = new ArrayList<>();
    private Double valueOfDiscount;
    private Double valueOfItens;
    private Double valueTotal;

    public CartResponseDto(List<Product> products, Double valueOfDiscounte, Double valueOfItens, Double valueTotal) {
        this.products = products;
        this.valueOfDiscount = valueOfDiscounte;
        this.valueOfItens = valueOfItens;
        this.valueTotal = valueTotal;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getValueOfDiscount() {
        return valueOfDiscount;
    }

    public void setValueOfDiscount(Double valueOfDiscount) {
        this.valueOfDiscount = valueOfDiscount;
    }

    public Double getValueOfItens() {
        return valueOfItens;
    }

    public void setValueOfItens(Double valueOfItens) {
        this.valueOfItens = valueOfItens;
    }

    public Double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(Double valueTotal) {
        this.valueTotal = valueTotal;
    }
}
