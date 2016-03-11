

public class Student {
	
	private String userName;
	private String password;
	private String name;
	private int ra;
	private Institution institution;
	private Course course;
	private int year;
	private int period;
	private Competencies competencies;
	private int question = 1;
	private boolean completed = false;
	
	public Student(String userName, String password, String name, int ra,
			Institution institution, Course course, int year, int period,
			Competencies competencies) {

		this.userName = userName;
		this.password = password;
		this.name = name;
		this.ra = ra;
		this.institution = institution;
		this.course = course;
		this.year = year;
		this.period = period;
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


	public Institution getInstitution() {
		return institution;
	}


	public void setInstitution(Institution institution) {
		this.institution = institution;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
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


	public Competencies getCompetencies() {
		return competencies;
	}


	public void setCompetencies(Competencies competencies) {
		this.competencies = competencies;
	}
	
	
	public int getQuestion(){
		return question;
	}
	
	public void setQuestion(int question){
		this.question = question;
	}

	public boolean getCompleted(){
		return completed;
	}
	
	public void setCompleted(boolean completed){
		this.completed = completed;
	}
	
}
