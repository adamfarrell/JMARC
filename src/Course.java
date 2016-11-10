
public class Course extends DatabaseObject{
	
	private String courseTag;
	private int courseNumber;
	private String courseName;
	private String courseDescription;
	
	public Course(String courseTag, int courseNumber, String courseName, int primaryKey)
	{
		super(primaryKey); 
		this.courseTag = courseTag;
		this.courseNumber = courseNumber;
		this.courseName = courseName; 
		 
	}
	
	public void ChangeCourseNumber(int courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	
	public int GetCourseNumber()
	{
		return this.courseNumber;
	}
	
	public void ChangeCourseName(String courseName)
	{
		this.courseName = courseName; 
	}
	
	public String GetCourseName()
	{
		return this.courseName; 
	}
	
	public void ChangeCourseTag(String courseTag)
	{
		this.courseTag = courseTag; 
	}
	
	public String GetCourseTag()
	{
		return courseTag; 
	}
	
	public void ChangeCourseDescription(String courseDiscription)
	{
		this.courseDescription = courseDiscription; 
	}
	
	public String GetCourseDescription()
	{
		return this.courseDescription;  
	}
	
	public String toString()
	{
		return courseTag + " " + Integer.toString(courseNumber) + " " + courseName + " " + courseDescription; 
	}

}
