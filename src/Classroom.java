
public class Classroom extends DatabaseObject{
	private String roomNumber;
	private String buildingID; 
	private String buildingTag;
	private String capacity; 
	private String numOfComps;
	
	public Classroom(String primaryKey, String roomNumber, String buildingID, String buildingTag, String capacity, String numOfComps)
	{
		super(primaryKey);
		this.roomNumber = roomNumber; 
		this.buildingID = buildingID;
		this.buildingTag = buildingTag;
		this.capacity = capacity; 
		this.numOfComps = numOfComps; 
	}
	
	public String getRoomNumber()
	{
		return roomNumber; 
	}
	
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String getBuildingID()
	{
		return this.buildingID; 
	}
	
	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}
	
	public String getCapacity()
	{
		return this.capacity; 
	}
	
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = Integer.toString(capacity);
	}
	
	public String getNumOfComps()
	{
		return numOfComps; 
	}
	
	public void setNumOfComps(String numOfComps) {
		this.numOfComps = numOfComps;
	}
	
	public void setNumOfComps(int numOfComps) {
		this.numOfComps = Integer.toString(numOfComps);
	}
	
	public String getBuildingTag() {
		return buildingTag;
	}
	
	public void setBuildingTag(String buildingTag) {
		this.buildingTag = buildingTag;
	}
	
	public String toString()
	{
		return buildingTag + " " + roomNumber;
	}
	

}
