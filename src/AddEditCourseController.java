import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.Alert.*;

public class AddEditCourseController implements Initializable {
	
	private Stage dialogStage;
    private Course course;
    private boolean createClicked = false;
	
	@FXML // fx:id="coursePrefix"
    private TextField coursePrefix; 

    @FXML // fx:id="courseNumber"
    private TextField courseNumber; 

    @FXML // fx:id="creditHours"
    private Spinner<Integer> creditHours; 

    @FXML // fx:id="courseTitle"
    private TextField courseTitle; 

    @FXML // fx:id="courseDescription"
    private TextArea courseDescription; 
    
    @FXML // fx:id="createBtn"
    private Button createBtn;
	
    public void setDialogStage(Stage dialogStage) {
    	this.dialogStage = dialogStage;
    }
    
    public void setCourse(Course course) {
    	this.course = course;
    	
    	coursePrefix.setText(course.getPrefix());
    	courseNumber.setText(course.getCourseNumber());
    	creditHours.getValueFactory().setValue(Integer.parseInt(course.getCreditHours()));
    	courseTitle.setText(course.getTitle());
    	courseDescription.setText(course.getDescription());
    	createBtn.setText("Update");
    }
    
    public boolean isCreateClicked() {
    	return createClicked;
    }
    
    @FXML
    void handleCancel(ActionEvent event) {
    	dialogStage.close();
    }

    @FXML
    void handleCreate(ActionEvent event) {
    	if (isInputValid()) {
    		if (course != null) {
    			course.changePrefix(coursePrefix.getText());
    			course.changeCourseNumber(courseNumber.getText());
    			course.changeTitle(courseTitle.getText());
    			course.changeDescription(courseDescription.getText());
    			course.setCreditHours(creditHours.getValue().toString());
    			Controller.updateCourse(course);
    		} else {
//    			System.out.println("Create Course: " + coursePrefix.getText() + " " + courseNumber.getText() + " " + courseTitle.getText());
    			Controller.insertCourse(courseTitle.getText(), creditHours.getValue().toString(), coursePrefix.getText(), 
    					courseNumber.getText(), courseDescription.getText());
    		}
    		createClicked = true;
    		dialogStage.close();
    	}
    }
    
    private boolean isInputValid() {
    	String prefix 	= coursePrefix.getText(),
    		   number	= courseNumber.getText(),
    		   title	= courseTitle.getText(),
    		   errMsg	= "";
    	int hours = creditHours.getValueFactory().getValue();
    	
    	//check for errors
    	if (prefix == null || prefix.length() == 0 || prefix.length() >= 6)
    		errMsg += "Course Prefix not valid!\n";
    	if (number == null || number.length() == 0 || !isValidInt(number))
    		errMsg += "Course Number not valid!\n";
    	if (title == null || title.length() == 0)
    		errMsg += "Course Title is not valid!\n";
    	if (hours < 0 || hours >= 20)
    		errMsg += "Credit Hours are invalid!\n";
    	
    	//If there are errors, alert user.
    	if (errMsg.length() == 0) {
    		return true;
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMsg);

            alert.showAndWait();

            return false;
    	}
    }
    
    private boolean isValidInt(String number) {
    	try {
    		Integer.parseInt(number);
    	} catch(NumberFormatException e) {
    		return false;
    	}
    	
    	return true;
    }
    
	public void initialize(URL location, ResourceBundle resources) {
		SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,  20, 0);
		creditHours.setValueFactory(factory);
		creditHours.getValueFactory().setValue(4);
		creditHours.setEditable(true);
		TextFormatter<Integer> formatter = new TextFormatter<Integer>(factory.getConverter(), factory.getValue());
		creditHours.getEditor().setTextFormatter(formatter);
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
	}

}
