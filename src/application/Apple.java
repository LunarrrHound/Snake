package application;

import javafx.scene.paint.Color;

public class Apple {
	
	public static final Color color = Color.RED;
	private Point point;
	
	public Apple(Point point) {
		this.point = point;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}
}
