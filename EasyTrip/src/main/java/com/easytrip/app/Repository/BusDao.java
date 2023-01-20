package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easytrip.app.Model.Bus;

public interface BusDao extends JpaRepository<Bus,Integer>{

}
