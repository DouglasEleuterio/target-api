package br.com.douglas.target.services;


import br.com.douglas.target.models.Category;
import br.com.douglas.target.models.Coupon;
import br.com.douglas.target.models.Partner;
import br.com.douglas.target.models.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartServiceTest {

    CartService service = new CartService();

    @Test
    public void calculeTotalProductValueWith1Product(){
        Product product = buildProductsForTest().get(0);
        Integer quantity = 1;
        Double total = service.calculateTotalProduct(product, quantity);
        System.out.println(total);
        Assert.assertTrue(total.compareTo (new Double("98.99")) == 0);
    }

    @Test
    public void calculeTotalProductValueWith10ProductsAndLessThen1000(){
        Product product = buildProductsForTest().get(0);
        Integer quantity = 10;
        Double total = service.calculateTotalProduct(product, quantity);
        System.out.println(total);
        Assert.assertTrue(total.compareTo (new Double("890.91")) == 0);
    }

    @Test
    public void calculeTotalProductValueWithPriceOfProductGraterThe1000AndLessThen5000(){
        Product product = buildProductsForTest().get(0);
        Integer quantity = 15;
        Double total = service.calculateTotalProduct(product, quantity);
        System.out.println(total);
        Assert.assertTrue(total.compareTo (new Double("1336.37")) == 0);
    }

    @Test
    public void calculeTotalProductValueWithPriceOfProductGraterThe5000AndLessThen10000(){
        Product product = buildProductsForTest().get(0);
        Integer quantity = 102;
        Double total = service.calculateTotalProduct(product, quantity);
        System.out.println(total);
        Assert.assertTrue(total.compareTo (new Double("9087.28")) == 0);
    }

    @Test
    public void calculeTotalProductValueWithPriceOfProductGraterTheN10000(){
        Product product = buildProductsForTest().get(0);
        Integer quantity = 150;
        Double total = service.calculateTotalProduct(product, quantity);
        System.out.println(total);
        Assert.assertTrue(total.compareTo (new Double("13363.65")) == 0);
    }


    @Test
    public void calculateTotalForPayment(){
        Double total = service.calculateTotalForPayment(new Double("1000.00"), new Double("100.00"));
        System.out.println(total);
        Assert.assertTrue(total.compareTo(new Double("900.00")) == 0);
    }

    private List<Product> buildProductsForTest(){
        List<Product> productList = new ArrayList<>();

        Product prod1 = new Product("Camisa Social Lisa", 98.99, Arrays.asList(new Category("Camisas")));
        Product prod2 = new Product("Calça Jeans 44", 198.99, Arrays.asList(new Category("Calças"), new Category("Calça Jeans")));
        Product prod3 = new Product("Sapato Social 41", 161.67, Arrays.asList(new Category("Sapatos")));

        productList.add(prod1);
        productList.add(prod2);
        productList.add(prod3);

        return productList;
    }

    private Coupon buildCoupom(){
        return new Coupon("Coupom from test", 15.00, new Partner("Partner of Test"));
    }
}
