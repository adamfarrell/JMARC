
public class Course extends DatabaseObject{
	
	private String courseTag;
	private String courseNumber;
	private String courseName;
	private String courseDescription;
	
	public Course(String primaryKey, String courseName, String courseTag, String courseNumber, String courseDescription)
	{
		super(primaryKey); 
		this.courseTag = courseTag;
		this.courseNumber = courseNumber;
		this.courseName = courseName; 
		this.courseDescription = courseDescription; 
	}
	
	public void ChangeCourseNumber(String courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	
	public String GetCourseNumber()
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
		return courseTag + " " + courseNumber + " " + courseName + " " + courseDescription; 
	}

}
