
public class Classroom extends DatabaseObject{
	private String roomNumber;
	private String buildingTag; 
	
	public Classroom(String primaryKey, String roomNumber, String buildingTag)
	{
		super(primaryKey);
		this.roomNumber = roomNumber; 
		this.buildingTag = buildingTag; 
	}
	
	public String GetRoomNumber()
	{
		return roomNumber; 
	}
	
	public String GetBuildingTag()
	{
		return this.buildingTag; 
	}
	
	public String toString()
	{
//		return roomNumber + " " + super.GetPrimaryKey(); 
		return roomNumber + " " + buildingTag;
	}
	

}
