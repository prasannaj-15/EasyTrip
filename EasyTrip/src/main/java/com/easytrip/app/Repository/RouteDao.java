package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easytrip.app.Model.Route;

public interface RouteDao extends JpaRepository<Route,Integer>{

}
