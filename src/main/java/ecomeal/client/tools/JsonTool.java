package ecomeal.client.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class JsonTool {

	private HttpURLConnection connection;
	
	public JsonTool(HttpURLConnection connection) {
		this.connection = connection;
	}
	
	public String readJson(UrlWrapper url) {
		return readJson(url, new HashMap<String,String>());
	}
	
	public String readJson(UrlWrapper url, Map<String,String> params) {
		try {
			String urlString = url.getPath() + "?";
			for(String key : params.keySet()){
				urlString = urlString + key + "=" + params.get(key) + "&";
			}
			urlString = urlString.substring(0, urlString.length() - 1);
			url = new UrlWrapper(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			System.out.println("\n\n\n" + inputStream.toString() + "\n\n\n");
			return inputStreamToString(inputStream, 8096);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String inputStreamToString (InputStream in, int bufSize) {         
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
