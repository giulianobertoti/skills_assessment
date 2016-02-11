package model;
/*
* Copyright 2014 Giuliano Bertoti 
* Released under the MIT license 
* github.com/giulianobertoti
*/

import java.util.List;
import java.util.LinkedList;

import org.json.JSONException;

import view.Observer;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model implements Subject{
	
	private List<Observer> observers = new LinkedList<Observer>();
	ObjectContainer students = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../competencies.db4o");
	
	
	public void registerObserver(Observer o){
		observers.add(o);
	}
	
	public void notifyObservers(Student student) throws JSONException{
		for(Observer o : observers){
			o.update(student);
		}
	}
	
	public void addStudent(Student student){
		students.store(student);
	}
	
	
	public void searchStudentByRA(int ra) throws JSONException{
		
		
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
		
	    for(Student student:allStudents){
	    	if(student.getRa()==ra){
	    		notifyObservers(student);
	    		break;
	    	}
	    	
	    }
	    
	    
		
	}
	
	

}
