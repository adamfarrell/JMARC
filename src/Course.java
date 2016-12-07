public class Course extends DatabaseObject{
	
	private String prefix;
	private String courseNumber;
	private String title;
	private String description;
	private String creditHours;
	
	public Course(String primaryKey, String title, String creditHours, String prefix, String courseNumber, String description)
	{
		super(primaryKey); 
		this.prefix = prefix;
		this.courseNumber = courseNumber;
		this.title = title; 
		this.description = description; 
		this.creditHours = creditHours;
	}
	
	public void setCreditHours(String creditHours) 
	{
		this.creditHours = creditHours;
	}
	
	public String getCreditHours() 
	{
		return this.creditHours;
	}
	
	public void changeCourseNumber(String courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	
	public String getCourseNumber()
	{
		return this.courseNumber;
	}
	
	public void changeTitle(String title)
	{
		this.title = title; 
	}
	
	public String getTitle()
	{
		return this.title; 
	}
	
	public void changePrefix(String prefix)
	{
		this.prefix = prefix; 
	}
	
	public String getPrefix()
	{
		return prefix; 
	}
	
	public void changeDescription(String courseDiscription)
	{
		this.description = courseDiscription; 
	}
	
	public String getDescription()
	{
		return this.description;  
	}
	
	public String toString()
	{
		return prefix + " " + courseNumber + " " + title; 
	}

}
