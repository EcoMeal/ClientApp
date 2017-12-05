package ecomeal.client.services;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Product;
import ecomeal.client.tools.JsonTool;

public class BasketServiceTest {

	private BasketService service;
	private JsonTool jsonTool;
	
	@Before
	public void init() {
		jsonTool = Mockito.mock(JsonTool.class);
		service = new BasketService(jsonTool);
	}
	
	@Test
	public void testFindAll() {
		
		// INIT MOCKS
		Mockito.when(jsonTool.readJson(Mockito.anyString())).thenReturn(readExampleJson());
		
		
		// INIT EXPECTED RESULT
		Product product = new Product("Bob", "Humain");
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		Basket basket = new Basket("ee", 789, "Panier vegan", "", products);
		List<Basket> baskets = new ArrayList<Basket>();
		baskets.add(basket);		
		
		
		// TEST
		List<Basket> result = service.findAll();
		assertEquals(result.size(), baskets.size());
		assertEquals(result.get(0).getName(), basket.getName());
		assertEquals(result.get(0).getPrice(), basket.getPrice());
		assertEquals(result.get(0).getCategory(), basket.getCategory());
		assertEquals(result.get(0).getProducts().size(), basket.getProducts().size());
		assertEquals(result.get(0).getProducts().get(0).getName(), product.getName());
		assertEquals(result.get(0).getProducts().get(0).getCategory(), product.getCategory());
	}
	
	private String readExampleJson() {
		StringBuilder builder = new StringBuilder();
		
		try {
			FileReader fr = new FileReader(getClass().getClassLoader().getResource("example.json").getFile());
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				builder.append(line);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

}
