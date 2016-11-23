
public class Classroom extends DatabaseObject{
	private String roomNumber;
	
	public Classroom(int primaryKey, String roomNumber)
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
		return roomNumber + " " + Integer.toString(super.GetPrimaryKey()); 
	}

}
