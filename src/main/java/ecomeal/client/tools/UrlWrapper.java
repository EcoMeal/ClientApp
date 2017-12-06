package ecomeal.client.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlWrapper {

    URL url;

    public UrlWrapper(String spec) throws MalformedURLException{
        url = new URL(spec);
    }

    public URLConnection openConnection() throws IOException{
        return url.openConnection();
    }

	public String getPath() {
		return url.getPath();
	}

}
