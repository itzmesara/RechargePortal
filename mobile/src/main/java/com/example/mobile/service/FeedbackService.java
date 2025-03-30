package com.example.mobile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobile.model.FeedBack;
import com.example.mobile.repository.FeedbackRepo;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepo feedbackRepo;

    public void saveFeedback(FeedBack feedback) {
        feedbackRepo.insertFeedback(feedback.getComments(), feedback.getRating());
    }

    public List<FeedBack> getFeedbackList() {
        return feedbackRepo.getFeedbacks();
    }

    public FeedBack updateFeedBack(long id, FeedBack feedback) {
        Optional<FeedBack> feedbackExist = feedbackRepo.findById(id);
        if (feedbackExist.isPresent()) {
            FeedBack updatedFeedback = feedbackExist.get();
            if (feedback.getComments() != null) {
                updatedFeedback.setComments(feedback.getComments());

            }
            if (feedback.getRating() != -1) {

                updatedFeedback.setRating(feedback.getRating());
            }
            return feedbackRepo.save(updatedFeedback);
        } else {
            return null;
        }
    }

    public void deleteFeedback(long id) {
        feedbackRepo.deleteById(id);
    }
}
