
public class Section extends DatabaseObject{

	
	private Classroom classroom;
	private Professor professor;
	private Course course;
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	private int numSeats;
	private String daysOfWeek;
	
	public Section(int primaryKey, Classroom classroom, Professor professorID, Course course, String startTime, String endTime, String startDate, String EndDate, int numSeats, String daysOfWeek){
		super(primaryKey); 
		this.classroom = classroom;
		this.professor = professor;
		this.course = course;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numSeats = numSeats;
		this.daysOfWeek = daysOfWeek;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public String ToString(){
		return "Professor " + professor.GetFirstName() + " " + professor.GetLastName() + " teaches " + course.GetCourseTag() + " " + course.GetCourseNumber() + " in " + classroom.GetRoomNumber();
	}
}
