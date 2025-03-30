package com.example.mobile.service;

import com.example.mobile.model.RechargePlan;
import com.example.mobile.model.User;
import com.example.mobile.repository.RechargePlanRepo;
import com.example.mobile.repository.UserRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RechargePlanService {

    private final RechargePlanRepo planRepo;
    private final UserRepo userRepo; // Add UserRepo to manage User entities

    public RechargePlanService(RechargePlanRepo planRepo, UserRepo userRepo) {
        this.planRepo = planRepo;
        this.userRepo = userRepo;
    }

    // Add a new recharge plan
    public void addPlan(RechargePlan plan) {
        planRepo.save(plan); // Use JPA's save method instead of custom query
    }

    // Get all recharge plans
    public List<RechargePlan> getPlans() {
        return planRepo.findAll(); // Use JPA's findAll method
    }

    // Get a recharge plan by ID
    public Optional<RechargePlan> getPlan(long id) {
        return planRepo.findById(id); // Use JPA's findById method
    }

    // Update a recharge plan
    public RechargePlan updatePlan(long id, RechargePlan plan) {
        Optional<RechargePlan> planExists = planRepo.findById(id);
        if (planExists.isPresent()) {
            RechargePlan newPlan = planExists.get();
            if (plan.getPlanName() != null) {
                newPlan.setPlanName(plan.getPlanName());
            }
            if (plan.getPlanDescription() != null) {
                newPlan.setPlanDescription(plan.getPlanDescription());
            }
            if (plan.getPlanDuration() != null) {
                newPlan.setPlanDuration(plan.getPlanDuration());
            }
            if (plan.getPrice() != 0) {
                newPlan.setPrice(plan.getPrice());
            }

            return planRepo.save(newPlan); // Save the updated plan
        } else {
            return null;
        }
    }

    // Delete a recharge plan
    public void deletePlan(long id) {
        Optional<RechargePlan> plan = planRepo.findById(id);
        if (plan.isPresent()) {
            // Remove the plan from all users before deleting
            Set<User> users = plan.get().getUsers();
            for (User user : users) {
                user.getRechargePlans().remove(plan.get()); // Remove the plan from each user
            }
            userRepo.saveAll(users); // Save the updated users
            planRepo.deleteById(id); // Delete the plan
        }
    }

    // Get recharge plans with pagination
    public Page<RechargePlan> getPages(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return planRepo.findAll(pageable);
    }

    // Add a recharge plan to a user
    public void addPlanToUser(long userId, long planId) {
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<RechargePlan> planOptional = planRepo.findById(planId);

        if (userOptional.isPresent() && planOptional.isPresent()) {
            User user = userOptional.get();
            RechargePlan plan = planOptional.get();

            user.getRechargePlans().add(plan); // Add the plan to the user
            plan.getUsers().add(user); // Add the user to the plan

            userRepo.save(user); // Save the updated user
            planRepo.save(plan); // Save the updated plan
        }
    }

    // Remove a recharge plan from a user
    public void removePlanFromUser(long userId, long planId) {
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<RechargePlan> planOptional = planRepo.findById(planId);

        if (userOptional.isPresent() && planOptional.isPresent()) {
            User user = userOptional.get();
            RechargePlan plan = planOptional.get();

            user.getRechargePlans().remove(plan); // Remove the plan from the user
            plan.getUsers().remove(user); // Remove the user from the plan

            userRepo.save(user); // Save the updated user
            planRepo.save(plan); // Save the updated plan
        }
    }
}