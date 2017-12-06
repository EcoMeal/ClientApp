package ecomeal.client.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

/**
 * Class used to parse data from server as JSON format
 */
public class JsonTool {

	private HttpURLConnection connection;
	private InputStream inputStream;
	
	public JsonTool() {
		
	}
	
	public JsonTool(HttpURLConnection connection, InputStream inputStream) {
		this.connection = connection;
		this.inputStream = inputStream;
	}
	
	/**
	 * Make connection with server and then parse the result as String
	 * 
	 * @param url the url from the server api
	 * @return JSON as String format
	 */
	public String readJson(UrlWrapper url) {
		return readJson(url, new HashMap<String,String>());
	}
	
	/**
	 * Make connection with server and then parse the result as String
	 * 
	 * @param url the url from the server api
	 * @return JSON as String format
	 */
	public String readJson(UrlWrapper url, Map<String,String> params) {
		try {
			// Adapt URL if there is some parameters
			if(!params.isEmpty()) {				
				String urlString = url.getPath() + "?";
				for(String key : params.keySet()){
					urlString = urlString + key + "=" + params.get(key) + "&";
				}
				urlString = urlString.substring(0, urlString.length() - 1);
				url.setUrl(urlString);
			}
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			inputStream = connection.getInputStream();
			System.out.println("\n\n\n" + inputStream.toString() + "\n\n\n");
			return inputStreamToString(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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
	
}
