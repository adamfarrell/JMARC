import javafx.scene.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

public class MainGUI extends Application {
	private FXMLLoader loader;
	public MainGUI() {}
	public MainGUI(String[] args) {
		launchGUI(args);
	}
	private static void launchGUI(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Set up main window
		loader = new FXMLLoader(getClass().getResource("MainGUI.fxml"));
		BorderPane borderPane = loader.load();
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle("JMARC Super Scheduler");
		stage.show();
		GUIController.setMainStage(stage);
	}
}
