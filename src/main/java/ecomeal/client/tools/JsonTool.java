package ecomeal.client.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTool {

	public String readJson(String JsonURL) {
		URL url;
		try {
			url = new URL(JsonURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			return inputStreamToString(inputStream, 8096);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String inputStreamToString (InputStream in, int bufSize) {         
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
