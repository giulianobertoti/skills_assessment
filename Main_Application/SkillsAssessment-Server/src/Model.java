
import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model{
	
	ObjectContainer students = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../students11.db4o");
	ObjectContainer questions = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../questions.db4o");
	ObjectContainer competencies = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../competencies2.db4o");
	ObjectContainer institutions = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../institutions.db4o");
	
	
	public void addStudent(Student student){
		
		List<Competency> studentsCompetencies = new LinkedList<Competency>();
		
		Query query = competencies.query();
		query.constrain(Competency.class);
	    ObjectSet<Competency> allCompetencies = query.execute();
	    
	    for(Competency competency:allCompetencies){
	    	studentsCompetencies.add(competency);
	    }
		
	    student.setCompetencies(studentsCompetencies);
	    
	    
		students.store(student);
	}
	
	public void addQuestion(Question question){
		questions.store(question);
	}
	
	public void addCompetency(Competency competency){
		competencies.store(competency);
	}
	
	public void addInstitution(Institution institution){
		institutions.store(institution);
	}
	
	public void addCourse(String institutionName, String courseName){
		
		Query query = institutions.query();
		query.constrain(Institution.class);
	    List<Institution> allInstitutions = query.execute();
	    
	    for(Institution institution:allInstitutions){
	    	if(institution.getInstitutionName().equals(institutionName)){
	    		institution.addCourse(courseName);
	    		institutions.store(institution.getCourses());
	    		institutions.commit();
	    	}
	    }
		
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
	

	public List<Student> searchStudentsByInstitutionCourseYearPeriod(String institution, String course, int year, int period){
		
		List<Student> result = new LinkedList<Student>();
		
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
		
	    for(Student student:allStudents){
	    	if(student.getInstitution().equals(institution) && student.getCourse().equals(course) && student.getYear()==year && student.getPeriod()==period) result.add(student);
	    
	    }
		
		return result;
		
	}
	
	
	public int recordAnswer(int ra, int questionNumber, int answerCode){
		
		
		
		Query queryStudents = students.query();
		queryStudents.constrain(Student.class);
	    ObjectSet<Student> allStudents = queryStudents.execute();
	    
	    Query queryQuestions = questions.query();
		queryQuestions.constrain(Question.class);
	    ObjectSet<Question> allQuestions = queryQuestions.execute();
		
	    for(Student student:allStudents){
	    	if(student.getRa()==ra){
	    		student.setQuestion(student.getQuestion()+1);
	    		for(Question question:allQuestions){
	    			if(question.getNumber()==questionNumber){
	    				for(Answer answer: question.getAnswers()){
	    					for(Competency competency:answer.getCompetencies()){
	    						for(Competency studentCompetency:student.getCompetencies()){
	    							if(studentCompetency.getName().equals(competency.getName())){
	    								studentCompetency.setValue(studentCompetency.getValue()+competency.getValue());
	    								students.store(student.getCompetencies()); //to update an array in DB4O you must reference it explicitly -> Object.List -> if you reference only the Object like you do with others attributes it does not work
	    				    			students.commit();
	    							}
	    						}
	    						
	    						
	    					}
	    				}
	    					
	    			}
	    		}
	    		
	    		
	    		if(allQuestions.toArray().length < student.getQuestion()){
	    			student.setCompleted(true);
	    			students.store(student);
	    			students.commit();
	    			
	    			Query query = students.query();
	    			query.constrain(Student.class);
	    		    ObjectSet<Student> s = queryStudents.execute();
	    		    System.out.println(s);
	    			
	    			
	    			return 1;
	    		} else {
	    			
	    			return 0; //continua
	    		}
	    		
	    	}
	    	
	    	
	    	
	    }
		
		
	    
	    
	    return 0;
	    
	    
	}
	
	
	public List<Institution> getAllInstitutions(){
		
		Query query = institutions.query();
		query.constrain(Institution.class);
	    List<Institution> allInstitutions = query.execute();
	    
	    return allInstitutions;
		
	}
	
	public List<Course> getCourses(String institutionName){
		
		Query query = institutions.query();
		query.constrain(Institution.class);
	    List<Institution> allInstitutions = query.execute();
	    
	    for(Institution institution:allInstitutions){
	    	if(institution.getInstitutionName().equals(institutionName)){
	    		return institution.getCourses();
	    	}
	    }
	    
	    return null;
	    
	}
	
}
