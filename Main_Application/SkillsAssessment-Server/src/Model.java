
import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model{
	
	ObjectContainer students = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../competencies.db4o");
	ObjectContainer questions = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../questions.db4o");
	
	public void addStudent(Student student){
		students.store(student);
	}
	
	public void addQuestion(Question question){
		questions.store(question);
	}
	
	
	public Student login(String username, String password){
		
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
		
	    
	    for(Student student:allStudents){
	    	if(student.getUserName().equals(username) && student.getPassword().equals(password)){
	    		
	    		return student;
	    	}
	    	
	    }
	    
	    return null;

	}
	
	public Integer searchStudentsQuestion(int ra){
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
		
	    
	    for(Student student:allStudents){
	    	if(student.getRa()==ra){
	    		
	    		return student.getQuestion();
	    	}
	    	
	    }
	    
	    return null;

	}
	
	public Student searchStudentbyRA(int ra){
		
		
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
		
	    for(Student student:allStudents){
	    	if(student.getRa()==ra){
	    		return student;
	    	}
	    	
	    }
	    
	    return null;

		
	}
	
	public Question searchQuestionByCode(int code){
		
		Query query = questions.query();
		query.constrain(Question.class);
	    ObjectSet<Question> allQuestions = query.execute();
		
	    for(Question question:allQuestions){
	    	if(question.getNumber() == code){
	    		return question;
	    	}
	    	
	    }
	    
	    return null;
		
	}
	

	public List<Student> searchStudentsByInstitutionCourseYearPeriod(Institution institution, Course course, int year, int period){
		
		List<Student> result = new LinkedList<Student>();
		
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
		
	    for(Student student:allStudents){
	    	if(student.getInstitution().equals(institution) && student.getCourse().equals(course) && student.getYear()==year && student.getPeriod()==period) result.add(student);
	    
	    }
		
		return result;
		
	}
	
	
	public void recordAnswer(int ra, int questionNumber, int answerCode){
		
		
		
		Query queryStudents = students.query();
		queryStudents.constrain(Student.class);
	    ObjectSet<Student> allStudents = queryStudents.execute();
	    
	    Query queryQuestions = questions.query();
		queryQuestions.constrain(Student.class);
	    ObjectSet<Question> allQuestions = queryQuestions.execute();
		
	    for(Student student:allStudents){
	    	if(student.getRa()==ra){
	    		student.setQuestion(student.getQuestion()+1);
	    		for(Question question:allQuestions){
	    			if(question.getNumber()==questionNumber){
	    				for(Answer answer: question.getAnswers()){
	    					for(Competency competency:answer.getCompetencies()){
	    						if(competency.getName().equals("leadership")){
	    							student.getCompetencies().setLeadership(student.getCompetencies().getLeadership()+competency.getValue());
	    						}
	    						if(competency.getName().equals("communication")){
	    							student.getCompetencies().setCommunication(student.getCompetencies().getCommunication()+competency.getValue());
	    						}
	    						if(competency.getName().equals("values")){
	    							student.getCompetencies().setValues(student.getCompetencies().getValues()+competency.getValue());
	    						}
	    						if(competency.getName().equals("workGroup")){
	    							student.getCompetencies().setWorkGroup(student.getCompetencies().getWorkGroup()+competency.getValue());
	    						}
	    						if(competency.getName().equals("determination")){
	    							student.getCompetencies().setDetermination(student.getCompetencies().getDetermination()+competency.getValue());
	    						}
	    						if(competency.getName().equals("resilience")){
	    							student.getCompetencies().setResilience(student.getCompetencies().getResilience()+competency.getValue());
	    						}
	    						if(competency.getName().equals("autonomy")){
	    							student.getCompetencies().setAutonomy(student.getCompetencies().getAutonomy()+competency.getValue());
	    						}
	    						
	    					}
	    				}
	    					
	    			}
	    		}
	    	}
	    	
	    }
		
		//para ver se o cara chegou ao final, pega o lenght do array de questions e ve se é igual a pergunta que o user ta
	}
	
}
