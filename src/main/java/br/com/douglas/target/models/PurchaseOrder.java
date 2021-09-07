package br.com.douglas.target.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;
    @Column(name = "coupon_id")
    private Integer couponId;

    private Double discount;

    private Double total;
    @Column(name = "total_payment")
    private Double totalPayment;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Date date, Integer couponId, Double discount, Double total, Double totalPayment) {
        this.date = date;
        this.couponId = couponId;
        this.discount = discount;
        this.total = total;
        this.totalPayment = totalPayment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
