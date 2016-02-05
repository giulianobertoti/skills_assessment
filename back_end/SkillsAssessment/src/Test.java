

import model.Competencies;
import model.Model;
import model.Student;

import org.json.JSONException;

import controller.StudentRoutes;

import view.View;

public class Test {
	
	static Model model = new Model();
	
	public static void main(String[] args) throws JSONException{
		
		
		
		initializeModel();
		
		View view = new View();
		
		StudentRoutes controller = new StudentRoutes(model, view); //connection "Controller-> Model" and "Controller -> View" 
		
		controller.makeRoute();
		
		model.registerObserver(view); // connection "Model -> View
		
		
		
  		
		
	}
	
	public static void initializeModel(){
		model.addStudent(new Student("joao@gmail.com", "12345", "Joao", 20161, new Competencies(9, 8, 6, 2, 4, 7, 2)));
  		
	}
	
}
