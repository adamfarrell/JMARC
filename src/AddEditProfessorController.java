import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddEditProfessorController implements Initializable {

	private Stage dialogStage;
    private Professor professor;
    private boolean createClicked = false;
	
    @FXML // fx:id="firstName"
    private TextField firstName; 

    @FXML // fx:id="lastName"
    private TextField lastName; 

    @FXML // fx:id="status"
    private TextField status; 

    @FXML // fx:id="creditHours"
    private Spinner<Integer> creditHours; 

    @FXML // fx:id="releaseHours"
    private Spinner<Integer> releaseHours; 

    @FXML // fx:id="createBtn"
    private Button createBtn; 

    @FXML
    void handleCancel(ActionEvent event) {
    	dialogStage.close();
    }

    @FXML
    void handleCreate(ActionEvent event) {
    	if (isInputValid()) {
    		if (professor != null) {
    			professor.changeFirstName(firstName.getText());
    			professor.changeLastName(lastName.getText());
    			professor.changeStatus(status.getText());
    			professor.changeCreditHours(creditHours.getValue());
    			professor.changeReleaseHours(releaseHours.getValue());
    			Controller.updateProfessor(professor);
    		} else {
    			Controller.insertProfessor(firstName.getText(), lastName.getText(), status.getText(), 
    					creditHours.getValue().toString(), releaseHours.getValue().toString());
    		}
    		createClicked = true;
    		dialogStage.close();
    	}
    }
    
    public void setDialogStage(Stage dialogStage) {
    	this.dialogStage = dialogStage;
    }
    
    public boolean isCreateClicked() {
    	return createClicked;
    }
    
    public void setProfessor(Professor professor) {
    	this.professor = professor;
    	
    	firstName.setText(professor.getFirstName());
    	lastName.setText(professor.getLastName());
    	status.setText(professor.getStatus());
    	creditHours.getValueFactory().setValue(professor.getCreditHours());
    	releaseHours.getValueFactory().setValue(professor.getReleaseHours());
    	createBtn.setText("Update");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		SpinnerValueFactory<Integer> factory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  20, 0);
		creditHours.setValueFactory(factory1);
		creditHours.getValueFactory().setValue(12);
		creditHours.setEditable(true);
		TextFormatter<Integer> formatter1 = new TextFormatter<Integer>(factory1.getConverter(), factory1.getValue());
		creditHours.getEditor().setTextFormatter(formatter1);
		factory1.valueProperty().bindBidirectional(formatter1.valueProperty());
		
		SpinnerValueFactory<Integer> factory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  20, 0);
		releaseHours.setValueFactory(factory2);
		releaseHours.getValueFactory().setValue(0);
		releaseHours.setEditable(true);
		TextFormatter<Integer> formatter2 = new TextFormatter<Integer>(factory2.getConverter(), factory2.getValue());
		releaseHours.getEditor().setTextFormatter(formatter2);
		factory2.valueProperty().bindBidirectional(formatter2.valueProperty());
	}
	
	/* 
	 * Helper Methods
	 * * */
	private boolean isValidInt(int number) {
		if (number < 0 || number > 20)
			return false;
		else
			return true;
	}
	
	private boolean isValidString(String string) {
		if (string == null || string.length() == 0 || string.length() > 50)
			return false;
		else
			return true;
	}
	
	private boolean isInputValid() {
    	String fName 	= firstName.getText(),
    		   lName	= lastName.getText(),
    		   standing	= status.getText(),
    		   errMsg	= "";
    	int credit = creditHours.getValueFactory().getValue(),
    		filler = releaseHours.getValueFactory().getValue();
    	
    	//check for errors
    	if (!isValidString(fName))
    		errMsg += "First Name not valid\n";
    	if (!isValidString(lName))
    		errMsg += "Last Name not valid\n";
    	if (!isValidString(standing))
    		errMsg += "Professor Status is not valid\n";
    	if (!isValidInt(credit))
    		errMsg += "Credit Hours is not valid\n";
    	if (!isValidInt(filler))
    		errMsg += "Filler Hours is not valid\n";
    	
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