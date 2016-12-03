
public class Classroom extends DatabaseObject{
	private String roomNumber;
	private String buildingID; 
	private String capacity; 
	private String NumOfComps;
	
	public Classroom(String primaryKey, String roomNumber, String buildingID, String capacity, String NumOfComps)
	{
		super(primaryKey);
		this.roomNumber = roomNumber; 
		this.buildingID = buildingID;
		this.capacity = capacity; 
		this.NumOfComps = NumOfComps; 
	}
	
	public String GetRoomNumber()
	{
		return roomNumber; 
	}
	
	public String GetBuildingID()
	{
		return this.buildingID; 
	}
	public String GetCapacity()
	{
		return this.capacity; 
	}
	public String GetNumOfComps()
	{
		return this.NumOfComps; 
	}
	public String toString()
	{
//		return roomNumber + " " + super.GetPrimaryKey(); 
		return roomNumber + " " + buildingID+ " " + capacity + " " + NumOfComps;
	}
	

}
