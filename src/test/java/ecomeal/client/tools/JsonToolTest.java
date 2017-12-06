package ecomeal.client.tools;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class JsonToolTest {

	private JsonTool jsonTool;
	private JsonTool jsonToolSpy;
	private String jsonExample = "[" +
	                             "{" +
		                         "\"name\":\"ee\"," +
	                             "\"price\":789," +
	                             "\"category\":\"Panier vegan\"," +
	                             "\"category_image\":null," +
	                             "\"products\":[" +
	                             "{" +
	                             "\"name\":\"Bob\"," +
	                             "\"category\":\"Humain\"" +
	                             "}" +
	                             "]" +
	                             "}" +
	                             "]";
	private HttpURLConnection connection;
	private UrlWrapper url;
	private UrlWrapper urlSpy;
	private InputStream inputStream;
	
	@Before
	public void init() throws IOException {
		url = new UrlWrapper("http://vps434333.ovh.net/api/basket");
		urlSpy = Mockito.spy(url);
		connection = Mockito.mock(HttpURLConnection.class);
		inputStream = Mockito.mock(InputStream.class);
		jsonTool = new JsonTool(connection, inputStream);
		jsonToolSpy = Mockito.spy(jsonTool);
	}
	
	@Test
	public void testReadJson() throws IOException {
		Mockito.when(urlSpy.openConnection()).thenReturn(connection);
		Mockito.doNothing().when(connection).connect();
		Mockito.when(connection.getInputStream()).thenReturn(inputStream);
		
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();
		
		Mockito.when(jsonToolSpy.inputStreamToString(conn.getInputStream())).thenReturn(jsonExample);
		jsonToolSpy.readJson(urlSpy);
		Mockito.verify(urlSpy).openConnection();
		Mockito.verify(connection).connect();
		Mockito.verify(connection).getInputStream();
		Mockito.verify(jsonToolSpy).inputStreamToString(inputStream);
	}
	
	@Test
	public void testInputStreamToString() throws UnsupportedEncodingException {
		InputStream stream = new ByteArrayInputStream(jsonExample.getBytes(StandardCharsets.UTF_8.name()));
		assertEquals(jsonExample, jsonTool.inputStreamToString(stream));
	}

}