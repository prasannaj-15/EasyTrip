package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.Feedback;

@Repository
public interface FeedBackDAO extends JpaRepository<Feedback, Integer>{

	
}
