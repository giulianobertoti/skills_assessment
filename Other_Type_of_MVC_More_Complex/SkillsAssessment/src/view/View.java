package view;
import java.util.List;

import model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.db4o.ObjectSet;

import controller.Controller;


public class View implements Observer{
	
	private Controller os;
	private Student student;
	
	public void setOS(Controller os){
		this.os = os;
	}
	
	public void routingOS(){
		os.makeRoute();
	}
	
	public void update(Student student){
		this.student = student;
	}

	public JSONArray generateJSON() throws JSONException{
		JSONArray jsonResult = new JSONArray();
 	    JSONObject jsonObj = new JSONObject();
 	    
 	    
 	    	
 	    try {
			jsonObj.put("name", this.student.getName());
			jsonObj.put("lidership", this.student.getCompetencies().getLeadership());
     	    jsonResult.put(jsonObj);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
 	    	
 	    return jsonResult;
	}

	
}
