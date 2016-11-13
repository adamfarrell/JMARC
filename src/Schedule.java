import java.util.ArrayList;

public class Schedule {
	
	private ArrayList<Section> sections;
	
	public Schedule(){
		
	}
	
	public void AddSection(Section newSection){
		sections.add(newSection);
	}
	
	public void RemoveSection(Section deletedSection){
		sections.remove(deletedSection);
	}
	
	
}
