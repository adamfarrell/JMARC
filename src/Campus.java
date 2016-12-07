

import java.util.ArrayList; 


public class Campus extends DatabaseObject
{
	private String name; 
	private String address;
	private ArrayList<Building> buildingList; 
	
	Campus(String primaryKey, String name, String address, ArrayList<Building> buildingList)
	{
		super(primaryKey);
		this.name = name;
		this.address = address;
		this.buildingList = new ArrayList<Building>();
		this.buildingList.addAll(buildingList); 
		
	}
	
//	@Override
//	public boolean equals(Object object) {
//		if (!(object instanceof Campus))
//			return false;
//		if (object == this)
//			return true;
//		
//		Campus otherCampus = (Campus) object;
//		if (this.getPrimaryKey().equals(otherCampus.getPrimaryKey()))
//			return true;
//		else
//			return false;
//	}
//	
//	@Override
//	public int hashCode() {
//		final int prime = 17;
//		int result = 1;
//		result = prime * result + ((this.getPrimaryKey() == null) ? 0 : this.getPrimaryKey().hashCode());
//		return result;
//	}
	
	public void changeAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void changeName(String name)
	{
		this.name = name; 
	}
	
	public String getName()
	{
		return name; 
	}
	
	public void addBuilding(Building toAdd)
	{
		buildingList.add(toAdd); 
	}
	
	public Building getBuilding(int index)
	{
		return buildingList.get(index); 
	}
	public ArrayList<Building> getBuildings()
	{
		return buildingList; 
	}
	
	public void removeBuilding(String primaryKey)
	{
		for(int i = 0; i < buildingList.size(); i++)
		{
			if(buildingList.get(i).getPrimaryKey() == primaryKey)
			{
				buildingList.remove(i); 
			}
		}
	}
	
	public String toString()
	{
//		return name + " " + super.getPrimaryKey(); 
		return name;
	}

}
