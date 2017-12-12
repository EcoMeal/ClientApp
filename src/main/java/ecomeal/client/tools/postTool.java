package ecomeal.client.tools;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
	public String postMessage(UrlWrapper url, String json){	
		
		/*URL serverAddress = url.getUrl();
        HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) serverAddress.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
			return "postMessage : IOException";
		}
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("X-HTTP-Method-Override", "POST");
        try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
			return "postMessage : ProtocolException";
		}

        try {
        	OutputStream os = connection.getOutputStream();
        	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        	
			writer.write(json);
			writer.flush();
	        writer.close();
	        os.close();
	        connection.connect();
	        
	        InputStream _is;
	        if (connection.getResponseCode() / 100 == 2) { // 2xx code means success
	            _is = connection.getInputStream();  
	        } else {  

	            _is = connection.getErrorStream();  

	            String result = inputStreamToString(_is);
	            System.out.println("Error != 2xx" + result);
	        }
	        
	        
	        String json_response = "";
	        InputStreamReader in = new InputStreamReader(_is);
	        BufferedReader br = new BufferedReader(in);
	        String text = "";
	        while ((text = br.readLine()) != null) {
	          json_response += text;
	        }
		    System.out.println("Response : " + json_response);
	        return json_response;
		} catch (IOException e) {
			e.printStackTrace();
			return "postMessage : IOException";
		}*/
		
		
		try {
			
			
			StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);

			HttpClient client = HttpClients.createDefault();
			HttpPost postMethod = new HttpPost(url.getUrl().toURI());
			postMethod.setEntity(requestEntity);

			HttpResponse response = client.execute(postMethod);
			
	        /*HttpPost request = new HttpPost();
	        request.setURI(url.getUrl().toURI());
	        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	        request.setEntity(new StringEntity(json));
	        HttpResponse response = client.execute(request);*/
	
	        InputStream ips  = response.getEntity().getContent();
	        BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
	        if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
	        {
	            throw new Exception(response.getStatusLine().getReasonPhrase());
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
	        
		} catch(Exception e) {
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
