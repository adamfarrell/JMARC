/**
 * Sample Skeleton for 'AddEditSection.fxml' Controller Class
 */

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.FXCollections.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddEditSectionController implements Initializable {
	private Stage dialogStage;
	private Section section;
	private Schedule schedule;
	private boolean createClicked = false;
	private ObservableList<Course> courses;
	private ObservableList<Classroom> classrooms;
	private ObservableList<Professor> professors;
	
    @FXML // fx:id="coursePicker"
    private ChoiceBox<Course> coursePicker; 

    @FXML // fx:id="classroomPicker"
    private ChoiceBox<Classroom> classroomPicker; 

    @FXML // fx:id="professorPicker"
    private ChoiceBox<Professor> professorPicker; 

    @FXML // fx:id="numSeatsSlider"
    private Spinner<Integer> numSeatsSlider; 

    @FXML // fx:id="daysOfWeek"
    private TextField daysOfWeek; 

    @FXML // fx:id="startDate"
    private DatePicker startDate; 

    @FXML // fx:id="endDate"
    private DatePicker endDate; 

    @FXML // fx:id="startTime"
    private TextField startTime; 

    @FXML // fx:id="endTime"
    private TextField endTime; 

    @FXML // fx:id="createBtn"
    private Button createBtn; 

    @FXML
    void handleCancel(ActionEvent event) {
    	dialogStage.close();
    }

    @FXML
    void handleCreate(ActionEvent event) {
    	if (isInputValid()) {
    		if (section != null) {
				section.setClassroom(classroomPicker.getSelectionModel().getSelectedItem());
				section.setProfessor(professorPicker.getSelectionModel().getSelectedItem()); 
				section.setCourse(coursePicker.getSelectionModel().getSelectedItem());
				section.setStartTime(startTime.getText());
				section.setEndTime(endTime.getText());
				section.setStartDate(startDate.getValue().toString()); 
				section.setEndDate(endDate.getValue().toString());
				section.setNumSeats(numSeatsSlider.getValue().toString()); 
				section.setDaysOfWeek(daysOfWeek.getText());
				Controller.updateSection(section);
    		} else {
    			Controller.insertSection(classroomPicker.getSelectionModel().getSelectedItem(), 
    									 professorPicker.getSelectionModel().getSelectedItem(), 
    									 coursePicker.getSelectionModel().getSelectedItem(), 
    									 schedule.getPrimaryKey(), 
    									 startTime.getText(), 
    									 endTime.getText(), 
    									 startDate.getValue().toString(), 
    									 endDate.getValue().toString(), 
    									 numSeatsSlider.getValue().toString(), 
    									 daysOfWeek.getText());
    		}
    		createClicked = true;
    		dialogStage.close();
    	}
    }
    
    public void setSection(Section section) {
    	this.section = section;
    	
    	coursePicker.getSelectionModel().select(section.getCourse());
    	classroomPicker.getSelectionModel().select(section.getClassroom());
    	professorPicker.getSelectionModel().select(section.getProfessor());
    	numSeatsSlider.getValueFactory().setValue(Integer.valueOf(section.getNumSeats()));
    	daysOfWeek.setText(section.getDaysOfWeek());
    	startDate.setValue(LocalDate.parse(section.getStartDate()));
    	endDate.setValue(LocalDate.parse(section.getEndDate()));
    	startTime.setText(section.getStartTime());
    	endTime.setText(section.getEndTime());
    	createBtn.setText("Update");
    }
    
    public void setSchedule(Schedule schedule) {
    	this.schedule = schedule;
    }
    
    public boolean isCreateClicked() {
    	return createClicked;
    }
    
    public void setDialogStage(Stage stage) {
    	dialogStage = stage;
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courses = FXCollections.observableArrayList(Controller.getCourses());
		classrooms = FXCollections.observableArrayList(Controller.getClassrooms());
		professors = FXCollections.observableArrayList(Controller.getProfessors());
		coursePicker.setItems(courses);
		classroomPicker.setItems(classrooms);
		professorPicker.setItems(professors);
		
		SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  Integer.MAX_VALUE, 0);
		numSeatsSlider.setValueFactory(factory);
		numSeatsSlider.setEditable(true);
		TextFormatter<Integer> formatter = new TextFormatter<Integer>(factory.getConverter(), factory.getValue());
		numSeatsSlider.getEditor().setTextFormatter(formatter);
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
	}
	
	/* 
	 * Helper Methods
	 * * */
	private boolean isValidString(String string) {
		if (string == null || string.length() == 0 || string.length() > 14)
			return false;
		else
			return true;
	}
	
	private boolean isInputValid() {
    	String sTime 	= startTime.getText(),
    		   eTime	= endTime.getText(),
    		   sDate	= startDate.getValue().toString(),
    		   eDate	= endDate.getValue().toString(),
    		   week 	= daysOfWeek.getText(),
    		   errMsg	= "";
    	Classroom classroom = classroomPicker.getSelectionModel().getSelectedItem();
    		
    	//check for errors
    	if (!isValidString(sTime))
    		errMsg += "Start Time is not valid\n";
    	if (!isValidString(eTime))
    		errMsg += "End Time is not valid\n";
    	if (!isValidString(sDate))
    		errMsg += "Start Date is not valid\n";
    	if (!isValidString(eDate))
    		errMsg += "End Date is not valid\n";
    	if (!isValidString(week))
    		errMsg += "Week is not valid\n";
    	if (numSeatsSlider.getValue() < 0)
    		errMsg += "Number of seats is not valid\n";
    	if (coursePicker.getSelectionModel().isEmpty())
    		errMsg += "Must select a classroom\n";
    		
    	if (professorPicker.getSelectionModel().isEmpty())
    		errMsg += "Must select a professor\n";
    	if (classroomPicker.getSelectionModel().isEmpty())
    		errMsg += "Must select a classroom!\n";
		if (numSeatsSlider.getValue() > Integer.valueOf(classroom.getCapacity()))
			errMsg += "Section number of seats must not be greater than the number of seats in the classroom\n";
    	System.out.println(numSeatsSlider.getValue() + ">" + Integer.valueOf(classroom.getCapacity()));
    	//If there are errors, alert user.
    	if(errMsg.length() != 0) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMsg);

            alert.showAndWait();

            return false;
    	} else {
    		return true;
    	}
    }

}
