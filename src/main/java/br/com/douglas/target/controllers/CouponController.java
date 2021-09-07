package br.com.douglas.target.controllers;

import br.com.douglas.target.dtos.CouponRequestDto;
import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.models.Coupon;
import br.com.douglas.target.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    CouponService service;

    @GetMapping
    public ResponseEntity<MessageDto> getAllCoupons() {
        MessageDto messageDto = service.getAll();
        return ResponseEntity.ok(messageDto);
    }

    @PostMapping()
    public ResponseEntity<MessageDto> saveCoupon(@RequestBody CouponRequestDto coupon) {
        MessageDto savedCoupon = service.save(coupon);
        return ResponseEntity.ok(savedCoupon);
    }

}
