package application;

import java.util.Random;

import javafx.scene.paint.Color;

public class Grid {
	
	public static final int size = 10;
	public static final Color color = new Color(0.1, 0.1, 0.1, 1);
	
	private final int cols;
	private final int rows;
	
	private Snake snake;
	private Apple apple;
	
	public Grid(final double width, final double height) {
		rows = (int)width / size;
		cols = (int)height / size;
		snake = new Snake(this, new Point(rows / 2, cols / 2),new Point(rows/2-1,cols/2), new Point(rows/2-2,cols/2));//set the snake at the middle of the screen
		apple = new Apple(getRandomPoint());
	}
	
	public void update() {
		if(apple.getPoint().equals(snake.getHead())) {
			snake.growUp();
			apple.setPoint(getRandomPoint());
		}
		else {
			snake.move();
		}
	}
	
	public Point goThrough(Point point) {
		int x = point.getX();
		int y = point.getY();
		if(x >= rows) x = 0;
		if(y >= cols) y = 0;
		if(x < 0) x = rows -1;
		if(y < 0) y = cols -1;
		return new Point(x, y);
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	public Apple getApple() {
		return apple;
	}
	
	public Point getRandomPoint() {
		Random random = new Random();
		Point point;
		do {
			point = new Point(random.nextInt(rows), random.nextInt(cols));
		}while(point.equals(snake.getHead()));//check if the apple is generated on the snake head
		return point;
	}
	
	public double getWidth() {
		return rows*size;
	}
	
	public double getHeight() {
		return cols*size;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
}
