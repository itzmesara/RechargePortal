package com.example.mobile.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mobile.model.RechargePlan;

@Repository
public interface RechargePlanRepo extends JpaRepository<RechargePlan, Long> {

    @Query("SELECT a FROM RechargePlan a")
    List<RechargePlan> findPlans();

    @Query("SELECT a FROM RechargePlan a WHERE a.id= :id ")
    Optional<RechargePlan> findPlanById(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Recharge_Plan ( plan_name,plan_description,plan_duration,price) VALUES (:plan_name,:plan_description,:plan_duration,:price)", nativeQuery = true)
    void insertPlan(@Param("plan_name") String planName, @Param("plan_description") String planDescription,
            @Param("plan_duration") String planDuration, @Param("price") double price);
}