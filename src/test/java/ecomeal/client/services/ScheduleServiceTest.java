package ecomeal.client.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.MalformedURLException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.vaadin.ui.Button;
import com.vaadin.ui.Slider;

import ecomeal.client.tools.JsonTool;
import ecomeal.client.tools.UrlWrapper;

public class ScheduleServiceTest {
	
	private ScheduleService service;
	private JsonTool jsonTool;
	
	@Before
	public void init() {
		jsonTool = new JsonTool();
		service = new ScheduleService(jsonTool);
	}
	
	@Test
	public void findAGoodScheduleGoodTest(){
		jsonTool = Mockito.mock(JsonTool.class);
		Mockito.when(jsonTool.readJson(Mockito.any(UrlWrapper.class), Mockito.anyMapOf(String.class, String.class))).thenReturn("{'deliveryTime':1512918000}");
		service = new ScheduleService(jsonTool);
		Button b = new Button("ButtonTest");
		Slider from = new Slider(960, 1350);
		Slider to = new Slider(990, 1380);
		double result = service.findAGoodSchedule(b, from, to);
		assertEquals(1512918000, result, 0.001);
	}
	
	@Test
	public void findAGoodSchedulePasGoodTest(){
		jsonTool = Mockito.mock(JsonTool.class);
		Mockito.when(jsonTool.readJson(Mockito.any(UrlWrapper.class), Mockito.anyMapOf(String.class, String.class))).thenReturn("{'deliveryTime':0}");
		service = new ScheduleService(jsonTool);
		Button b = new Button("ButtonTest");
		Slider from = new Slider(960, 1350);
		Slider to = new Slider(990, 1380);
		double result = service.findAGoodSchedule(b, from, to);
		assertEquals(0, result, 0.001);
	}
	
	
	@Test
	public void findAGoodScheduleMalformedURLExceptionTest(){ 
		jsonTool = Mockito.mock(JsonTool.class);
		service = new ScheduleService(jsonTool);
		Button b = new Button("ButtonTest");
		Slider from = new Slider(960, 1350);
		Slider to = new Slider(990, 1380);
		Mockito.when(jsonTool.readJson(Mockito.any(UrlWrapper.class), Mockito.anyMapOf(String.class, String.class))).thenThrow(MalformedURLException.class);
		assertEquals(service.findAGoodSchedule(b, from, to),-2, 0.001);
	}
	
	@Test
	public void getTimestampTest(){
		
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.MILLISECOND, 12);
		cd.set(Calendar.SECOND, 50);
		cd.set(Calendar.MINUTE, 15);
		cd.set(Calendar.HOUR_OF_DAY, 10);
		cd.set(Calendar.DAY_OF_MONTH, 15);
		cd.set(Calendar.MONTH, 8);
		cd.set(Calendar.YEAR, 2080);
		
		
		Calendar cd2 = Calendar.getInstance();
		cd2.set(Calendar.MILLISECOND, 0);
		cd2.set(Calendar.SECOND, 0);
		cd2.set(Calendar.MINUTE, 0);
		cd2.set(Calendar.HOUR_OF_DAY, 0);
		cd2.set(Calendar.DAY_OF_MONTH, 15);
		cd2.set(Calendar.MONTH, 8);
		cd2.set(Calendar.YEAR, 2080);
		
		long test = service.getTimestamp(cd.getTime());
		assertEquals(cd2.getTimeInMillis() / 1000,test);
	}
	
	@Test
	public void transformToHourTest(){
		//Cas normal
		assertEquals(service.transformToHour(1151), "19h11");
		
	}
	
	@Test
	public void transformToHourOuIlFautAjouterUnZeroTest(){
		//Cas où il faut ajouter un '0' après le 'h'
		assertEquals(service.transformToHour(960), "16h00");
	}

}
