package com.easytrip.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Exception.FeedbackException;
import com.easytrip.app.Model.Feedback;
import com.easytrip.app.Service.FeedbackService;

@RestController
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/Feedback_add/{key}")
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback,@PathVariable("customerId")Integer customerId)throws FeedbackException
	{
		Feedback fbr=feedbackService.addFeedback(feedback, customerId);
		
		return new ResponseEntity<Feedback>(fbr,HttpStatus.CREATED);
	}

	@GetMapping("/Feedback_find/{feedbackId}")
	public ResponseEntity<Feedback> findByFeedbackId(@PathVariable("feedbackId") Integer feedbackId)throws FeedbackException
	{
		Feedback fbr=feedbackService.findByFeedbackId(feedbackId);
		
		return new ResponseEntity<Feedback>(fbr,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/Feedback_find/{customerId}")
	public ResponseEntity<List<Feedback>> findByCustomerId(@PathVariable("userId") Integer userId) throws FeedbackException
	{
		List<Feedback> fbr=feedbackService.findByCustomerId(userId);
		
		return new ResponseEntity<List<Feedback>>(fbr,HttpStatus.CREATED);
	}
	
	@GetMapping("/Feedback/allFeedbacks/")
	public ResponseEntity<List<Feedback>> viewAllFeedbacks() throws FeedbackException
	{
		List<Feedback> list=feedbackService.viewAllFeedbacks();
		
		return new ResponseEntity<List<Feedback>>(list,HttpStatus.CREATED);
	}


}
