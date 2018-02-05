package ecomeal.client.services;

import java.net.MalformedURLException;

import org.json.JSONObject;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.tools.UrlWrapper;
import ecomeal.client.tools.postTool;
import ecomeal.client.ui.MainUI;

public class ConnectionService {

	private JsonTool jsonTool;
	
	public ConnectionService(JsonTool jsontool){
		this.jsonTool = jsontool;
	}
	
	public boolean createUser(MainUI ui, String login, String mail, String password){
		
		postTool tool = new postTool();
		
		JSONObject jobj = new JSONObject();
        jobj.put("login",login);
        jobj.put("mail",mail);
        jobj.put("password",password);
        
        String result = "";
        try {
			result = tool.postMessage(new UrlWrapper(EcomealConstants.URL_ECOMEAL + "/api/register_customer"), jobj.toString(), "");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Probleme d'url");
			return false;
		}
        
        System.out.println(result);
        
        if(result.equals("PROBLEME")){
        	return false;
        }else{
        	return this.connect(ui, login, password);
        }
        
        
		
	}
	
	public boolean connect(MainUI ui, String login, String password){
		postTool tool = new postTool();
		
		JSONObject jobj = new JSONObject();
        jobj.put("login",login);
        jobj.put("password",password);
        
        String result = "";
        try {
			result = tool.postMessage(new UrlWrapper(EcomealConstants.URL_ECOMEAL + "/api/login_customer"), jobj.toString(), "");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Probleme d'url");
			return false;
		}
        
        System.out.println(result);
        if(result.equals("PROBLEME")){
        	return false;
        }else{
        	JSONObject resultJson = new JSONObject(result);
            
        	String token = resultJson.getString("X-Auth_Token");
        	String mail = resultJson.getString("mail");
        	System.err.println("Token :" +  token);
        	ui.getUser().setToken(token);
        	ui.getUser().setEmail(mail);
        	ui.getUser().setLogin(login);
        	return true;        	
        }
        
	}
}
