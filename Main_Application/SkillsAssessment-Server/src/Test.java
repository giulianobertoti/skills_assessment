
import spark.Spark;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;


public class Test {
	
	static Model model = new Model();
	
	public static void main(String[] args) throws JSONException{
		
		
		
		initializeModel();
		
		
		Spark.staticFileLocation("/public");
		
		REST controller = new REST(model); 
		
		
		controller.getLogin();
		controller.getStudentsQuestionbyRA();
		controller.getStudentCompetencies();
		controller.getQuestionByNumber();
		controller.getStudentsbyInstitutionCourseYearPeriod();
		
		
		
	}
	
	public static void initializeModel(){
		model.addStudent(new Student("joao@gmail.com", "12345", "Joao", 1212, Institution.FATECSJC, Course.BD ,2016, 2, new Competencies(9, 8, 6, 2, 4, 7, 2)));
		model.addStudent(new Student("lilian@gmail.com", "12345", "Lilian", 54321, Institution.FATECSJC, Course.ADS, 2017, 1, new Competencies(6, 3, 10, 4, 3, 9, 1)));
	
		List<Competency> competencies1 = new LinkedList<Competency>();
		Competency comp1 = new Competency("leadership", 1);
		Competency comp2 = new Competency("workGroup", 3);
		competencies1.add(comp1);
		competencies1.add(comp2);
		
		List<Answer> answers = new LinkedList<Answer>();
		Answer answersA = new Answer(1, "Interface Gráfica", competencies1);
		answers.add(answersA);
		
		List<Competency> competencies2 = new LinkedList<Competency>();
		Competency comp3 = new Competency("leadership", 0);
		Competency comp4 = new Competency("workGroup", 0);
		competencies2.add(comp3);
		competencies2.add(comp4);
		
		Answer answersB = new Answer(2, "Interface Linha de Comando", competencies2);
		answers.add(answersB);
		
		List<Competency> competencies3 = new LinkedList<Competency>();
		Competency comp5 = new Competency("leadership", 10);
		Competency comp6 = new Competency("workGroup", 10);
		Competency comp7 = new Competency("communication", 10);
		competencies3.add(comp5);
		competencies3.add(comp6);
		competencies3.add(comp7);
		
		Answer answersC = new Answer(3, "Interface Natural", competencies3);
		answers.add(answersC);
		
		List<Competency> competencies4 = new LinkedList<Competency>();
		Competency comp8 = new Competency("leadership", 0);
		competencies4.add(comp8);
		
		Answer answersD = new Answer(4, "Interface Organica", competencies4);
		answers.add(answersD);
		
		model.addQuestion(new Question(1, "https://www.youtube.com/watch?v=5t1FPSvpDko", "Qual é este tipo de Interação Humano Computador", answers));
	
	}
	
}
