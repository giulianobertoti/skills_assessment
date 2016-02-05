package controller;



import static spark.Spark.get;

import model.Model;


import org.json.JSONArray;
import org.json.JSONException;


import spark.Request;
import spark.Response;
import spark.Route;


import view.View;


public class StudentRoutes implements Controller{
	
	private Model model;
	private View view;
	
	public StudentRoutes(Model store, View view){
		this.model = store;
		this.view = view;
	}
	
	public void makeRoute(){
		
		get(new Route("/competencies/:ra") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	
	            Integer ra = Integer.parseInt(request.params(":ra"));
	        	
	            
	            try {
	            	model.searchStudentByRA(ra);
	            	JSONArray jsonResult = view.generateJSON();
	     	    	return jsonResult;
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
	     		
	     	    
	     	    return null;
	     	    
	     	    
	         }
	      });
	         
	}
		
}
