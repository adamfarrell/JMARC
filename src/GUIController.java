import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.binding.*;
import javafx.collections.*;
import javafx.scene.input.*;
import javafx.event.*;
import java.util.*;
import javafx.scene.control.Alert.*;

public class GUIController implements Initializable {
	ObservableList<Building> buildings;
	ObservableList<Campus> campuses;
	ObservableList<Classroom> classrooms;
	ObservableList<Course> courses;
	ObservableList<Professor> professors;
	ObservableList<Schedule> schedules;

    @FXML // fx:id="newBuilding"
    private MenuItem newBuilding;

    @FXML // fx:id="newCampus"
    private MenuItem newCampus;

    @FXML // fx:id="newClassroom"
    private MenuItem newClassroom;

    @FXML // fx:id="newCourse"
    private MenuItem newCourse;

    @FXML // fx:id="newProfessor"
    private MenuItem newProfessor;
    
    @FXML // fx:id="buildingList"
    private ListView<Building> buildingList;
    
    @FXML // fx:id="campusList"
    private ListView<Campus> campusList;
    
    @FXML // fx:id="classroomList"
    private ListView<Classroom> classroomList;

    @FXML // fx:id="courseList"
    private ListView<Course> courseList;
    
    @FXML // fx:id="professorList"
    private ListView<Professor> professorList;
    
    @FXML // fx:id="scheduleList"
    private ListView<Schedule> scheduleList;
    
    @FXML
    void addNewBuilding(ActionEvent event) {
    	System.out.println("Create new Building nao!");
    }

    @FXML
    void addNewCampus(ActionEvent event) {
    	System.out.println("Create new Campus nao!");
    }

    @FXML
    void addNewClassroom(ActionEvent event) {
    	System.out.println("Create new classroom nao!");
    }

    @FXML
    void addNewCourse(ActionEvent event) {
    	System.out.println("Create new course nao!");
    }

    @FXML
    void addNewProfessor(ActionEvent event) {
    	System.out.println("Create new professor nao!");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		ArrayList<Professor> profs = Controller.getProfessors();
		
		
		buildings = FXCollections.observableArrayList(Controller.getBuildings());
		campuses = FXCollections.observableArrayList(Controller.getCampuses());
		classrooms = FXCollections.observableArrayList(Controller.getClassrooms());
		courses = FXCollections.observableArrayList(Controller.getCourses());
		professors = FXCollections.observableArrayList(Controller.getProfessors());
		schedules = FXCollections.observableArrayList(Controller.getSchedules());
		
		
		buildingList.setItems(buildings);
		campusList.setItems(campuses);
		classroomList.setItems(classrooms);
		courseList.setItems(courses);
		professorList.setItems(professors);
		scheduleList.setItems(schedules);
	}

}
