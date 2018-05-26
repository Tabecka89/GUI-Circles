import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;


public class CircleModel1 {
	
	// Attributes for the model.
	private DoubleProperty radius = new SimpleDoubleProperty(20);
	private BooleanProperty filled = new SimpleBooleanProperty(false);
	private ObjectProperty<Color> color = new SimpleObjectProperty<Color>(Color.BLACK);
	private ArrayList<EventHandler<MyCircleActionEvent>> eventHandlerList;

	public DoubleProperty getRadius() {
		return radius;
	}

	public void setRadius(DoubleProperty radius) {
		this.radius = radius;
		processEvent(new MyCircleActionEvent(this, iFinals.RADIUS_LABEL));
	}

	public BooleanProperty isFilled() {
		return filled;
	}

	public void setFilled(BooleanProperty filled) {
		this.filled = filled;
		processEvent(new MyCircleActionEvent(this, iFinals.FILLED_LABEL));
	}

	public ObjectProperty<Color> getColor() {
		return color;
	}

	public void setColor(ObjectProperty<Color> color) {
		this.color = color;
		processEvent(new MyCircleActionEvent(this, iFinals.COLOR_LABEL));
	}

	// Methods for adding and removing listeners.
	public synchronized void addListener(EventHandler<MyCircleActionEvent> l) {
		if (eventHandlerList == null)
			eventHandlerList = new ArrayList<EventHandler<MyCircleActionEvent>>();
		eventHandlerList.add(l);
	}

	public synchronized void removeListener(EventHandler<MyCircleActionEvent> l) {
		if (eventHandlerList != null && eventHandlerList.contains(l))
			eventHandlerList.remove(l);
	}

	// A method to process the event that occurred.
	public void processEvent(MyCircleActionEvent e) {
		synchronized (this) {
			if (eventHandlerList == null){
				System.out.println("No one is listening to the Controller. Please press 'Show View' to interact with the interface.");
				return;
			}
		}
		int size = eventHandlerList.size();
		System.out.println("size of actionListenerList is: " + eventHandlerList.size());
		for (int i = 0; i < size; i++) {
			EventHandler<MyCircleActionEvent> listener = (EventHandler<MyCircleActionEvent>) eventHandlerList.get(i);
			System.out.println("event is: " + e.getType());
			listener.handle(e);
		}
	}


}