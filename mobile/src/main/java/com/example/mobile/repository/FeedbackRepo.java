package com.example.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mobile.model.FeedBack;


@Repository
public interface FeedbackRepo extends JpaRepository<FeedBack,Long> {

    @Query("SELECT f FROM FeedBack f")
    List<FeedBack> getFeedbacks();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO feed_back (comments,rating) VALUES (?1,?2)",nativeQuery = true)
    void insertFeedback(String comments,int rating);
}
