// Gal Tabecka 201668001
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HW3_GalTabecka_MultiplyCircleMVC3 extends Application {
	
	// Attributes for the main stage.
	private Button btControllerView = new Button(iFinals.CONTROLLER_VIEW_BUTTON);
	private CircleController3 circleController;
	private CircleModel3 circleModel;
	private int circleCounter = 0;

	@Override
	public void start(Stage primaryStage) {
		// Constructing the main stage.
		FlowPane pane = new FlowPane();
		pane.getChildren().add(btControllerView);
		pane.setAlignment(Pos.CENTER);

		// Handle event using Lambda Expression.
		btControllerView.setOnAction(e -> showViewAndControllerStage());

		Scene scene = new Scene(pane, iFinals.MAIN_SCENE_WIDTH, iFinals.MAIN_SCENE_HEIGHT);
		primaryStage.setScene(scene);
		readyStageForAppearance(primaryStage);
	}

	// A method used to create and display a circle view and controller.
	private void showViewAndControllerStage() {
		IntegerProperty circleCounterAsProperty = new SimpleIntegerProperty(circleCounter);
		circleModel = new CircleModel3(circleCounterAsProperty);
		circleController = new CircleController3(circleCounter);
		new CircleView3(circleCounter, circleModel);
		circleController.setModel(circleModel);
		circleCounter++;
	}

	// A method to set the stage for on screen appearance.
	private void readyStageForAppearance(Stage primaryStage) {
		primaryStage.setTitle(iFinals.MULTIPLE2_MAIN_TITLE);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setOnCloseRequest(e -> Platform.exit());
		primaryStage.setX(iFinals.MAIN_X_COORDINATE);
		primaryStage.setY(iFinals.MAIN_Y_COORDINATE);
		primaryStage.setResizable(false);
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
