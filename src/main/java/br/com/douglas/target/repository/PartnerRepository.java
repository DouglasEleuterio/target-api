package br.com.douglas.target.repository;

import br.com.douglas.target.models.Coupon;
import br.com.douglas.target.models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    Optional<Partner> findPartnersByCoupons(Coupon coupon);
}
