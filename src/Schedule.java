import java.util.ArrayList;

public class Schedule extends DatabaseObject{
	
	private ArrayList<Section> sections;
	
	public Schedule(int primaryKey){
		super(primaryKey); 
	}
	
	public void AddSection(Section newSection){
		sections.add(newSection);
	}
	
	public void RemoveSection(Section deletedSection){
		sections.remove(deletedSection);
	}
	
	
}
