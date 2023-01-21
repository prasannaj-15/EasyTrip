package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.Route;
@Repository
public interface RouteDao extends JpaRepository<Route,Integer>{

	
}
