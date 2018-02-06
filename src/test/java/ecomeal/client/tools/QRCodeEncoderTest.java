package ecomeal.client.tools;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.entity.PresetBasket;
import ecomeal.client.entity.Product;

public class QRCodeEncoderTest {

	@Test
	public void testEncodeDecode() {
		Product product = new Product("Steak", "Viande");
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		Basket basket = new PresetBasket(1, "panier test", 5.0, "category test", "", products);
		Order order = new Order();
		order.setId(1);
		order.setOrderTime(1);
		order.setDeliveryTime(2);
		order.addBasket(basket);
		
		Order result = (Order) QRCodeEncoder.decodeStringToObject(QRCodeEncoder.encodeObjectToString(order));
		assertEquals(order.getId(), result.getId());
		assertEquals(order.getOrderTime(), result.getOrderTime());
		assertEquals(order.getDeliveryTime(), result.getDeliveryTime());
		assertEquals(order.getBaskets().size(), result.getBaskets().size());
		Map<Basket, Integer> resultProducts = result.getBaskets();
		PresetBasket resultBasket;
		for(Map.Entry<Basket, Integer> entry : resultProducts.entrySet()) {
			resultBasket = (PresetBasket) entry.getKey();
			assertEquals(basket.getId(), resultBasket.getId());
			assertEquals(basket.getName(), resultBasket.getName());
			assertEquals(basket.getPrice(), resultBasket.getPrice());
			assertEquals(basket.getCategory(), resultBasket.getCategory());
			for(Product resultProduct : resultBasket.getProducts()) {
				assertEquals(product.getName(), resultProduct.getName());
				assertEquals(product.getCategory(), resultProduct.getCategory());
			}
		}
	}

}
