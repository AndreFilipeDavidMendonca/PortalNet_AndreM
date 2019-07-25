package com.polarising.PortalNet.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polarising.PortalNet.model.Workers;

@Repository
public interface WorkersRepository extends CrudRepository<Workers, Integer>{

	List<Workers> findByName(String name);
	List<Workers> findByEmail(String email);
	boolean existsByEmail(String email);
}
