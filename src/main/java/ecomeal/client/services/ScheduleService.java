package ecomeal.client.services;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.vaadin.ui.Button;
import com.vaadin.ui.Slider;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.tools.UrlWrapper;
import ecomeal.client.tools.postTool;

public class ScheduleService extends AbstractService{
	
	private JsonTool jsonTool;
	
	/**
	 * Constructor of ScheduleService
	 */
	public ScheduleService(JsonTool jsonTool){
		this.jsonTool = jsonTool;
	}
	
	/**
	 * Methode permettant de retourner une horraire valide via un appel à l'API
	 * @return
	 */
	public long findAGoodSchedule(Button valideCommand, Slider from, Slider to) {
		
		Map<String,String> params = new HashMap<String,String>();
		
		long time = getTimestamp(new Date());
		String fromTime = "" + (long) (time + from.getValue() * 60);
		String toTime = "" + (long) (time + to.getValue() * 60);
		System.out.println("TIME : " + time);
		System.out.println("FROM : " + fromTime);
		System.out.println("TO : " + toTime);
		params.put("start_time",fromTime);
		params.put("end_time",toTime);
				
		String result = "";
		try {
			result = jsonTool.readJson(new UrlWrapper("http://vps434333.ovh.net/api/dtime_calculation"),params);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			valideCommand.setVisible(false);
			return -2;
		}
		
		// Si le service n'est pas en route
		long scheduleTime = 0;
		try{
		JSONObject jsonObj = new JSONObject(result);
		scheduleTime = jsonObj.getLong("deliveryTime");
		System.out.println("SEND : " + scheduleTime );
		}
		catch(Exception e){
			valideCommand.setVisible(false);
			return -1;
		}
		if(scheduleTime != 0){
			valideCommand.setVisible(true);
			return scheduleTime;
		}else{
			valideCommand.setVisible(false);
			return 0;
		}
	}
	
	/**
	 * Change a double representing the number of minutes since 1970 to String
	 * @param time the timestamp
	 * @return the goo String
	 */
	public String ScheduleToString(long time){
		if(time == -2){
			return "Malformed";
		}
		else if(time == -1){
			return "Le service est indisponible pour le moment.";
		}
		else if(time == 0){
			return "Il n'y a pas d'horaire dans cette tranche horaire ,veuillez choisir une autre tranche horaire svp.";
		}
		else if(time > 0){
			return "Horaire disponible : "+ transformToHour(((time /60) % 1440));
		}
		else{
			return "Impossible";
		}
	}
	
	/**
	 * Return the timestamp of today at 00:00 GMT 0
	 * @param date
	 * @return
	 */
	public long getTimestamp(Date date){
		
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		
		long time = cd.getTimeInMillis() - (cd.getTimeInMillis() % (1000 * 60 * 60 * 24));
		time = time / 1000;
		System.out.println("Time 0:00 :" + time);
		return time;
	}
	
	/**
	 * Return the timestamp now at minute
	 * @param date
	 * @return
	 */
	public long getTimestampNow(){
		
		Calendar cd = Calendar.getInstance();
		cd.setTime(new Date());
		
		long time = cd.getTimeInMillis() - (cd.getTimeInMillis() % (1000 * 60));
		time = time / 1000;
		time = time + 3600; // GMT + 1
		return time;
	}

	/**
	 * Transforme une valeur double représentant le nombre de minutes en chaine de caractères XXhYY
	 * @param Dvalue le nombre de minutes dans une journée
	 * @return
	 */
	public String transformToHour(double Dvalue) {
		int value = (int) Dvalue;
		String hour = (value / 60) + "h";
		String minute;
		if(value % 60 < 10){
			minute = "0" + (value % 60);
		}
		else{
			minute = "" + (value % 60);
		}
		
		return hour + minute;
	}

	public void validateOrder(Order order, long deliveryTime) {
		
		/*
		 * Envoyer les infos de la commande en POST
		 */
		
		postTool tool = new postTool();
		
    	order.setDeliveryTime(deliveryTime);
		
		order.setOrderTime(getTimestampNow());
		
		order.setId(-2);
		
		
	}

}
