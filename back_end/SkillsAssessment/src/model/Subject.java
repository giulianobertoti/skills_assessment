package model;
import org.json.JSONException;




public interface Subject {

	public void notifyObservers(Student student) throws JSONException;
	
}
