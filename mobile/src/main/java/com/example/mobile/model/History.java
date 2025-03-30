package com.example.mobile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    // long userId;
    String rechargePlan;
    String date;
    String time;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    @JsonBackReference
    User user;
    public History() {
    }

    public History(long id, String rechargePlan, String date, String time,User user) {
        this.id = id;
        // this.userId = userId;
        this.rechargePlan = rechargePlan;
        this.date = date;
        this.time = time;
        this.user=user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // public long getUserId() {
    // return userId;
    // }

    // public void setUserId(long userId) {
    // this.userId = userId;
    // }

    public String getRechargePlan() {
        return rechargePlan;
    }

    public void setRechargePlan(String rechargePlan) {
        this.rechargePlan = rechargePlan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}