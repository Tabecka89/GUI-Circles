import javafx.event.Event;

// A class that extends 'Event' and is used in the 'Model' class.
public class MyCircleActionEvent extends Event {
	private static final long serialVersionUID = 1L;
	private String type;

	public MyCircleActionEvent(Object source, String type) {
		super(source, null, null);
		this.type = type;
	}

	public String getType() {
		return type;
	}

}