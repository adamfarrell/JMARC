import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import java.util.Optional;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DeletableListCell<T> extends ListCell<T> {
	private static GUIController controller;
	private static ScheduleEditorController altController;
	private String classType;
	public DeletableListCell() {
		super();
	}
	
	public DeletableListCell(GUIController controller) {
		super();
		DeletableListCell.controller = controller;
	}
	
	public DeletableListCell(ScheduleEditorController controller) {
		super();
		DeletableListCell.altController = controller;
	}
	
	@Override
	public void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item.toString());
			classType = item.getClass().toString().replace("class ", "");
			
			Button delete = new Button();
			delete.setText("Delete");
			delete.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
			delete.setTextFill(Color.DARKRED);
			delete.setOnAction((ActionEvent event) -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setHeaderText("Are you sure you want to delete the " + classType + " " + item + "?");
		    	alert.setTitle("Delete " + classType + ": " + item);
		    	alert.getButtonTypes().remove(0, 2);
		    	alert.getButtonTypes().add(0, ButtonType.YES);
		    	alert.getButtonTypes().add(1, ButtonType.NO);
		    	Optional<ButtonType> confirmationResponse = alert.showAndWait();
		    	if(confirmationResponse.get() == ButtonType.YES) {
		    		switch(classType) {
			    		case "Building":
			    			Controller.deleteBuilding((Building) item);
			    			controller.refreshLocations();
			    			break;
			    		case "Campus":
			    			Controller.deleteCampus((Campus) item);
			    			controller.refreshLocations();
			    			break;
			    		case "Classroom":
			    			Controller.deleteClassroom((Classroom) item);
			    			controller.refreshLocations();
			    			break;
			    		case "Course":
			    			Controller.deleteCourse((Course) item);
			    			controller.refreshCourses();
			    			break;
			    		case "Professor":
			    			Controller.deleteProfessor((Professor) item);
			    			controller.refreshProfessors();
			    			break;
			    		case "Schedule":
			    			Controller.deleteSchedule((Schedule) item);
			    			controller.refreshSchedules();
			    			break;
			    		case "Section":
			    			Controller.deleteSection((Section) item);
			    			altController.refreshSections();
			    			break;
			    		default:
			    			break;
		    		}
		    	}
			});
			setGraphic(delete);
		}
	}
}
