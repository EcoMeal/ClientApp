package ecomeal.client.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ecomeal.client.tools.JsonTool;

public class ScheduleServiceTest {
	
	private ScheduleService service;
	
	@Before
	public void init() {
		JsonTool jsonTool = new JsonTool(null);
		service = new ScheduleService(jsonTool);
	}
	
	@Test
	public void findAGoodScheduleTest(){
		
	}
	
	@Test
	public void transformToHourTest(){
		//Cas normal
		assertEquals(service.transformToHour(960), "16h00");
		//Cas où il faut ajouter un '0' après le 'h'
		assertEquals(service.transformToHour(1147), "19h07");
	}

}
