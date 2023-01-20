package com.easytrip.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.TripPackage;




@Repository
public interface TripPackageDao extends JpaRepository<TripPackage, Integer> {
	
	public TripPackage findByPackageName(String packageName);
}
