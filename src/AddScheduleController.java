
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddScheduleController implements Initializable {
	private Stage stage;
	private boolean isCreateClicked = false;
	private ObservableList<String> semesters;

	
    @FXML // fx:id="semesterPicker"
    private ChoiceBox<String> semesterPicker;

    @FXML // fx:id="yearSpinner"
    private Spinner<Integer> yearSpinner;

    @FXML
    void handleCancel(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void handleCreate(ActionEvent event) {
    	if(isInputValid()){
    		Controller.insertSchedule(semesterPicker.getValue(), yearSpinner.getValue().toString());
    		isCreateClicked = true;
    		stage.close();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle res) {
		
		//set the available semesters
		semesters = FXCollections.observableArrayList("Fall", "Spring", "Summer");
		
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR);
		
		yearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  Integer.MAX_VALUE, 0));
		yearSpinner.getValueFactory().setValue(year);
		yearSpinner.setEditable(true);
		
		semesterPicker.setItems(semesters);
	}
	
	public void setDialogStage(Stage stage){
		this.stage = stage;
	}

	public boolean isCreateClicked() {
		return isCreateClicked;
	}
	
	private boolean isInputValid() {
		
		String errMsg = "";
		
		if(semesterPicker.getSelectionModel().isEmpty()){
			errMsg += "Semester must be selected";
		}
    	
    	//If there are errors, alert user.
    	if(errMsg.length() != 0) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(stage);
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
