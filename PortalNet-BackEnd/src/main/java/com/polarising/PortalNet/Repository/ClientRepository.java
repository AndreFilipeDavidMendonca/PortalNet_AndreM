package com.polarising.PortalNet.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polarising.PortalNet.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>{
	
	List<Client> findByClientId(int clientId);
	List<Client> findByEmail(String email);
	boolean existsByEmail(String email);
}
