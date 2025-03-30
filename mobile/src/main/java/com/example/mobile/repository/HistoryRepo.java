package com.example.mobile.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mobile.model.History;

@Repository
public interface HistoryRepo extends JpaRepository<History, Long> {

    @Query("SELECT a FROM History a")
    List<History> findHistories();

    @Query("SELECT a FROM History a WHERE a.id= :id ")
    Optional<History> findHistoryById(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO History (recharge_plan,date,time) VALUES (:recharge_plan,:date,:time)", nativeQuery = true)
    void insertHistory(@Param("recharge_plan") String rechargePlan, @Param("date") String date,
            @Param("time") String time);
}

