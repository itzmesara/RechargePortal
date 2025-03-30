package com.example.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mobile.model.Coupon;


@Repository
public interface CouponRepo extends JpaRepository<Coupon,Long> {
    
    @Query("SELECT c FROM Coupon c")
    List<Coupon> getAllCoupons();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Coupon (code,discount) VALUES (?1,?2)",nativeQuery = true)
    void saveCoupon(String code, double discount);
}
