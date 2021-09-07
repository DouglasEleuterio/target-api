package br.com.douglas.target.services;

import br.com.douglas.target.dtos.CartRequestDto;
import br.com.douglas.target.dtos.ItemDto;
import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.models.Coupon;
import br.com.douglas.target.models.Product;
import br.com.douglas.target.models.PurchaseOrder;
import br.com.douglas.target.repository.PurchaseOrderRepository;
import br.com.douglas.target.services.Exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public final class CartService {

    @Autowired
    private CouponService couponService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public MessageDto calculate(CartRequestDto cart) {

        List<Coupon> couponList = new ArrayList<>();
        Coupon coupon = null;
        if (cart.getCoupons().size() > 0) {
            cart.getCoupons().forEach(couponId -> couponList.add(couponService.getCouponById(couponId.getId().toString())));
            coupon = couponList.get(0);
            for (int i = 1; i < couponList.size() ; i++) {
                if(couponList.get(i).getDiscount() > coupon.getDiscount()){
                    coupon = couponList.get(i);
                }
            }
        }



        Double totalOfProductsBeforeDiscount = 0.00;
        Double totalAfterDiscount = 0.00;
        Double discountOfCouponValue = 0.00;
        Double totalForPayment = 0.00;

        for (ItemDto item : cart.getItens()) {
            //Total before Discount
            try {
                Product product = (Product) productService.findById(item.getProduct().getId().toString()).getObj();
                totalOfProductsBeforeDiscount += product.getPrice() * item.getQuantity();
                totalAfterDiscount += calculateTotalProduct(product, item.getQuantity());
            } catch (ProductNotFoundException | ClassNotFoundException e) {
                throw new ProductNotFoundException(e.getMessage());
            }
        }

        totalAfterDiscount = setPrecisionOfValues(totalAfterDiscount);
        totalOfProductsBeforeDiscount = setPrecisionOfValues(totalOfProductsBeforeDiscount);

        if (coupon != null) {
            discountOfCouponValue = calculateDiscountOfCoupon(totalAfterDiscount, coupon);
        }

        totalForPayment = calculateTotalForPayment(totalAfterDiscount, discountOfCouponValue);

        totalForPayment = calculeProgressiveDiscount(totalForPayment);

        totalForPayment = setPrecisionOfValues(totalForPayment);

        PurchaseOrder order = new PurchaseOrder(new Date(), (coupon != null ? coupon.getId() : null), totalOfProductsBeforeDiscount - totalForPayment, totalOfProductsBeforeDiscount, totalForPayment);

        PurchaseOrder saved = purchaseOrderRepository.save(order);

        return new MessageDto("Total for Payment: R$ " + totalForPayment + ", Value without discount R$ " + totalOfProductsBeforeDiscount + " ", new Date(), saved);
    }

    private Double setPrecisionOfValues(Double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    protected Double calculateDiscountOfCoupon(Double total, Coupon coupon) {
        return new BigDecimal(total * (coupon.getDiscount() / 100)).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    protected Double calculateTotalForPayment(Double productsValue, Double discountValue) {
        return new BigDecimal(productsValue - discountValue).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    protected Double calculateTotalProduct(Product product, Integer quantity) {
        Double totalOfProducts = 0.00;
        if (quantity < 10) {
            totalOfProducts = product.getPrice() * quantity;
        }
        if (quantity >= 10) {
            totalOfProducts = product.getPrice() * quantity;
            totalOfProducts = (totalOfProducts - (totalOfProducts * 0.10));
        }

        return new BigDecimal(totalOfProducts).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    protected Double calculeProgressiveDiscount(Double totalOfProducts) {
        // Entre R$ 1.000,00 e R$ 5.000,00 = 5% Desconto
        if (totalOfProducts.compareTo(new Double("1000.00")) > 0 && totalOfProducts.compareTo(new Double("5000.00")) < 0) {
            return (totalOfProducts - (totalOfProducts * 0.05));
        }
        // Entre R$ 5.000,00 e R$ 7.000,00 = 7% Desconto
        if (totalOfProducts.compareTo(new Double("5000.00")) > 0 && totalOfProducts.compareTo(new Double("10000.00")) < 0) {
            return (totalOfProducts - (totalOfProducts * 0.07));
        }
        // Acima de R$ 10.000,00 = 10% Desconto
        if (totalOfProducts.compareTo(new Double("10000.00")) > 0) {
            return (totalOfProducts - (totalOfProducts * 0.10));
        }
        //Abaixo de R$ 1.000,00 = 0% Desconto
        return totalOfProducts;
    }
}
