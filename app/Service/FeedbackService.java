package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.FeedbackException;
import com.easytrip.app.Model.Feedback;

public interface FeedbackService {

	public Feedback addFeedback(Feedback feedback,Integer coustmerId) throws FeedbackException;
	
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException;
	
	public List<Feedback> findByCustomerId(Integer customerId) throws FeedbackException;
	
	public List<Feedback> viewAllFeedbacks() throws FeedbackException;

}
