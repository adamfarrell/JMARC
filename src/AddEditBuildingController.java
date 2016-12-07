import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddEditBuildingController implements Initializable {
	private Stage dialogStage;
	private Building building;
	private ObservableList<Campus> campuses;
	private boolean createClicked = false;
	
	@FXML // fx:id="name"
    private TextField name;

    @FXML // fx:id="code"
    private TextField code;

    @FXML // fx:id="campusPicker"
    private ChoiceBox<Campus> campusPicker;

    @FXML // fx:id="createBtn"
    private Button createBtn;
	
	@FXML
	void handleCreate(ActionEvent event) {
		if (isInputValid()) {
			if (building != null) {
				building.setBuildingCode(code.getText());
				building.setTitle(name.getText());
				building.setCampusID(campusPicker.getValue().getPrimaryKey());
				Controller.updateBuilding(building);
			} else {
				Controller.insertBuilding(campusPicker.getValue().getPrimaryKey(), name.getText(), code.getText());
			}
			createClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	void handleCancel(ActionEvent event) {
		dialogStage.close();
	}
	
	public boolean isCreateClicked() {
		return createClicked;
	}
	
	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
	
	public void setCampuses(ObservableList<Campus> campuses) {
		this.campuses = campuses;
		campusPicker.setItems(campuses);
	}
	
	public void setBuilding(Building building) {
		Campus buildingCampus = Controller.getSingleCampus(building.getCampusID());
		this.building = building;

		name.setText(building.getTitle());
		code.setText(building.getBuildingCode());
		campusPicker.getSelectionModel().select(buildingCampus);
		createBtn.setText("Update");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	/* 
	 * Helper Functions
	 * * */
	private boolean isValidString(String string) {
		if (string == null || string.length() == 0 || string.length() > 50)
			return false;
		else
			return true;
	}
	
	private boolean isInputValid() {
    	String title 	= name.getText(),
    		   bCode	= code.getText(),
    		   errMsg	= "";
    	
    	//check for errors
    	if (!isValidString(title))
    		errMsg += "Campus Name is not valid\n";
    	if (!isValidString(bCode))
    		errMsg += "City is not valid\n";
    	if (campusPicker.getSelectionModel().isEmpty())
    		errMsg += "Must select the campus where this building is located\n";
    	
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
