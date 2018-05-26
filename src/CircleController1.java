import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CircleController1 {

	// Attributes for the controller.
	private CircleModel1 model;
	private TextField tfRadius = new TextField();
	private Boolean[] booleanValues = { false, true };
	private ObservableList<Boolean> booleanItems = FXCollections.observableArrayList(booleanValues);
	private ComboBox<Boolean> cboFilled = new ComboBox<Boolean>();
	private ColorPicker cp = new ColorPicker(iFinals.DEFAULT_STROKE_COLOR);

	public CircleController1(CircleModel1 model) {
		setModel(model);
		setPreferencesForAttributes(this.cboFilled, this.cp);
		GridPane pane = (GridPane) getPane();
		
		
		// Handling events for every scenario using Lambda Expressions.
		tfRadius.setOnAction(e -> {
			try{
				double radiusValue = Double.valueOf(tfRadius.getText());
				DoubleProperty radiusAsProperty = new SimpleDoubleProperty(radiusValue);
				this.model.setRadius(radiusAsProperty);
			} catch(NumberFormatException ex){
				System.out.println("Radius must be a Double value.");
			}
		});

		cp.setOnAction(e -> {
			ObjectProperty<Color> colorAsProperty = new SimpleObjectProperty<Color>(cp.getValue());
			this.model.setColor(colorAsProperty);
		});

		cboFilled.setOnAction(e -> {
			boolean booleanValue = booleanValues[booleanItems.indexOf(cboFilled.getValue())];
			BooleanProperty booleanValueAsProperty = new SimpleBooleanProperty(booleanValue);
			this.model.setFilled(booleanValueAsProperty);
		});

		Scene scene = new Scene(pane);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		readyStageForAppearance(primaryStage);
	}

	// A method to set the properties of the combo box and color picker.
	private void setPreferencesForAttributes(ComboBox<Boolean> cboFilled, ColorPicker cp) {
		this.cboFilled.getItems().addAll(booleanItems);
		this.cboFilled.getSelectionModel().select(0);
		this.cboFilled.setPrefSize(iFinals.CBO_WIDTH, iFinals.CBO_HEIGHT);
		this.cp.setPrefSize(iFinals.CP_WIDTH, iFinals.CP_HEIGHT);
	}

	public void setModel(CircleModel1 newModel) {
		model = newModel;
	}

	public CircleModel1 getModel() {
		return model;
	}

	// A method to construct and return a suitable pane.
	public Pane getPane() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(iFinals.CONTROLLER_PANE_INSETS_VALUES[0], iFinals.CONTROLLER_PANE_INSETS_VALUES[1],
				iFinals.CONTROLLER_PANE_INSETS_VALUES[2], iFinals.CONTROLLER_PANE_INSETS_VALUES[3]));
		pane.add(new Label(iFinals.RADIUS_LABEL), 0, 0);
		pane.add(tfRadius, 1, 0);
		pane.add(new Label(iFinals.FILLED_LABEL), 0, 1);
		pane.add(cboFilled, 1, 1);
		pane.add(new Label(iFinals.COLOR_LABEL), 0, 2);
		pane.add(cp, 1, 2);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	// A method to set the stage for on screen appearance.
	private void readyStageForAppearance(Stage primaryStage) {
		primaryStage.setTitle(iFinals.CONTROLLER_TITLE);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setX(iFinals.CONTROLLER_X_COORDINATE);
		primaryStage.setY(iFinals.CONTROLLER_Y_COORDINATE);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
			}
		});
	}
}
