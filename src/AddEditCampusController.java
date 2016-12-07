import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddEditCampusController implements Initializable {
	private Stage dialogStage;
	private Campus campus;
	private boolean createClicked = false;
	
    @FXML // fx:id="name"
    private TextField name;

    @FXML // fx:id="city"
    private TextField city;

    @FXML // fx:id="createBtn"
    private Button createBtn;

    @FXML
    void handleCancel(ActionEvent event) {
    	dialogStage.close();
    }

    @FXML
    void handleCreate(ActionEvent event) {
    	if(isInputValid()) {
    		if(campus != null) {
    			campus.changeName(name.getText());
    			campus.changeAddress(city.getText());
    			Controller.updateCampus(campus);
    		} else {
    			Controller.insertCampus(name.getText(), city.getText());
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
    
    public void setCampus(Campus campus) {
    	this.campus = campus;
    	name.setText(campus.getName());
    	city.setText(campus.getAddress());
    	createBtn.setText("Update");
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {}
    
    /* 
	 * Helper Methods
	 * * */
	private boolean isValidString(String string) {
		if (string == null || string.length() == 0 || string.length() > 50)
			return false;
		else
			return true;
	}
	
	private boolean isInputValid() {
    	String title 	= name.getText(),
    		   address	= city.getText(),
    		   errMsg	= "";
    	
    	//check for errors
    	if (!isValidString(title))
    		errMsg += "Campus Name is not valid\n";
    	if (!isValidString(address))
    		errMsg += "City is not valid\n";
    	
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
