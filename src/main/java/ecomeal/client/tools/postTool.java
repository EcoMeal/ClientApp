package ecomeal.client.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

public class postTool {
	
	public postTool(){
		
	}
	
	/**
	 * 
	 * @param url
	 * @param Oldparams
	 * @return
	 */
	public String postMessage(UrlWrapper url, List<NameValuePair> params){
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost;
		try {
			httppost = new HttpPost(url.getUrl().toURI());
			httppost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			return "URISyntaxException";
		}

		// Request parameters and other properties.
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "UnsupportedEncodingException";
		}

		//Execute and get the response.
		HttpResponse response;
		try {
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
			    InputStream instream = entity.getContent();
			    try {
			    	return inputStreamToString2(instream);
			    } finally {
			        instream.close();
			    }
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "ClientProtocolException";
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		}
		
		return "Bah, c'est la fin ...";
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
	public String inputStreamToString2(InputStream in) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		}
		
		System.out.println(result.toString());
		return result.toString();
    }
}
