



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
	            	
	            	JSONArray jsonResult = new JSONArray(); //first
	         	    JSONObject jsonObjQuestion = new JSONObject();
	         	    JSONObject jsonObjAnswer = new JSONObject();

	         	    jsonObjQuestion.put("number", question.getNumber());
	        		jsonObjQuestion.put("introduction", question.getIntroduction());
	        		jsonObjQuestion.put("question", question.getQuestion());
	        		
	        		JSONArray jsonResultAnswers = new JSONArray(); //second
	        		JSONObject [] innerObjectAnswer = new JSONObject[question.getAnswers().size()];
	        		
	        		JSONArray jsonResultCompetencies = new JSONArray(); //third
	        		
	        		for(int i = 0; i<question.getAnswers().size(); i++){
	        			innerObjectAnswer[i]=new JSONObject();
	        			innerObjectAnswer[i].put("code", question.getAnswers().get(i).getCode());
	        			innerObjectAnswer[i].put("answer", question.getAnswers().get(i).getAnswer());
		        		
	        			JSONObject [] innerObjectCompetencies = new JSONObject[question.getAnswers().get(i).getCompetencies().size()];
	        			
		        		for(int j = 0; j < question.getAnswers().get(i).getCompetencies().size(); j++){
		        			innerObjectAnswer[j]=new JSONObject();
		        			innerObjectAnswer[j].put(question.getAnswers().get(i).getCompetencies().get(j).getName(), question.getAnswers().get(i).getCompetencies().get(j).getValue());		        		
		        		}
		        		
		        		jsonResultCompetencies.put(innerObjectCompetencies);		        		
	        		}
	        		
	        		jsonResultAnswers.put(innerObjectAnswer);
	        		//jsonResultAnswers.put(jsonResultCompetencies);
	        		//jsonResultAnswers.put(jsonResultCompetencies);
	        		
	             	
	             	jsonObjQuestion.put("answers", jsonResultAnswers);
	             	//jsonObjAnswer.put("competencies", jsonResultCompetencies);
	             	
	             	
	             	jsonResult.put(jsonObjQuestion);
	             	jsonResult.put(jsonObjAnswer);

	        		//jsonResult.put(jsonResultAnswers);

	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });
		
	}
		
}
