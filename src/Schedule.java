import java.util.ArrayList;

public class Schedule extends DatabaseObject{
	
	
	private String semester;
	private String year;
	private ArrayList<Section> sections; 
	
	public Schedule(String primaryKey, String semester, String year, ArrayList<Section> Sections){
		super(primaryKey);
		this.semester = semester;
		this.year = year;
		this.sections = new ArrayList<Section>();
		this.sections = Sections; 
	}
	
	public void AddSection(Section newSection){
		sections.add(newSection);
	}
	
	public void RemoveSection(Section deletedSection){
		sections.remove(deletedSection);
	}
	public ArrayList<Section> getSections()
	{
		return sections;
	}
	public String getSemester()
	{
		return semester; 
	}
	public String getYear()
	{
		return year; 
	}
	public String toString() {
		return semester + " " + year;
	}
}
