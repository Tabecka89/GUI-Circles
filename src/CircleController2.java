import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

public class CircleController2 implements CircleEvents {

	// Attributes for the controller.
	private CircleModel2 model;
	private int circleCounter;
	private TextField tfRadius = new TextField();
	private ComboBox<Boolean> cboFilled = new ComboBox<Boolean>();
	private ComboBox<Boolean> cboCalculateArea = new ComboBox<Boolean>();
	private ColorPicker cp = new ColorPicker(iFinals.DEFAULT_STROKE_COLOR);
	private Boolean[] booleanValues = { false, true };
	private ObservableList<Boolean> booleanItems = FXCollections.observableArrayList(booleanValues);

	public CircleController2(int circleCounter) {
		this.circleCounter = circleCounter;
		setPreferencesForAttributes(this.cboFilled, this.cboCalculateArea, this.cp);
		GridPane pane = (GridPane) getPane();

		// Handling events for every scenario using anonymous handlers.
		tfRadius.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				try {
					DoubleProperty radius = new SimpleDoubleProperty(Double.valueOf(tfRadius.getText()));
					if (radius.doubleValue() <= 0) {
						tfRadius.setText(iFinals.INPUT_ERROR_1);
						return;
					}
					model.setRadius(radius);
				} catch (NumberFormatException ex) {
					tfRadius.setText(iFinals.INPUT_ERROR_2);
					return;
				} 
			}
		});

		cboFilled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				boolean filledValue = booleanValues[booleanItems.indexOf(cboFilled.getValue())];
				BooleanProperty filledValueAsProperty = new SimpleBooleanProperty(filledValue);
				model.setFilled(filledValueAsProperty);
			}
		});

		cp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				ObjectProperty<Color> colorAsProperty = new SimpleObjectProperty<Color>(cp.getValue());
				model.setColor(colorAsProperty);
			}
		});

		cboCalculateArea.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				boolean areaBooleanValue = booleanValues[booleanItems.indexOf(cboCalculateArea.getValue())];
				BooleanProperty areaBooleanValueAsProperty = new SimpleBooleanProperty(areaBooleanValue);
				model.setCalculateArea(areaBooleanValueAsProperty);

			}
		});

		Scene scene = new Scene(pane);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		readyStageForAppearance(primaryStage);
	}

	// A method to set the properties of the combo boxes and color picker.
	private void setPreferencesForAttributes(ComboBox<Boolean> cboFilled, ComboBox<Boolean> cboCalculateArea,
			ColorPicker cp) {
		this.cboFilled.getItems().addAll(booleanItems);
		this.cboFilled.getSelectionModel().select(0);
		this.cboFilled.setPrefSize(iFinals.CBO_WIDTH, iFinals.CBO_HEIGHT);
		this.cboCalculateArea.getItems().addAll(booleanItems);
		this.cboCalculateArea.setPrefSize(iFinals.CBO_WIDTH, iFinals.CBO_HEIGHT);
		this.cboCalculateArea.getSelectionModel().select(0);
		this.cp.setPrefSize(iFinals.CP_WIDTH, iFinals.CP_HEIGHT);
	}

	public int getcircleCounter() {
		return circleCounter;
	}

	public void setModel(CircleModel2 newModel) {
		model = newModel;
	}

	public CircleModel2 getModel() {
		return model;
	}

	// A method to construct and return a suitable pane.
	public Pane getPane() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(iFinals.CONTROLLER_PANE_INSETS_VALUES[0], iFinals.CONTROLLER_PANE_INSETS_VALUES[1],
				iFinals.CONTROLLER_PANE_INSETS_VALUES[2], iFinals.CONTROLLER_PANE_INSETS_VALUES[3]));
		pane.add(new Label(eventType.RADIUS.toString()), 0, 0);
		pane.add(new Label(eventType.FILLED.toString()), 0, 1);
		pane.add(new Label(eventType.AREA.toString()), 0, 2);
		pane.add(new Label(eventType.COLOR.toString()), 0, 3);
		pane.add(tfRadius, 1, 0);
		pane.add(cboFilled, 1, 1);
		pane.add(cboCalculateArea, 1, 2);
		pane.add(cp, 1, 3);
		return pane;
	}

	// A method to set the stage for on screen appearance.
	private void readyStageForAppearance(Stage primaryStage) {
		primaryStage.setTitle(iFinals.MULTIPLE_CONTROLLER_TITLE + " " + (circleCounter + 1));
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setX(iFinals.CONTROLLER_X_COORDINATE + (circleCounter * iFinals.PROPORTION_INCREMENT_VALUE));
		primaryStage.setY(iFinals.CONTROLLER_Y_COORDINATE + (circleCounter * iFinals.PROPORTION_INCREMENT_VALUE));
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
			}
		});

	}
}
