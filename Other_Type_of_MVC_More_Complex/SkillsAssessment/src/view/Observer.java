package view;


import org.json.JSONException;

import model.Student;




public interface Observer {
	
	public void update(Student student) throws JSONException;

}
