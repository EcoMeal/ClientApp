package ecomeal.client.services;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public abstract class AbstractService {
	
	protected Client client;
	protected WebTarget target;
	
	@PostConstruct
	public abstract void init();

}
