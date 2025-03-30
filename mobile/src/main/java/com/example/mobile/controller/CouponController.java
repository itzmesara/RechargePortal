package com.example.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mobile.model.Coupon;
import com.example.mobile.service.CouponService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @PostMapping
    public ResponseEntity<Object> createCoupon(@RequestBody Coupon coupon){
        couponService.addCoupon(coupon);
        return new ResponseEntity<>("Coupon Created Successfully",HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getCoupons()
    {
        return new ResponseEntity<>(couponService.getCoupons(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCoupon(@PathVariable long id,@RequestBody Coupon coupon){
        couponService.updateCoupon(id,coupon);
        return new ResponseEntity<>("Coupon Updated Successfully",HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCoupon(@PathVariable long id){
        couponService.removeCoupon(id);
        return new ResponseEntity<>("Coupon Deleted Successfully", HttpStatus.OK);
    }

}
