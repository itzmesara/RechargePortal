package com.example.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobile.model.Coupon;
import com.example.mobile.repository.CouponRepo;

@Service
public class CouponService {
    @Autowired
    CouponRepo couponRepo;

    public void addCoupon(Coupon coupon){
        couponRepo.saveCoupon(coupon.getCode(),coupon.getDiscount());
    }

    public List<Coupon> getCoupons(){
        return couponRepo.getAllCoupons();
    }

    public Coupon updateCoupon(long id,Coupon coupon){
        Coupon couponToUpdate = couponRepo.findById(id).orElse(null);
        if(couponToUpdate!= null){
            if(coupon.getCode()!=null)
            {

                couponToUpdate.setCode(coupon.getCode());
            }
            if(coupon.getDiscount()!=0)
            {

                couponToUpdate.setDiscount(coupon.getDiscount());
            }
            couponRepo.save(couponToUpdate);
        }
        return couponToUpdate;
    }

    public void removeCoupon(long id){
        couponRepo.deleteById(id);
    }
}
