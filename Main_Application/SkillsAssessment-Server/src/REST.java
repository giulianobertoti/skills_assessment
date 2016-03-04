



import static spark.Spark.get;

import java.util.List;


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
	
	
	public void getLogin(){
		
		get("/login/:username/:password", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	 
	        	 
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	            
	            
	            try {
	            	Student student = model.login(request.params(":username"), request.params(":password"));
	            	
	            	if(student != null){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();

		        		jsonObj.put("ra", student.getRa());
		        		
		        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();

	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
         	    JSONObject jsonObj = new JSONObject();

        		jsonObj.put("ra", 0);
        		
        		
             	jsonResult.put(jsonObj);
             	
             	return jsonResult;
	            
	     	     
	         }
	         
	      });
			

	}
	
	
	public void getStudentCompetencies(){
		
		
	
		get("/competencies/:ra", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	 
	        	 
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	            Integer ra = Integer.parseInt(request.params(":ra"));
	        	
	            
	            try {
	            	Student student = model.searchStudentbyRA(ra);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObj = new JSONObject();

	        		jsonObj.put("name", student.getName());
	        		jsonObj.put("leadership", student.getCompetencies().getLeadership());
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
	
	public void getStudentsQuestionbyRA(){
		
		get("/user/:ra", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	 
	        	 
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	        	Integer ra = Integer.parseInt(request.params(":ra"));
	            
	            try {
	            	Integer questionNumber = model.searchStudentsQuestion(ra);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObj = new JSONObject();

	        		jsonObj.put("question", questionNumber);
	        		
	        		
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
		
		get("/questions/:number", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	            Integer number = Integer.parseInt(request.params(":number"));
	        	
	            
	            try {
	            	Question question = model.searchQuestionByCode(number);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObjQuestion = new JSONObject();
	         	    
	         	    jsonObjQuestion.put("number", question.getNumber());
	        		jsonObjQuestion.put("introduction", question.getIntroduction());
	        		jsonObjQuestion.put("introductionMediaType", question.getIntroductionMediaType());
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
	
	
	
	public void getStudentsbyInstitutionCourseYearPeriod(){
		
		get("/students/:institution/:course/:year/:period", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	        	
	        	
	        	Institution institution = Institution.valueOf(request.params(":institution").toUpperCase());
	        	Course course = Course.valueOf(request.params(":course").toUpperCase());
	        	Integer year = Integer.parseInt(request.params(":year"));
	            Integer period = Integer.parseInt(request.params(":period"));
	            
	            
	            try {
	            	List<Student> students = model.searchStudentsByInstitutionCourseYearPeriod(institution, course, year, period);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    

	         	    for(Student student:students){
	         	    	JSONObject jsonObj = new JSONObject();
	         	    	jsonObj.put("name", student.getName());
	         	    	jsonObj.put("username", student.getUserName());
	         	    	jsonObj.put("ra", student.getRa());
	         	    	jsonResult.put(jsonObj);
	         	    	
	         	    }

	             	
	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });

	         
	}
	
	
	public void setAnswerbyLetter(){
		
		get("/answer/:ra/:questionNumber/:answerCode", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	        	
	        	
	        	Integer ra = Integer.parseInt(request.params(":ra"));
	        	Integer questionNumber = Integer.parseInt(request.params(":questionNumber"));
	        	Integer answerCode = Integer.parseInt(request.params(":answerCode"));
	            
	            
	            
	            try {
	            	
	            	model.recordAnswer(ra, questionNumber, answerCode);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    
	         	    JSONObject jsonObj = new JSONObject();
	         	    	
	         	    jsonObj.put("status", "ok");
	         	    jsonResult.put(jsonObj);
	
	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });

	         
	}
	
		
}
