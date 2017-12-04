package ecomeal.client.services;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public abstract class AbstractService {
	
	protected Client client;
	protected WebTarget target;
	
	@PostConstruct
	public abstract void init();
	
	public static String inputStreamToString (InputStream in, int bufSize) {         
        final StringBuilder out = new StringBuilder(); 
        final byte[] buffer = new byte[bufSize]; 
        try {
            for (int ctr; (ctr = in.read(buffer)) != -1;) {
                out.append(new String(buffer, 0, ctr));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot convert stream to string", e);
        }
        return out.toString(); 
    }

}
