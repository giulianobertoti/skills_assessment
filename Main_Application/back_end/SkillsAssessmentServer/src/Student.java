

public class Student {
	
	private String userName;
	private String password;
	private String name;
	private int ra;
	private int year;
	private int period;
	private Course course;
	private Competencies competencies;
	
	public Student(String userName, String password, String name, int ra,
			int year, int period, Course course, Competencies competencies) {
		
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.ra = ra;
		this.year = year;
		this.period = period;
		this.course = course;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Competencies getCompetencies() {
		return competencies;
	}

	public void setCompetencies(Competencies competencies) {
		this.competencies = competencies;
	}
	
	
	
	
	

}
