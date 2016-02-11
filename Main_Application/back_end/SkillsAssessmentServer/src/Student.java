

public class Student {
	
	private String userName;
	private String password;
	private String name;
	private int ra;
	private Competencies competencies;
	
	
	
	public Student(String userName, String password, String name, int ra,
			Competencies competencies) {
		
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.ra = ra;
		this.competencies = competencies;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRa() {
		return ra;
	}
	public void setRa(int ra) {
		this.ra = ra;
	}
	public Competencies getCompetencies() {
		return competencies;
	}
	public void setCompetencies(Competencies competencies) {
		this.competencies = competencies;
	}
	
	

}
