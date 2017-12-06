package ecomeal.client.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.vaadin.ui.Button;
import com.vaadin.ui.Slider;

import ecomeal.client.entity.Basket;
import ecomeal.client.tools.JsonTool;

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
	public String findAGoodSchedule(Button valideCommand, Slider from, Slider to) {
		
		Map<String,String> params = new HashMap<String,String>();
		
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDate();
		String dayS = "";
		String monthS = "";
		if(month < 10){
			monthS = "0" + month;
		}
		else{
			monthS = "" + month;
		}
		if(day < 10){
			dayS = "0" + day;
		}
		else{
			dayS = "" + day;
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date today = dateFormat.parse(dayS + "/" + monthS + "/" + year);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long time = date.getTime();
		String fromTime = "" + (long) (time + from.getValue() * 60000);
		String toTime = "" + (long) (time + to.getValue() * 60000);
		
		params.put("start_time",fromTime);
		params.put("end_time",toTime);
				
		String result = "";
		try {
			result = jsonTool.readJson(new URL("http://vps434333.ovh.net/api/deliveryTime_calculation"),params);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(from.getValue() == 960){
			valideCommand.setVisible(false);
			return "Il n'y a pas d'horaire valide" + result;
		}else{
			valideCommand.setVisible(true);
			return "Horaire selectionnée :" + result + transformToHour(from.getValue() + (Math.random()*30));
		}
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

}
