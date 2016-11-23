

import java.util.ArrayList; 


public class Campus extends DatabaseObject
{
	private String name; 
	private ArrayList<Building> buildingList; 
	
	Campus(int primaryKey, String name)
	{
		super(primaryKey);
		this.name = name;
		buildingList = new ArrayList<Building>();
		
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
	
	public void RemoveBuilding(int primaryKey)
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
		return name + " " + Integer.toString(super.GetPrimaryKey()); 
	}

}
