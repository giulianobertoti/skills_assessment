



import static spark.Spark.get;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import spark.Request;
import spark.Response;
import spark.Route;


public class REST{
	
	private Model model;
	
	
	public REST(Model store){
		this.model = store;
	}
	
	public void getStudentCompetencies(){
		
		get(new Route("/competencies/:ra") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	
	            Integer ra = Integer.parseInt(request.params(":ra"));
	        	
	            
	            try {
	            	Student student = model.searchStudentByRA(ra);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObj = new JSONObject();

	        		jsonObj.put("name", student.getName());
	        		jsonObj.put("lidership", student.getCompetencies().getLeadership());
	        		jsonObj.put("communication", student.getCompetencies().getCommunication());
	        		jsonObj.put("values", student.getCompetencies().getValues());
	        		jsonObj.put("workGroup", student.getCompetencies().getWorkGroup());
	        		jsonObj.put("determination", student.getCompetencies().getDetermination());
	        		jsonObj.put("resilience", student.getCompetencies().getResilience());
	        		jsonObj.put("autonomy", student.getCompetencies().getAutonomy());
	        		
	             	jsonResult.put(jsonObj);
	             	
	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });

	         
	}
	
	public void getQuestionByNumber(){
		
		get(new Route("/questions/:number") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	
	            Integer number = Integer.parseInt(request.params(":number"));
	        	
	            
	            try {
	            	Question question = model.searchQuestionByCode(number);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObjQuestion = new JSONObject();
	         	    
	         	    jsonObjQuestion.put("number", question.getNumber());
	        		jsonObjQuestion.put("introduction", question.getIntroduction());
	        		jsonObjQuestion.put("question", question.getQuestion());
	        		jsonObjQuestion.put("answer", question.getAnswers());
	        		
	        		jsonResult.put(jsonObjQuestion);

	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });
		
	}
		
}
