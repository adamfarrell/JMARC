
public class Professor {
	private int primaryKey; 
	private String firstName; 
	private String lastName;
	private int fillerHours;
	private int creditHours; 
	
	public Professor(int primaryKey ,String firstName, String lastName)
	{
		this.primaryKey = primaryKey; 
		this.firstName = firstName;
		this.lastName = lastName; 
	}
	
	public void ChangeCreditHours(int numberOfCreditHours)
	{
		this.creditHours = numberOfCreditHours; 
	}
	
	public int GetCreditHours()
	{
		return creditHours; 
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
		this.fillerHours = numberOfFillerHours; 
	}
	
	public int GetFillerHours()
	{
		return fillerHours; 
	}
	
	public int GetPrimaryKey()
	{
		return primaryKey; 
	}
	
	public String ToString()
	{
		return firstName + " " + lastName + " " + Integer.toString(creditHours) + " " + Integer.toString(fillerHours)
		 + " " + Integer.toString(primaryKey); 
	}

}
