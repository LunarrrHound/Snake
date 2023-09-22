package application;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.paint.Color;

public class Snake {
	
	public static final Color color = Color.SEAGREEN;
	public static final Color color_dead = Color.RED;
	private Grid grid;
	private int length;
	private Point head;
	private List<Point> body;
	private int velocityX;
	private int velocityY;
	public boolean safe;
	private Game game;
	private HardMode hardMode;
	
	public Snake (Grid grid, Point initialPoint,Point second, Point third) {
		length = 3;
		body = new LinkedList<>();
		body.add(initialPoint);
		body.add(second);
		body.add(third);
		head = initialPoint;
		safe = true;
		this.grid = grid;
		velocityX = 1;
		velocityY = 0;
	}
	
	public void move() {
		if(!isStill()) {
			moveTo(head.newPoint(velocityX, velocityY));
		}
	}
	
	public void moveTo(Point point) {
		checkAndAdd(point);
		body.remove(0);
	}
	
	public void growTo(Point point) {
		length++;
		checkAndAdd(point);
	}
	
	public void goUp() {
		if(velocityY == 1 && length >=3 ) return ;
		velocityX = 0;
		velocityY = -1;
	}
	
	public void goLeft() {
		if(velocityX == 1 && length >=3) return ;
		velocityX = -1;
		velocityY = 0;
	}
	
	public void goDown() {
		if(velocityY == -1 && length >=3 ) return ;
		velocityX = 0;
		velocityY = 1;
	}
	
	public void goRight() {
		if(velocityX == -1 && length >=3) return;
		velocityX = 1;
		velocityY = 0;
	}
	
	public Point getHead() {
		return head;
	}
	
	public List<Point> getBody() {
		return  body;
	}
	
	private void checkAndAdd(Point point) {
		point = grid.goThrough(point);
		safe = !body.contains(point);
		body.add(point);
		head = point;
	}
	
	public boolean isSafe() {
		if(safe) {
			return safe;
		}
		else {
		return false;
		}
	}
	
	public boolean isStill() {
		if(velocityX == 0 && velocityY == 0) {
			return true;
		}
		return false;
	}
	
	public void growUp() {
		if(! isStill()) {
			growTo(head.newPoint(velocityX, velocityY));
		}
	}
	
}

