package ecomeal.client.tools;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class JsonToolTest {

	/*private JsonTool jsonTool;
	private JsonTool jsonToolSpy;
	private String jsonExample = "[\n" + "\t{\n" + "\t\t\"name\":\"ee\",\n" + "\t\t\"price\":789,\n" + "\t\t\"category\":\"Panier vegan\",\n" + "\t\t\"category_image\":null,\n" + "\t\t\"products\":[\n" + "\t\t\t{\n" + "\t\t\t\t\"name\":\"Bob\",\n" + "\t\t\t\t\"category\":\"Humain\"\n" + "\t\t\t}\n" + "\t\t]\n" + "\t}\n" + "]";
	private HttpURLConnection connection;
	private UrlWrapper url;
	private InputStream inputStream;
	
	@Before
	public void init() throws IOException {
		connection = Mockito.mock(HttpURLConnection.class);
		url = Mockito.mock(UrlWrapper.class);
		Mockito.when(url.openConnection()).thenReturn(connection);
		inputStream = Mockito.mock(InputStream.class);
		Mockito.when(connection.getInputStream()).thenReturn(inputStream);
		jsonTool = new JsonTool(connection);
		jsonToolSpy = Mockito.spy(jsonTool);
		Mockito.when(jsonToolSpy.inputStreamToString(inputStream, 8192)).thenReturn(jsonExample);
	}
	
	@Test
	public void testReadJson() throws IOException {
		jsonTool.readJson(url);
		Mockito.verify(url).openConnection();
		Mockito.verify(connection).connect();
		Mockito.verify(connection).getInputStream();
		Mockito.verify(jsonToolSpy).inputStreamToString(inputStream, 8192);
		//assertEquals(jsonExample, jsonTool.readJson(url));
	}*/

}
