import java.util.ArrayList;

public class University {
	private ArrayList<Course> courses;
	private ArrayList<Schedule> schedules;
	private ArrayList<Professor> professors;
	private ArrayList<Campus> campuses;
	
	public University(){
		
	}
	
	public void AddCourse(Course newCourse){
		courses.add(newCourse);
	}
	
	public void AddProfessor(Professor newProfessor){
		professors.add(newProfessor);
	}
	
	public void AddCampus(Campus newCampus){
		campuses.add(newCampus);
	}
	
	public void AddSchedule(Schedule newSchedule){
		schedules.add(newSchedule);
	}
	
	
}
