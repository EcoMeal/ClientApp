package ecomeal.client.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTool {

	private HttpURLConnection connection;
	
	public JsonTool(HttpURLConnection connection) {
		this.connection = connection;
	}
	
	public String readJson(URL url) {
		try {;
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
