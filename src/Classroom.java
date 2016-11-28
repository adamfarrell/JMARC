
public class Classroom extends DatabaseObject{
	private String roomNumber;
	
	public Classroom(String primaryKey, String roomNumber)
	{
		super(primaryKey);
		this.roomNumber = roomNumber; 
	}
	
	public String GetRoomNumber()
	{
		return roomNumber; 
	}
	
	public String toString()
	{
//		return roomNumber + " " + super.GetPrimaryKey(); 
		return roomNumber;
	}

}
