import java.util.ArrayList;

public class Schedule extends DatabaseObject{
	
	private ArrayList<Section> sections;
	private String semester;
	private String year;
	
	public Schedule(String primaryKey, String semester, String year){
		super(primaryKey);
		this.semester = semester;
		this.year = year;
	}
	
	public void AddSection(Section newSection){
		sections.add(newSection);
	}
	
	public void RemoveSection(Section deletedSection){
		sections.remove(deletedSection);
	}
	
	public String toString() {
		return semester + " " + year;
	}
}
