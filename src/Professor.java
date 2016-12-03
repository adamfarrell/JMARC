
public class Professor extends DatabaseObject
{
	
	private String firstName; 
	private String lastName;
	private String fillerHours;
	private String creditHours; 
	
	public Professor(String primaryKey ,String firstName, String lastName, String fillerHours, String creditHours)
	{
		super(primaryKey); 
		
		this.firstName = firstName;
		this.lastName = lastName; 
		this.fillerHours = fillerHours; 
		this.creditHours = creditHours; 
	}
	
	public void ChangeCreditHours(int numberOfCreditHours)
	{
		this.creditHours = String.valueOf(numberOfCreditHours); 
	}
	
	public int GetCreditHours()
	{
		return Integer.valueOf(creditHours); 
	}
	
	public void ChangeFirstName(String firstName)
	{
		this.firstName = firstName; 
	}
	
	public String GetFirstName()
	{
		return firstName;
	}
	
	public void ChangeLastName(String lastName)
	{
		this.lastName = lastName; 
	}
	
	public String GetLastName()
	{
		return lastName; 
	}
	
	public void ChangeFillerHours(int numberOfFillerHours)
	{
		this.fillerHours = String.valueOf(numberOfFillerHours); 
	}
	
	public int GetFillerHours()
	{
		return Integer.valueOf(fillerHours); 
	}
	
	
	public String toString()
	{
//		return firstName + " " + lastName + " " + Integer.toString(creditHours) + " " + Integer.toString(fillerHours)
//		 + " " + super.GetPrimaryKey(); 
		return firstName + " " + lastName;
	}
	
}
