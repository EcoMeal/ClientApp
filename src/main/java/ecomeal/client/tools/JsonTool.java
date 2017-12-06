package ecomeal.client.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class JsonTool {

	private HttpURLConnection connection;
	private InputStream inputStream;
	
	public JsonTool() {
		
	}
	
	public JsonTool(HttpURLConnection connection, InputStream inputStream) {
		this.connection = connection;
		this.inputStream = inputStream;
	}
	
	public String readJson(UrlWrapper url) {
		return readJson(url, new HashMap<String,String>());
	}
	
	public String readJson(UrlWrapper url, Map<String,String> params) {
		try {
			if(!params.isEmpty()) {				
				String urlString = url.getPath() + "?";
				for(String key : params.keySet()){
					urlString = urlString + key + "=" + params.get(key) + "&";
				}
				urlString = urlString.substring(0, urlString.length() - 1);
				url = new UrlWrapper(urlString);
			}
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			inputStream = connection.getInputStream();
			System.out.println("\n\n\n" + inputStream.toString() + "\n\n\n");
			return inputStreamToString(inputStream);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	
}
