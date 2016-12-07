
public class Professor extends DatabaseObject
{
	
	private String firstName; 
	private String lastName;
	private String releaseHours;
	private String creditHours; 
	private String status;
	
	public Professor(String primaryKey, String firstName, String lastName, String status, String creditHours, String releaseHours)
	{
		super(primaryKey); 
		
		this.firstName = firstName;
		this.lastName = lastName; 
		this.status = status;
		this.releaseHours = releaseHours; 
		this.creditHours = creditHours; 
	}
	
	public void changeStatus(String status) 
	{
		this.status = status;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void changeCreditHours(int numberOfCreditHours)
	{
		this.creditHours = String.valueOf(numberOfCreditHours); 
	}
	
	public int getCreditHours()
	{
		return Integer.valueOf(creditHours); 
	}
	
	public void changeFirstName(String firstName)
	{
		this.firstName = firstName; 
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void changeLastName(String lastName)
	{
		this.lastName = lastName; 
	}
	
	public String getLastName()
	{
		return lastName; 
	}
	
	public void changeReleaseHours(int numberOfReleaseHours)
	{
		this.releaseHours = String.valueOf(numberOfReleaseHours); 
	}
	
	public int getReleaseHours()
	{
		return Integer.valueOf(releaseHours); 
	}
	
	
	public String toString()
	{
//		return firstName + " " + lastName + " " + Integer.toString(creditHours) + " " + Integer.toString(releaseHours)
//		 + " " + super.getPrimaryKey(); 
//		return firstName + " " + lastName;
		return lastName + ", " + firstName;
	}
	
}
