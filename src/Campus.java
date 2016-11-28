

import java.util.ArrayList; 


public class Campus extends DatabaseObject
{
	private String name; 
	private ArrayList<Building> buildingList; 
	
	Campus(String primaryKey, String name, ArrayList<Building> buildingList)
	{
		super(primaryKey);
		this.name = name;
		this.buildingList = new ArrayList<Building>();
		this.buildingList.addAll(buildingList); 
		
	}
	
	public void ChangeName(String name)
	{
		this.name = name; 
	}
	
	public String GetName()
	{
		return name; 
	}
	
	public void AddBuilding(Building toAdd)
	{
		buildingList.add(toAdd); 
	}
	
	public Building GetBuilding(int index)
	{
		return buildingList.get(index); 
	}
	
	public void RemoveBuilding(String primaryKey)
	{
		for(int i = 0; i < buildingList.size(); i++)
		{
			if(buildingList.get(i).GetPrimaryKey() == primaryKey)
			{
				buildingList.remove(i); 
			}
		}
	}
	
	public String toString()
	{
//		return name + " " + super.GetPrimaryKey(); 
		return name;
	}

}
