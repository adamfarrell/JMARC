
public class DatabaseObject {
	private String primaryKey;
	
	public DatabaseObject(String primaryKey)
	{
		this.primaryKey = primaryKey; 
	}
	
	public String getPrimaryKey()
	{
		return this.primaryKey; 
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DatabaseObject))
			return false;
		if (object == this)
			return true;
		
		DatabaseObject otherCampus = (DatabaseObject) object;
		if (this.getPrimaryKey().equals(otherCampus.getPrimaryKey()))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + ((this.getPrimaryKey() == null) ? 0 : this.getPrimaryKey().hashCode());
		return result;
	}
}
