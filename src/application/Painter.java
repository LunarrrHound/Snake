package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static application.Grid.size;


public class Painter {
	
	public static void paint(Grid grid, GraphicsContext context) {
		
		//Painting background
		context.setFill(Grid.color);
		context.fillRect(0, 0,grid.getWidth() , grid.getHeight());
		//painting apples
		context.setFill(Apple.color);
		paintPoint(grid.getApple().getPoint(),context);
		//painting the snake
		Snake snake = grid.getSnake();
		context.setFill(Snake.color);
		snake.getBody().forEach(point ->paintPoint(point, context));
		
		context.setFill(Color.BEIGE);
		context.fillText("Score: "+100*(snake.getBody().size()-3),10, 390);
	}
	
	public static void paintPoint(Point point, GraphicsContext context) {
		context.fillRect(point.getX()*size,point.getY()*size,size,size);
	}
	
	public static void paintGameOverMessage(Grid grid,GraphicsContext context) {
		Snake snake = grid.getSnake();
		context.setFill(Color.CRIMSON);
		context.fillText("Game Over press E to restart as normal game.", 200, 160);
		context.fillText("Your score is "+100*(snake.getBody().size()-3), 200, 180);
		context.fillText("Press H to play Hard mode.(faster)", 200, 210);
	}
	
	public static void paintGameOverScreen(Grid grid, GraphicsContext context) {
		context.setFill(Color.BLACK);
		context.fillRect(0, 0, grid.getWidth(), grid.getHeight());
	}
	
	public static void paintPaused(Grid grid, GraphicsContext context) {
		context.setFill(Color.BLACK);
		context.fillRect(0, 0, grid.getWidth(), grid.getHeight());
		context.setFill(Color.RED);
		context.fillText("paused",size, size);
	}
	
	
}
