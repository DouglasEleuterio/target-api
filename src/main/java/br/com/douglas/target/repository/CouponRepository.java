package br.com.douglas.target.repository;

import br.com.douglas.target.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository  extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findCouponById (Integer id);
}
