package br.com.douglas.target.dtos;

import br.com.douglas.target.models.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CartRequestDto {

    private List<ItemDto> itens = new ArrayList<>();
    private List<Coupon> coupons = new ArrayList<>();

    public CartRequestDto() {
    }

    public CartRequestDto(List<ItemDto> itens, List<Coupon> coupons) {
        this.itens = itens;
        this.coupons = coupons;
    }

    public List<ItemDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemDto> itens) {
        this.itens = itens;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
