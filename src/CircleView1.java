import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class CircleView1 extends StackPane {
	
	// Attributes for the view.
	private CircleModel1 model;
	private Circle circle;
	private boolean defaultFlag = true;

	public void setModel(CircleModel1 newModel) {
		model = newModel;
		
		// Telling the view to listen to the controller via the 'Paint' method.
		if (model != null)
			model.addListener(e -> paint());
		
		paint();
		getChildren().add(this.circle);
		Scene scene = new Scene(this, iFinals.VIEW1_SCENE_WIDTH, iFinals.VIEW1_SCENE_HEIGHT);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene); 
		readyStageForAppearance(primaryStage);
	}

	public CircleModel1 getModel() {
		return model;
	}
	
	public boolean isDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	

	// A method to create and set a default circle to be shown on default. 
	private void setDefaultCircle(Circle circle) {
		this.circle = circle;
		this.circle.setRadius(this.model.getRadius().doubleValue());
		this.circle.setFill(iFinals.DEFAULT_FILL_COLOR);
		this.circle.setStroke(iFinals.DEFAULT_STROKE_COLOR);
	}

	// A method used to show the view according to changes the user chooses to make.
	private void paint() {
		if(defaultFlag){
			setDefaultCircle(new Circle());
			this.defaultFlag = false;
		}
		else{
			this.circle.setRadius(this.model.getRadius().doubleValue());
			if(!this.model.isFilled().get()){
				this.circle.setFill(Color.TRANSPARENT);
				this.circle.setStroke(this.model.getColor().get());
			}
			else{
				this.circle.setFill(this.model.getColor().get());
				this.circle.setStroke(this.model.getColor().get());
			}
		}
	}
	
	// A method to set the stage for on screen appearance.
	private void readyStageForAppearance(Stage primaryStage) {
	    primaryStage.setTitle(iFinals.VIEW_TITLE); 
	    primaryStage.show(); 
	    primaryStage.setAlwaysOnTop(true);
	    primaryStage.setX(iFinals.VIEW_X_COORDINATE);
	    primaryStage.setY(iFinals.VIEW_Y_COORDINATE);
	    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        @Override
	        public void handle(WindowEvent event) {
	            event.consume();
	        }
	    });
	}
}