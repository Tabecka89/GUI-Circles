import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CircleView3 implements CircleEvents {

	// Different classes for each event.
	// The view is painted according to user choice.
	class RadiusEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			eventTitle = eventType.RADIUS.toString();
			eventText.setText(iFinals.EVENT_TYPE_PRECURSOR + " " + eventTitle);
			paint();
		}

	}

	class ColorEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			eventTitle = eventType.COLOR.toString();
			eventText.setText(iFinals.EVENT_TYPE_PRECURSOR + " " + eventTitle);
			paint();
		}

	}

	class FilledEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			eventTitle = eventType.FILLED.toString();
			eventText.setText(iFinals.EVENT_TYPE_PRECURSOR + " " + eventTitle);
			paint();
		}

	}

	class AreaEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			eventTitle = eventType.AREA.toString();
			eventText.setText(iFinals.EVENT_TYPE_PRECURSOR + " " + eventTitle);
			paint();
		}
	}

	// Attributes for the view.
	private CircleModel3 model;
	private int circleCounter;
	private Circle circle;
	private Text eventText;
	private Text circleNumberText;
	private Text circleRadiusText;
	private Text circleAreaText;
	private String eventTitle = "DEFAULT";
	private boolean defaultFlag = true;

	public CircleView3(int circleCounter, CircleModel3 model) {
		setModel(model);
		this.circleCounter = circleCounter;
		setDefaultCircle(new Circle());
		arrangeAndInitializeTexts(this.eventText, this.circleNumberText, this.circleRadiusText, this.circleAreaText);
		Pane pane = getPane();
		Scene scene = new Scene(pane, iFinals.VIEW2_SCENE_WIDTH, iFinals.VIEW2_SCENE_HEIGHT);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		readyStageForAppearance(primaryStage);
	}

	// A method to initialize and set the different Texts. 
	private void arrangeAndInitializeTexts(Text eventText, Text circleNumberText, Text circleRadiusText,
			Text circleAreaText) {
		this.eventText = new Text(iFinals.TEXT_X_VALUE, iFinals.EVENT_TEXT_Y_VALUE, iFinals.EVENT_TYPE_PRECURSOR + " " + eventTitle);
		this.circleNumberText = new Text(iFinals.TEXT_X_VALUE, iFinals.CIRCLE_NUMBER_TEXT_Y_VALUE, iFinals.CIRCLE_NUMBER + " " + String.valueOf(circleCounter + 1));
		this.circleRadiusText = new Text(iFinals.TEXT_X_VALUE, iFinals.CIRCLE_RADIUS_TEXT_Y_VALUE,
				iFinals.CIRCLE_RADIUS + " " + String.valueOf(this.model.getRadius().doubleValue()));
		this.circleAreaText = new Text(iFinals.TEXT_X_VALUE, iFinals.CIRCLE_AREA_TEXT_Y_VALUE, null);
	}

	// A method to construct and return a suitable pane.
	private Pane getPane() {
		Pane pane = new Pane();
		this.circle.centerXProperty().bind(pane.widthProperty().divide(2));
		this.circle.centerYProperty().bind(pane.heightProperty().divide(2));
		pane.getChildren().add(this.circle);
		pane.getChildren().add(this.eventText);
		pane.getChildren().add(this.circleNumberText);
		pane.getChildren().add(this.circleRadiusText);
		pane.getChildren().add(this.circleAreaText);
		return pane;
	}

	// A method to create and set a default circle to be shown on default.
	private void setDefaultCircle(Circle circle) {
		this.circle = circle;
		this.circle.setFill(iFinals.DEFAULT_FILL_COLOR);
		this.circle.setStroke(iFinals.DEFAULT_STROKE_COLOR);
		this.circle.setRadius(this.model.getRadius().doubleValue());
	}

	public int getcircleCounter() {
		return circleCounter;
	}

	// A method to set the model and establish listeners using different classes.
	public void setModel(CircleModel3 newModel) {
		model = newModel;
		if (model != null) {
			model.addListener(new RadiusEvent(), eventType.RADIUS);
			model.addListener(new FilledEvent(), eventType.FILLED);
			model.addListener(new ColorEvent(), eventType.COLOR);
			model.addListener(new AreaEvent(), eventType.AREA);
		}
		paint();
	}

	public CircleModel3 getModel() {
		return model;
	}

	// A method used to show the view according to changes the user chooses to make.
	public void paint(){
		if(defaultFlag){
			setDefaultCircle(new Circle());
			this.defaultFlag = false;
		}
		else{
			circleRadiusText.setText(iFinals.CIRCLE_RADIUS + " " + model.getRadius().doubleValue());
			circle.setRadius(model.getRadius().doubleValue());
			if (model.isFilled().get()) {
				circle.setStroke(model.getColor().get());
				circle.setFill(model.getColor().get());
			} else {
				circle.setStroke(model.getColor().get());
				circle.setFill(Color.TRANSPARENT);
			}
			if (model.calculateArea().get()) {
				double circleArea = model.getRadius().doubleValue() * model.getRadius().doubleValue() * Math.PI;
				circleAreaText.setText(iFinals.CIRCLE_AREA + " " + circleArea);
			} else
				circleAreaText.setText(null);
		}
	}
	
	// A method to set the stage for on screen appearance.
	private void readyStageForAppearance(Stage primaryStage) {
		primaryStage.setTitle(iFinals.MULTIPLE_VIEW_TITLE + " " + (circleCounter + 1));
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setX(iFinals.VIEW_X_COORDINATE + (circleCounter * iFinals.PROPORTION_INCREMENT_VALUE));
		primaryStage.setY(iFinals.VIEW_Y_COORDINATE + (circleCounter * iFinals.PROPORTION_INCREMENT_VALUE));
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
			}
		});

	}

}
