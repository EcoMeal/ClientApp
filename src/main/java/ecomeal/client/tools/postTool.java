package ecomeal.client.tools;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import ecomeal.client.ui.MainUI;

public class postTool {
	
	public postTool(){
		
	}
	
	
	/**
	 * 
	 * @param url
	 * @param Oldparams
	 * @return
	 */
	public String postMessage(UrlWrapper url, String json, String token){	
		
		try {
				
			StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);

			HttpClient client = HttpClients.createDefault();
			HttpPost postMethod = new HttpPost(url.getUrl().toURI());
			if(!token.equals("")){				
				postMethod.setHeader("X-Auth-Token", token);
			}
			postMethod.setEntity(requestEntity);

			HttpResponse response = client.execute(postMethod);
	
	        InputStream ips  = response.getEntity().getContent();
	        BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
	        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
	        {
	        	System.out.println("Code error PostTool : " + response.getStatusLine().getStatusCode());
	        	return "PROBLEME";
	        }
	        StringBuilder sb = new StringBuilder();
	        String s;
	        while(true )
	        {
	            s = buf.readLine();
	            if(s==null || s.length()==0)
	                break;
	            sb.append(s);
	
	        }
	        buf.close();
	        ips.close();
	        System.out.println(sb.toString());
	        return sb.toString();
	        
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
        		
	}
	
	
	/**
	 * Parse the InputStream read received from server as a String
	 * 
	 * @param in : InputStream
	 * @return result as String
	 */
	public String inputStreamToString(InputStream in) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(in, writer);
			return writer.toString();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        return null; 
    }
	
	/**
	 * Parse the InputStream read received from server as a String
	 * 
	 * @param in
	 * @return
	 */
	public String inputStreamToString2(DataInputStream in) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "inputStreamToString2 : IOException";
		}
		
		System.out.println(result.toString());
		return result.toString();
    }
}
