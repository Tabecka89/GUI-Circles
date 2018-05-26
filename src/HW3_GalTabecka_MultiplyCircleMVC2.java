// Gal Tabecka 201668001
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HW3_GalTabecka_MultiplyCircleMVC2 extends Application {
	
	// Attributes for the main stage.
	private Button btControllerView = new Button(iFinals.CONTROLLER_VIEW_BUTTON);
	private ArrayList<CircleController2> circleControllerList;
	private ArrayList<CircleView2> circleViewList;
	private ArrayList<CircleModel2> circleModelList;
	private int circleCounter = 0;

	@Override
	public void start(Stage primaryStage) {
		
		//Initializing the ArrayLists and constructing the main stage.
		circleControllerList = new ArrayList<CircleController2>();
		circleViewList = new ArrayList<CircleView2>();
		circleModelList = new ArrayList<CircleModel2>();
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
		circleModelList.add(new CircleModel2(circleCounterAsProperty));
		circleControllerList.add(new CircleController2(circleCounter));
		circleControllerList.get(circleCounter).setModel(circleModelList.get(circleCounter));
		circleViewList.add(new CircleView2(circleCounter, circleModelList.get(circleCounter)));
		circleCounter++;
	}

	// A method to set the stage for on screen appearance.
	private void readyStageForAppearance(Stage primaryStage) {
		primaryStage.setTitle(iFinals.MULTIPLE1_MAIN_TITLE);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setOnCloseRequest(e -> Platform.exit());
		primaryStage.setX(iFinals.MAIN_X_COORDINATE);
		primaryStage.setY(iFinals.MAIN_X_COORDINATE);
		primaryStage.setResizable(false);
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
