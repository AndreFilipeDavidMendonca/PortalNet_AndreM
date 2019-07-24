package com.polarising.PortalNet.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polarising.PortalNet.model.Services;

@Repository
public interface ServiceRepository extends CrudRepository<Services, Long>{
	
	List<Services> findByName(String name);
	
	List<Services> findByServiceID(Long serviceID);
	
	boolean existsByName(String name);
}
