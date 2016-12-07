import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.*;
import java.util.*;

public class GUIController implements Initializable {
	private ObservableList<Building> buildings;
	private ObservableList<Campus> campuses;
	private ObservableList<Classroom> classrooms;
	private ObservableList<Course> courses;
	private ObservableList<Professor> professors;
	private ObservableList<Schedule> schedules;
	private static Stage mainStage;
	
	/* 
	 * FXML Menu References
	 * * */
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
    
    /* 
	 * FXML List View References 
	 * * */
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
    
    /* 
	 * Add Functions
	 * * */
    @FXML
    void addNewBuilding(ActionEvent event) {
    	boolean createClicked = createBuildingDialog("Create New Building", null);
    	if (createClicked)
    		refreshBuildings();
    }

    @FXML
    void addNewCampus(ActionEvent event) {
    	boolean createClicked = createCampusDialog("Create New Campus", null);
    	if (createClicked) {
    		refreshCampuses();
    	}
    }

    @FXML
    void addNewClassroom(ActionEvent event) {
    	boolean createClicked = createClassroomDialog("Create New Classroom", null);
    	if (createClicked)
    		refreshClassrooms();
    }	

    @FXML
    void addNewCourse(ActionEvent event) {
    	boolean createClicked = createCourseDialog("Create New Course", null);
    	if (createClicked)
    		refreshCourses();
    }
    
    @FXML
    void addNewProfessor(ActionEvent event) {
    	boolean createClicked = createProfessorDialog("Create New Professor", null);
    	if (createClicked)
    		refreshProfessors();
    }
    
    @FXML
    void addNewSchedule(ActionEvent event) {
    	boolean createClicked = createScheduleDialog("Create New Schedule");
    	if (createClicked)
    		refreshSchedules();
    }
    
    /* 
	 * Edit Functions 
	 * * */
    @FXML 
    void editBuilding(MouseEvent event) {
    	Building building = buildingList.getSelectionModel().getSelectedItem();
    	if (building != null) {
    		boolean createClicked = createBuildingDialog("Edit Building: " + building, building);
    		if (createClicked) {
    			sortBuildings();
    			buildingList.refresh();
    			refreshCampuses();
    		}
    	}
    }
    
    @FXML 
    void editCampus(MouseEvent event) {
    	Campus campus = campusList.getSelectionModel().getSelectedItem();
    	if (campus != null) {
    		boolean createClicked = createCampusDialog("Edit Campus: " + campus, campus);
    		if (createClicked) {
    			sortCampuses();
    			campusList.refresh();
    		}
    	}
    }
    
    @FXML
    void editClassroom(MouseEvent event) {
    	Classroom classroom = classroomList.getSelectionModel().getSelectedItem();
    	if (classroom != null) {
    		boolean createClicked = createClassroomDialog("Edit Classroom: " + classroom, classroom);
    		if (createClicked) {
    			sortClassrooms();
    			classroomList.refresh();
    			refreshBuildings();
    		}
    	}
    }
    
    @FXML
    void editCourse(MouseEvent event) {
		Course course = courseList.getSelectionModel().getSelectedItem();
		if (course != null) {
			boolean createClicked = createCourseDialog("Edit Course: " + course, course);
			if (createClicked) {
				sortCourses();
				courseList.refresh();
			}
		}
    }
    
    @FXML
    void editProfessor(MouseEvent event) {
    	Professor professor = professorList.getSelectionModel().getSelectedItem();
    	if (professor != null) {
    		boolean createClicked = createProfessorDialog("Edit Professor" + professor, professor);
    		if (createClicked) {
    			sortProfessors();
    			professorList.refresh();
    		}
    	}
    }
    
    @FXML
    void editSchedule(MouseEvent event) {
    	Schedule schedule = scheduleList.getSelectionModel().getSelectedItem();
    	if (schedule != null) {
    		scheduleList.getSelectionModel().clearSelection();
    		boolean finishedClicked = createEditScheduleDialog("Edit Schedule: " + schedule, schedule);
    		if (finishedClicked) {
    			sortSchedules();
    			scheduleList.refresh();
    		}
    	}
    }
    
    public static void setMainStage(Stage stage) {
    	mainStage = stage;
    }
    
    
    /* 
     * Dialog Box Creation
     * * */
    private boolean createEditScheduleDialog(String title, Schedule schedule) {
    	if (schedule == null)
    		return false;
    	
    	try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditScheduleTabbedView.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	ScheduleEditorController controller = loader.getController();
			controller.setStage(stage);
	    	controller.setSchedule(schedule);
	    	mainStage.hide();
	    	stage.showAndWait();
	    	refreshSchedules();
	    	mainStage.show();
	    	return controller.isFinishedClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
    private boolean createClassroomDialog(String title, Classroom classroom) {
    	try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditClassroom.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	AddEditClassroomController controller = loader.getController();
			controller.setDialogStage(stage);
			controller.setBuildings(buildings);
	    	if (classroom != null)
	    		controller.setClassroom(classroom);
	    	
	    	stage.showAndWait();
	    	return controller.isCreateClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
    private boolean createBuildingDialog(String title, Building building) {
    	try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditBuilding.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	AddEditBuildingController controller = loader.getController();
			controller.setDialogStage(stage);
			controller.setCampuses(campuses);
	    	if (building != null)
	    		controller.setBuilding(building);
	    	
	    	stage.showAndWait();
	    	return controller.isCreateClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    private boolean createCampusDialog(String title, Campus campus) {
    	try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditCampus.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	AddEditCampusController controller = loader.getController();
			controller.setDialogStage(stage);
	    	if (campus != null)
	    		controller.setCampus(campus);
	    	
	    	stage.showAndWait();
	    	return controller.isCreateClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
    private boolean createScheduleDialog(String title) {
	    try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSchedule.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	AddScheduleController controller = loader.getController();
			controller.setDialogStage(stage);
	    	
	    	stage.showAndWait();
	    	return controller.isCreateClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    	
    }
    
    
    private boolean createCourseDialog(String title, Course course) {
	    try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("addCourseDialog.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	AddEditCourseController controller = loader.getController();
			controller.setDialogStage(stage);
	    	if (course != null)
	    		controller.setCourse(course);
	    	
	    	stage.showAndWait();
	    	return controller.isCreateClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    	
    }
    
    private boolean createProfessorDialog(String title, Professor professor) {
	    try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditProfessor.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	AddEditProfessorController controller = loader.getController();
			controller.setDialogStage(stage);
	    	if (professor != null)
	    		controller.setProfessor(professor);
	    	
	    	stage.showAndWait();
	    	return controller.isCreateClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//assets
		initBuildings();
		initCampuses();
		initClassrooms();
		initCourses();
		initProfessors();
		initSchedules();
	}
	
	/*
	 * Refresh Functions
	 * These are used to refresh the model and view.
	 * * */
	public void refreshCourses() {
		initCourses();
		courseList.refresh();
	}
	
	public void refreshBuildings() {
		initBuildings();
		courseList.refresh();
		refreshCampuses();
	}
	public void refreshCampuses() {
		initCampuses();
		campusList.refresh();
	}
	
	public void refreshClassrooms() {
		initClassrooms();
		classroomList.refresh();
		refreshBuildings();
	}
	
	public void refreshProfessors() {
		initProfessors();
		professorList.refresh();
	}
	
	public void refreshSchedules() {
		initSchedules();
		scheduleList.refresh();
	}
	
	public void refreshLocations() {
		//calling refresh on classrooms will bubble up and refresh them all.
		refreshClassrooms();
	}
	
	/* 
	 * Helper Functions
	 * * */
	//courses
	private void sortCourses() {
		Collections.sort(courses, (course1, course2) -> {
			return course1.getCourseNumber().compareTo(course2.getCourseNumber());
		});
	}
	
	private void initCourses() {
		courseList.setCellFactory((ListView<Course> list) -> {
			return new DeletableListCell<Course>(this);
		});
		courses = FXCollections.observableArrayList(Controller.getCourses());
		sortCourses();
		courseList.setItems(courses);
	}
	
	//buildings
	private void sortBuildings() {
		Collections.sort(buildings, (building1, building2) -> {
			return building1.getTitle().compareTo(building2.getTitle());
		});
	}
	
	private void initBuildings() {
		buildingList.setCellFactory((ListView<Building> list) -> {
			return new DeletableListCell<Building>(this);
		});
		buildings = FXCollections.observableArrayList(Controller.getBuildings());
		sortBuildings();
		buildingList.setItems(buildings);
	}
	
	//campuses
	private void sortCampuses() {
		Collections.sort(campuses, (campus1, campus2) -> {
			return campus1.toString().compareTo(campus2.toString());
		});
	}
	
	private void initCampuses() {
		campusList.setCellFactory((ListView<Campus> list) -> {
			return new DeletableListCell<Campus>(this);
		});
		campuses = FXCollections.observableArrayList(Controller.getCampuses());
		sortCampuses();
		campusList.setItems(campuses);
	}
	
	//classrooms
	private void sortClassrooms() {
		Collections.sort(classrooms, (classroom1, classroom2) -> {
			return classroom1.toString().compareTo(classroom2.toString());
		});
	}
	
	private void initClassrooms() {
		classroomList.setCellFactory((ListView<Classroom> list) -> {
			return new DeletableListCell<Classroom>(this);
		});
		classrooms = FXCollections.observableArrayList(Controller.getClassrooms());
		sortClassrooms();
		classroomList.setItems(classrooms);
	}
	
	//professors
	private void sortProfessors() {
		Collections.sort(professors, (professor1, professor2) -> {
			return professor1.toString().compareTo(professor2.toString());
		});
	}
	
	private void initProfessors() {
		professorList.setCellFactory((ListView<Professor> list) -> {
			return new DeletableListCell<Professor>(this);
		});
		professors = FXCollections.observableArrayList(Controller.getProfessors());
		sortProfessors();
		professorList.setItems(professors);
	}
	
	//schedules
	private void sortSchedules() {
		Collections.sort(schedules, Collections.reverseOrder((schedule1, schedule2) -> {
			return schedule1.toString().compareTo(schedule2.toString());
		}));
	}
	
	private void initSchedules() {
		scheduleList.setCellFactory((ListView<Schedule> list) -> {
			return new DeletableListCell<Schedule>(this);
		});
		schedules = FXCollections.observableArrayList(Controller.getSchedules());
		sortSchedules();
		scheduleList.setItems(schedules);
	}

}
