package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easytrip.app.Model.Travels;

public interface TravelsDao extends JpaRepository<Travels,Integer>{

}
