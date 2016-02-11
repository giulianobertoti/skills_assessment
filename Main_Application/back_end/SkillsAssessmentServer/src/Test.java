



import org.json.JSONException;



public class Test {
	
	static Model model = new Model();
	
	public static void main(String[] args) throws JSONException{
		
		
		
		initializeModel();
		
		
		REST controller = new REST(model); 
		
		controller.makeRoute();
		
		
	}
	
	public static void initializeModel(){
		model.addStudent(new Student("joao@gmail.com", "12345", "Joao", 20161, new Competencies(9, 8, 6, 2, 4, 7, 2)));
		model.addStudent(new Student("lilian@gmail.com", "12345", "Lilian", 20162, new Competencies(6, 3, 10, 4, 3, 9, 1)));
	}
	
}
