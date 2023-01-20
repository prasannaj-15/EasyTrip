package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.CurrentUserSession;

@Repository
public interface SessionRepository extends JpaRepository<CurrentUserSession, Integer>{

	public CurrentUserSession findByUuid(String uuid);
	
}
