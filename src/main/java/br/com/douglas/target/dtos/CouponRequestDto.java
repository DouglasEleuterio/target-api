package br.com.douglas.target.dtos;

public class CouponRequestDto {
    private String name;
    private Double discount;
    private String partner_id;

    public CouponRequestDto() {
    }

    public CouponRequestDto(String name, Double discount, String partner_id) {
        this.name = name;
        this.discount = discount;
        this.partner_id = partner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }
}
