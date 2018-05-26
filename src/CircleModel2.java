import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class CircleModel2 implements CircleEvents {
	
	// Attributes for the model.
	private IntegerProperty circleCounter;
	private DoubleProperty radius = new SimpleDoubleProperty(20);
	private BooleanProperty filled = new SimpleBooleanProperty(false);
	private BooleanProperty calculateArea = new SimpleBooleanProperty(false);
	private ObjectProperty<Color> color;
	private HashMap<eventType, ArrayList<EventHandler<ActionEvent>>> circleHashMap = new HashMap<eventType, ArrayList<EventHandler<ActionEvent>>>();

	public CircleModel2(IntegerProperty circleCounter) {
		this.circleCounter = circleCounter;
		this.color = new SimpleObjectProperty<Color>(Color.BLACK);
		for (eventType et : eventType.values())
			circleHashMap.put(et, new ArrayList<EventHandler<ActionEvent>>());
	}

	public IntegerProperty getcircleCounter() {
		return circleCounter;
	}

	public DoubleProperty getRadius() {
		return radius;
	}

	public void setRadius(DoubleProperty radius) {
		this.radius = radius;
		processEvent(eventType.RADIUS, new ActionEvent());
	}

	public BooleanProperty isFilled() {
		return filled;
	}

	public void setFilled(BooleanProperty filled) {
		this.filled = filled;
		processEvent(eventType.FILLED, new ActionEvent());
	}

	public BooleanProperty calculateArea() {
		return calculateArea;
	}

	public void setCalculateArea(BooleanProperty calculateArea) {
		this.calculateArea = calculateArea;
		processEvent(eventType.AREA, new ActionEvent());
	}

	public ObjectProperty<Color> getColor() {
		return color;
	}

	public void setColor(ObjectProperty<Color> color) {
		this.color = color;
		processEvent(eventType.COLOR, new ActionEvent());
	}

	// Methods for adding and removing listeners.
	public synchronized void addListener(EventHandler<ActionEvent> l, eventType et) {
		ArrayList<EventHandler<ActionEvent>> al;
		al = circleHashMap.get(et);
		if (al == null)
			al = new ArrayList<EventHandler<ActionEvent>>();
		al.add(l);
		circleHashMap.put(et, al);
	}

	public synchronized void removeListener(EventHandler<ActionEvent> l, eventType et) {
		ArrayList<EventHandler<ActionEvent>> al;
		al = circleHashMap.get(et);
		if (al != null && al.contains(l))
			al.remove(l);
		circleHashMap.put(et, al);
	}

	// A method to process the event that occurred.
	private void processEvent(eventType et, ActionEvent e) {
		ArrayList<EventHandler<ActionEvent>> al;
		synchronized (this) {
			al = circleHashMap.get(et);
			if (al == null)
				return;
		}
		System.out.println("model number: " + (getcircleCounter().intValue() + 1) + " actionCommand: " + et.toString()
				+ " array size is: " + al.size());
		for (int i = 0; i < al.size(); i++) {
			EventHandler<ActionEvent> listener = al.get(i);
			listener.handle(e);
		}
	}

}
