package br.com.douglas.target.services;

import br.com.douglas.target.dtos.CouponRequestDto;
import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.models.Category;
import br.com.douglas.target.models.Coupon;
import br.com.douglas.target.models.Partner;
import br.com.douglas.target.repository.CouponRepository;
import br.com.douglas.target.repository.PartnerRepository;
import br.com.douglas.target.security.jwt.AuthEntryPointJwt;
import br.com.douglas.target.services.Exceptions.CoupomNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static br.com.douglas.target.services.Utils.validateId;

@Service
public class CouponService {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private PartnerService partnerService;

    public void insertCoupon(Coupon coupon) {
        validateId(coupon.toString());
        couponRepository.save(coupon);
    }

    public MessageDto getAll() {
        return new MessageDto("List of coupons", new Date(), couponRepository.findAll());
    }

    public MessageDto save(CouponRequestDto coupon) {
        try {
            Partner partner = partnerService.findById(coupon.getPartner_id());
            Coupon cp = new Coupon(coupon.getName(), coupon.getDiscount(), partner);
            Coupon toSave = couponRepository.save(cp);
            return new MessageDto("Coupon saved: ", Date.from(Instant.now()), toSave);
        } catch (Exception e) {
            logger.error("Coupon not Saved: {}", e.getMessage());
            return null;
        }
    }

    public Coupon getCouponById(String coupon_id) {
        try{
            Coupon coupon = couponRepository.findCouponById(Integer.parseInt(coupon_id)).orElseThrow(CoupomNotFoundException::new);
            return coupon;
        }catch (CoupomNotFoundException e){
            logger.error("Coupon id {} not found", coupon_id);
            throw new CoupomNotFoundException("Coupon id: " + coupon_id + ", not found");
        }
    }
}
