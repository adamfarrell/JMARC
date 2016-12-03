import java.util.ArrayList; 

public class Building extends DatabaseObject
{

	private ArrayList<Classroom> innerArr; 
	private String buildingCode;
	private String campusID; 
	private String title;
	Building(String primaryKey,String campusID ,String title, String buildingCode , ArrayList<Classroom> innerArr)
	{
		super(primaryKey);
		this.campusID = campusID; 
		this.buildingCode = buildingCode; 
		this.title = title; 
		this.innerArr = new ArrayList<Classroom>();
		//innerArr.addAll(innerArr);
		this.innerArr = innerArr; 

	}
	public void SetBuildingTag(String buildingTag)
	{
		this.buildingCode = buildingCode; 
	}
	
	public String GetBuildingTag()
	{
		return buildingCode; 
	}
	public String GetBuildingTitle()
	{
		return title; 
	}
	public String GetCampusID()
	{
		return campusID; 
	}
	
	public String toString()
	{
		return buildingCode + " " + super.GetPrimaryKey(); 
	}
	
	public void AddClassroom(Classroom toAdd)
	{
		innerArr.add(toAdd); 
		
	}
	
	public void RemoveClassroom(String primaryKey)
	{
		for(int i = 0; i < innerArr.size(); i++)
		{
			if(innerArr.get(i).GetPrimaryKey() == primaryKey)
			{
				innerArr.remove(i);
				return; 
			}
		}
		//Throw Error Saying Class doesn't exist 
	}
	
	public Classroom GetClassroom(int index)
	{
		return innerArr.get(index); 
	}
	public ArrayList<Classroom> GetClassrooms()
	{
		return innerArr; 
	}
}
