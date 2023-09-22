package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import application.Painter;

public class Game implements Runnable{
	
	private final Grid grid;
	private final GraphicsContext context;
	private int frames;
	private float interval;
	private boolean running;
	private boolean paused;
	private boolean keyIsPressed;
	
	public Game(final Grid grid, final GraphicsContext context) {
		this.grid = grid;
		this.context = context;
		frames = 20;
		interval = 1000.0f / frames;
		running = true;
		paused = false;
		keyIsPressed = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running && !paused) {
			float time = System.currentTimeMillis();
			keyIsPressed = false;
			grid.update();
			Painter.paint(grid, context);
			//check if it's game over
			if(!grid.getSnake().isSafe()) {
				pause();
				Painter.paintGameOverScreen(grid, context);
				Painter.paintGameOverMessage(grid,context);
				break;
			}
			
			time = System.currentTimeMillis() - time;
			if(time < interval) {
				try {
					Thread.sleep((long)(interval - time));
				}catch(InterruptedException ignore) {
					
				}
			}
		}
	}
	
	public boolean isKeyPressed() {
		return keyIsPressed;
	}
	
	public void setKeyPressed() {
		keyIsPressed = true;
	}
	
	public void pause() {
		paused = true;
		running = false;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	
	
}
