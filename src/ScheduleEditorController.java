import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

public class ScheduleEditorController implements Initializable {
	private Schedule schedule;
	private Stage mainStage;
	private ObservableList<String> semesters;
	private boolean finishedClicked = false;
	private ObservableList<Section> sections;

    @FXML // fx:id="semesterPicker"
    private ChoiceBox<String> semesterPicker;

    @FXML // fx:id="yearSpinner"
    private Spinner<Integer> yearSpinner;
    
    @FXML // fx:id="sectionsList"
    private ListView<Section> sectionsList;

    @FXML
    void updateSchedule(ActionEvent event) {
    	if (isInputValid("schedule")) {
    		schedule.setSemester(semesterPicker.getValue());
    		schedule.setYear(yearSpinner.getValue());
    		Controller.updateSchedule(schedule);
    	}
    }
    
    @FXML
    void exportSchedule() {
    	try {
    		String result = Controller.exportSchedule(schedule.getPrimaryKey());
    		Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Export Success");
            alert.setHeaderText("Your schedule was successfully exported.");
            alert.setContentText("It was saved at: " + result);
            alert.showAndWait();
    		
    	} catch(IOException ex) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainStage);
            alert.setTitle("Error Occurred");
            alert.setHeaderText("There was an error exporting your schedule.");
            alert.showAndWait();
    		ex.printStackTrace();
    	}
    }
    
    public boolean isFinishedClicked() {
    	return finishedClicked;
    }
    
    public void setSchedule(Schedule schedule) {
    	this.schedule = schedule;
    	semesterPicker.getSelectionModel().select(schedule.getSemester());
    	yearSpinner.getValueFactory().setValue(Integer.valueOf(schedule.getYear()));
    	initSections();
    }
    
    public void setStage(Stage stage) {
    	this.mainStage = stage;
    }

    @FXML
    void addNewSection(ActionEvent event) {
    	boolean createClicked = createSectionDialog("Create New Schedule", null);
    	if (createClicked)
    		refreshSections();
    }
    
    /* 
	 * Edit Functions 
	 * * */
    @FXML 
    void editSection(MouseEvent event) {
    	Section section = sectionsList.getSelectionModel().getSelectedItem();
    	sectionsList.getSelectionModel().clearSelection();
    	if (section != null) {
    		
    		boolean createClicked = createSectionDialog("Edit Section: " + section, section);
    		if (createClicked) {
    			sortSections();
    			sectionsList.refresh();
    		}
    	}
    }
    
    /* 
     * Create Dialogs
     * * */
    private boolean createSectionDialog(String title, Section section) {
	    try {
	    	//load
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditSection.fxml"));
	    	AnchorPane content = (AnchorPane) loader.load();
	    	
	    	//create dialog scene
	    	Stage stage = new Stage();
	    	stage.setTitle(title);
	    	stage.initModality(Modality.WINDOW_MODAL);
	    	stage.initOwner(mainStage);
	    	Scene scene = new Scene(content);
	    	stage.setScene(scene);
	    	
	    	//set up for editing if necessary
	    	AddEditSectionController controller = loader.getController();
	    	controller.setSchedule(schedule);
			controller.setDialogStage(stage);
	    	if (section != null)
	    		controller.setSection(section);
	    	
	    	stage.showAndWait();
	    	return controller.isCreateClicked();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		semesters = FXCollections.observableArrayList("Fall", "Spring", "Summer");
		semesterPicker.setItems(semesters);
		
		SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  Integer.MAX_VALUE, 0);
		yearSpinner.setValueFactory(factory);
		yearSpinner.setEditable(true);
		TextFormatter<Integer> formatter = new TextFormatter<Integer>(factory.getConverter(), factory.getValue());
		yearSpinner.getEditor().setTextFormatter(formatter);
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
	}
	
	private boolean isInputValid(String objToValidate) {
		String errMsg = "";
		switch (objToValidate) {
			case "schedule":
				if(semesterPicker.getSelectionModel().isEmpty())
					errMsg += "Semester must be selected";
				break;
			case "section":
				break;
		}
		
    	//If there are errors, alert user.
    	if(errMsg.length() != 0) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMsg);

            alert.showAndWait();

            return false;
    	} else {
    		return true;
    	}
	}
	
	public void refreshSections() {
		initSections();
		sectionsList.refresh();
	}
	
	
	/* 
	 * Helper Function
	 * * * */
	
	private void sortSections() {
		Collections.sort(sections, (section1, section2) -> {
			return section1.toString().compareTo(section2.toString());
		});
	}
	
	private void initSections() {
		sectionsList.setCellFactory((ListView<Section> list) -> {
			return new DeletableListCell<Section>(this);
		});
		sections = FXCollections.observableArrayList(Controller.getScheduleSections(schedule.getPrimaryKey()));
		sortSections();
		sectionsList.setItems(sections);
	}
}