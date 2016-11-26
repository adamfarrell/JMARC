import java.util.ArrayList; 

public class Building extends DatabaseObject
{

	private ArrayList<Classroom> innerArr; 
	private String buildingTag; 
	Building(String primaryKey, String buildingTag)
	{
		super(primaryKey);
		this.buildingTag = buildingTag; 
		innerArr = new ArrayList<Classroom>(); 

	}
	public void SetBuildingTag(String buildingTag)
	{
		this.buildingTag = buildingTag; 
	}
	
	public String GetBuildingTag()
	{
		return buildingTag; 
	}
	
	public String toString()
	{
		return buildingTag + " " + super.GetPrimaryKey(); 
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
}
