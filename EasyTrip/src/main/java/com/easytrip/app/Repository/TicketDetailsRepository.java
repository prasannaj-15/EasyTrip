package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.TicketDetails;

@Repository
public interface TicketDetailsRepository extends JpaRepository<TicketDetails, Integer> {
	
	@Query("select t.totalTicketCost from TicketDetails t where t.ticketId=?1")
	public Double getTotalTicketCost(Integer ticketId);
}
