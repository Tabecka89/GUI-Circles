// Gal Tabecka 201668001
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HW3_GalTabecka_SingleCircleMVC1 extends Application {
	
	// A model attribute to be used later.
	private CircleModel1 model = new CircleModel1();
	
	@Override
	public void start(Stage primaryStage) {
		// Constructing the main stage.
		BorderPane pane = new BorderPane();
		Button btController = new Button(iFinals.CONTROLLER_BUTTON);
		Button btView = new Button(iFinals.VIEW_BUTTON);
		HBox hBox = getHBox();
		hBox.getChildren().add(btController);
		hBox.getChildren().add(btView);
		pane.setCenter(hBox);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		readyStageForAppearance(primaryStage);

		// Handling events using Lambda Expression.
		btController.setOnAction(e -> showControllerStage());
		btView.setOnAction(e -> showViewStage());
	}

	// A method to construct and return a horizontal box.
	private HBox getHBox() {
		HBox hBox = new HBox(iFinals.HBOX_SPACING);
		hBox.setPadding(new Insets(iFinals.HBOX_INSETS_VALUES[0], iFinals.HBOX_INSETS_VALUES[1],
				iFinals.HBOX_INSETS_VALUES[2], iFinals.HBOX_INSETS_VALUES[3]));
		hBox.setAlignment(Pos.CENTER);
		return hBox;

	}
	
	// A method used to create and display a circle view.
	private void showViewStage() {
		CircleView1 view = new CircleView1();
		// Setting the model to the view.
		view.setModel(model);
	}

	// A method used to create and display a circle controller.
	private void showControllerStage() {
		new CircleController1(model);
	}

	// A method to set the stage for on screen appearance.
	private void readyStageForAppearance(Stage primaryStage) {
		primaryStage.setTitle(iFinals.SINGLE_MAIN_TITLE);
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
