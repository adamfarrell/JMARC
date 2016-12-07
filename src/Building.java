import java.util.ArrayList; 

public class Building extends DatabaseObject
{
	private ArrayList<Classroom> innerArr; 
	private String buildingCode;
	private String campusID; 
	private String title;
	
	Building(String primaryKey, String campusID, String title, String buildingCode , ArrayList<Classroom> innerArr)
	{
		super(primaryKey);
		this.campusID = campusID; 
		this.buildingCode = buildingCode; 
		this.title = title;
		this.innerArr = new ArrayList<Classroom>(innerArr);

	}
	
	public void setBuildingCode(String buildingCode)
	{
		this.buildingCode = buildingCode; 
	}
	
	public String getBuildingCode()
	{
		return buildingCode; 
	}
	public String getTitle()
	{
		return title; 
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getCampusID()
	{
		return campusID; 
	}
	
	public void setCampusID(String campusID) {
		this.campusID = campusID;
	}
	
	public String toString()
	{
		return "(" + buildingCode + ") " + title; 
	}
	
	public void addClassroom(Classroom toAdd)
	{
		innerArr.add(toAdd); 
		
	}
	
	public void removeClassroom(String primaryKey)
	{
		for(int i = 0; i < innerArr.size(); i++)
		{
			if(innerArr.get(i).getPrimaryKey() == primaryKey)
			{
				innerArr.remove(i);
				return; 
			}
		}
		//Throw Error Saying Class doesn't exist 
	}
	
	public Classroom getClassroom(int index)
	{
		return innerArr.get(index); 
	}
	public ArrayList<Classroom> getClassrooms()
	{
		return innerArr; 
	}
}
