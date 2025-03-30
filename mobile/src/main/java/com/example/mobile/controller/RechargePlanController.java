package com.example.mobile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mobile.model.RechargePlan;
import com.example.mobile.service.RechargePlanService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/RechargePlan")
public class RechargePlanController {

    private final RechargePlanService planService;

    public RechargePlanController(RechargePlanService planService) {
        this.planService = planService;
    }

    // 201 Created – When the recharge plan is created successfully.
    @PostMapping
    public ResponseEntity<Object> addPlan(@RequestBody RechargePlan plan) {
        planService.addPlan(plan);
        return new ResponseEntity<>("Recharge Plan is created successfully", HttpStatus.CREATED);
    }

    // 200 OK – When the recharge plans are retrieved successfully.
    @GetMapping
    public ResponseEntity<List<RechargePlan>> getPlans() {
        List<RechargePlan> plans = planService.getPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    // 200 OK – When the recharge plans are retrieved with pagination.
    @GetMapping("/paginate")
    public ResponseEntity<Page<RechargePlan>> paging(@RequestParam int page, @RequestParam int size) {
        Page<RechargePlan> plans = planService.getPages(page, size);
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    // 200 OK – When the recharge plan is found.
    // 404 Not Found – When the recharge plan is not found.
    @GetMapping("/{id}")
    public ResponseEntity<RechargePlan> getPlan(@PathVariable long id) {
        Optional<RechargePlan> plan = planService.getPlan(id);
        return plan.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 200 OK – When the recharge plan is updated successfully.
    // 404 Not Found – When the recharge plan is not found.
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlan(@PathVariable long id, @RequestBody RechargePlan plan) {
        RechargePlan updatedPlan = planService.updatePlan(id, plan);
        if (updatedPlan != null) {
            return new ResponseEntity<>("Recharge Plan is updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Recharge Plan not found", HttpStatus.NOT_FOUND);
        }
    }

    // 204 No Content – When the recharge plan is deleted successfully.
    // 404 Not Found – When the recharge plan is not found.
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlan(@PathVariable long id) {
        try {
            planService.deletePlan(id);
            return new ResponseEntity<>("Recharge Plan is deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Add a recharge plan to a user
    @PostMapping("/{userId}/addPlan/{planId}")
    public ResponseEntity<Object> addPlanToUser(@PathVariable long userId, @PathVariable long planId) {
        try {
            planService.addPlanToUser(userId, planId);
            return new ResponseEntity<>("Recharge Plan added to user successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Remove a recharge plan from a user
    @DeleteMapping("/{userId}/removePlan/{planId}")
    public ResponseEntity<Object> removePlanFromUser(@PathVariable long userId, @PathVariable long planId) {
        try {
            planService.removePlanFromUser(userId, planId);
            return new ResponseEntity<>("Recharge Plan removed from user successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}