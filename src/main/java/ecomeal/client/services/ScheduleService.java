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

import ecomeal.client.tools.JsonTool;
import ecomeal.client.tools.UrlWrapper;

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
		
		long time = getTimestamp(new Date());
		String fromTime = "" + (long) (time / 1000 + from.getValue() * 60);
		String toTime = "" + (long) (time / 1000 + to.getValue() * 60);
		System.out.println("TIME : " + (time / 1000));
		System.out.println("FROM : " + fromTime);
		System.out.println("TO : " + toTime);
		params.put("start_time",fromTime);
		params.put("end_time",toTime);
				
		String result = "";
		try {
			result = jsonTool.readJson(new UrlWrapper("http://vps434333.ovh.net/api/dtime_calculation"),params);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		JSONObject jsonObj = new JSONObject(result);
		double scheduleTime = jsonObj.getDouble("deliveryTime");
		if(scheduleTime != 0){
			valideCommand.setVisible(true);
			
			return "Horaire disponible : "+ transformToHour((((scheduleTime /60) + 60 /* GMT + 1*/) % 1440));
		}else{
			valideCommand.setVisible(false);
			return "Il n'y a pas d'horaire dans cette tranche horaire ,veuillez choisir une autre tranche horaire svp.";
		}
	}
	
	public long getTimestamp(Date date){
		
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.set(Calendar.MILLISECOND, 0);
		cd.set(Calendar.SECOND, 0);
		cd.set(Calendar.MINUTE, 0);
		cd.set(Calendar.HOUR_OF_DAY, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return cd.getTimeInMillis();
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
