import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddEditClassroomController implements Initializable {
	private Classroom classroom;
	private Stage dialogStage;
	private boolean createClicked;
	private ObservableList<Building> buildings;
	
	@FXML // fx:id="roomCapacity"
    private Spinner<Integer> roomCapacity;

    @FXML // fx:id="numComputers"
    private Spinner<Integer> numComputers;

    @FXML // fx:id="createBtn"
    private Button createBtn;

    @FXML // fx:id="roomNumber"
    private TextField roomNumber;

    @FXML // fx:id="buildingPicker"
    private ChoiceBox<Building> buildingPicker;

    @FXML
    void handleCancel(ActionEvent event) {
    	dialogStage.close();
    }

    @FXML
    void handleCreate(ActionEvent event) {
    	if (isInputValid()) {
    		if (classroom != null) {
    			classroom.setRoomNumber(roomNumber.getText());
    			classroom.setCapacity(roomCapacity.getValue());
    			classroom.setNumOfComps(numComputers.getValue());
    			classroom.setBuildingID(buildingPicker.getValue().getPrimaryKey());
    			classroom.setBuildingTag(buildingPicker.getValue().getBuildingCode());
    			Controller.updateClassroom(classroom);
    		} else {
    			Controller.insertClassroom(roomNumber.getText(), buildingPicker.getValue().getPrimaryKey(), roomCapacity.getValue(), numComputers.getValue());
    		}
    		
    		createClicked = true;
    		dialogStage.close();
    	}
    }
	
	public void setClassroom(Classroom classroom) {
		Building building = Controller.getSingleBuilding(classroom.getBuildingID());
		this.classroom = classroom;
		
		roomNumber.setText(classroom.getRoomNumber());
		roomCapacity.getValueFactory().setValue(Integer.valueOf(classroom.getCapacity()));
		numComputers.getValueFactory().setValue(Integer.valueOf(classroom.getNumOfComps()));
		buildingPicker.getSelectionModel().select(building);
		createBtn.setText("Update");
	}
	
	public void setBuildings(ObservableList<Building> buildings) {
		this.buildings = buildings;
		buildingPicker.setItems(this.buildings);
	}
	
	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
	
	public boolean isCreateClicked() {
		return createClicked;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SpinnerValueFactory<Integer> factory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  1000, 0);
		roomCapacity.setValueFactory(factory1);
		roomCapacity.getValueFactory().setValue(0);
		roomCapacity.setEditable(true);
		TextFormatter<Integer> formatter1 = new TextFormatter<Integer>(factory1.getConverter(), factory1.getValue());
		roomCapacity.getEditor().setTextFormatter(formatter1);
		factory1.valueProperty().bindBidirectional(formatter1.valueProperty());
		
		SpinnerValueFactory<Integer> factory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  1000, 0);
		numComputers.setValueFactory(factory2);
		numComputers.getValueFactory().setValue(0);
		numComputers.setEditable(true);
		TextFormatter<Integer> formatter2 = new TextFormatter<Integer>(factory2.getConverter(), factory2.getValue());
		numComputers.getEditor().setTextFormatter(formatter2);
		factory2.valueProperty().bindBidirectional(formatter2.valueProperty());
		
		
		
		
	}
	
	/* 
	 * Helper Methods
	 * * */
	private boolean isValidInt(int number) {
		if (number < 0 || number > 1000)
			return false;
		else
			return true;
	}
	
	private boolean isValidString(String string) {
		if (string == null || string.length() == 0 || string.length() > 10)
			return false;
		else
			return true;
	}
	
	private boolean isInputValid() {
    	String roomNum 	= roomNumber.getText(),
    		   errMsg	= "";
    	int capacity 	= roomCapacity.getValueFactory().getValue(),
    		computers	= numComputers.getValueFactory().getValue();
    	
    	//check for errors
    	if (!isValidString(roomNum))
    		errMsg += "Room Number is not valid\n";
    	if (!isValidInt(capacity))
    		errMsg += "Capacity is not valid\n";
    	if (!isValidInt(computers))
    		errMsg += "Number of Computers is not valid\n";
    	if (buildingPicker.getSelectionModel().isEmpty())
    		errMsg += "Must select the building this classroom is located in.\n";
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
