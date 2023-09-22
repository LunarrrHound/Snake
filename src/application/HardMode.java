package application;

import javafx.scene.canvas.GraphicsContext;

public class HardMode implements Runnable{
	private final Grid grid;
	private final GraphicsContext context;
	private int frames;
	private float interval;
	private boolean running;
	private boolean paused;
	private boolean keyIsPressed;
	
	public HardMode(final Grid grid, final GraphicsContext context) {
		this.grid = grid;
		this.context = context;
		frames = 35;
		interval = 1000.0f / frames;
		running = true;
		paused = false;
		keyIsPressed = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running&&!paused ) {
			float time = System.currentTimeMillis();
			keyIsPressed = false;
			grid.update();
			Painter.paint(grid, context);
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
	
	public void stop() {
		running = false;
	}
	
	public boolean isKeyPressed() {
		return keyIsPressed;
	}
	
	public void setKeyPressed() {
		keyIsPressed = true;
	}
	
	public void pause() {
		paused = true;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	
	
}
