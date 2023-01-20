package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.FeedbackException;
import com.easytrip.app.Model.Feedback;
import com.easytrip.app.Repository.FeedBackDAO;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedBackDAO feedbackDao;
	
	@Autowired
	private CustomerDAO customerDao;
	
	/*
	 * Add and autowired  all the cases according to use like .
	 * 
	  private UserDAO userDao;
	  private CustomerSessionDao customerSessionDao
	*/
	

	@Override
	public Feedback addFeedback(Feedback feedback, Integer coustmerId) throws FeedbackException {
		// TODO Auto-generated method stub
		
		// this is hardcoded..first check for use session
		// create customer--> find customer--> then save his feedback
		/////////////////////////
		
//		Customer constumer= customerDao.findbyid(currentSession.getuserID().get())
//		
//		feedback.setCustomer(customer);
//		
//		return feedbackDao.save(feedback);
		
		//////////////////////

		
	}

	@Override
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException {
		// TODO Auto-generated method stub
		
		Optional<Feedback> feedbackoptional = feedbackDao.findById(feedbackId);
		
		if(!feedbackoptional.isPresent())
		{
			throw new FeedbackException("No Feedback is present with the given Feedback Id:- "+feedbackId);
		}
		
		return feedbackoptional.get();

	}

	@Override
	public List<Feedback> findByCustomerId(Integer customerId) throws FeedbackException {
		// TODO Auto-generated method stub
		
		// this is hardcoded..first check for use session
		// create customer--> find customer--> then save his feedback
		/////////////////////////
		
//		Customer constumer= customerDao.findbyid(currentSession.getuserID().get())
		
		Optional<Customer> customerOptional= customerDao.findById(customerId);
		
		if(!customerOptional.isPresent())
		{
			throw new FeedbackException("No user present with "+customerId);
		}
		
		Customer cufb = customerOptional.get();
		
		List<Feedback> list= cufb.getFeedbacks();
			if(list.size()==0)
			{
				throw new FeedbackException("No feedback from this user");
			}
		
		return list;
	}

	@Override
	public List<Feedback> viewAllFeedbacks() throws FeedbackException {
		// TODO Auto-generated method stub
		
		List<Feedback> ansList=feedbackDao.findAll();
		
		return ansList;

	}
	
	
	

}
