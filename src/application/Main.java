package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import application.Game;
import application.Grid;


public class Main extends Application {
	
	private static final int width = 600;
	private static final int height = 400;
	
	private Game game;
	private Grid grid;
	private GraphicsContext context;
	private HardMode hardMode;

	
	@Override
	public void start(Stage stage) {
		
		try {
			StackPane root = new StackPane();
			Canvas canvas = new Canvas(width, height);
			context = canvas.getGraphicsContext2D();
			canvas.setFocusTraversable(true);
			canvas.setOnKeyPressed(e ->{
			Snake snake = grid.getSnake();
			if(game.isKeyPressed()) {
					return;
				}
				switch(e.getCode()) {
				case W:
					snake.goUp();
					break;
				case A:
					snake.goLeft();
					break;
				case S:
					snake.goDown();
					break;
				case D:
					snake.goRight();
					break;
				case E:
					if(game.isPaused()) {
						reset();
						(new Thread(game)).start();
					}
					break;
				case H:
					if(game.isPaused()&&!snake.isSafe()) {
						resetHard();
						(new Thread(hardMode)).start();
					}
					
					break;
					
				}
			});

			reset();
			
			root.getChildren().add(canvas);
			Scene scene = new Scene(root);
			stage.setResizable(true);
			stage.setTitle("Snake");
			stage.setOnCloseRequest(e -> System.exit(0));
			stage.setScene(scene);
			stage.show();
			
			(new Thread(game)).start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reset() {
		grid = new Grid(width, height);
		game = new Game(grid, context);
		Painter.paint(grid, context);
	}
	
	public void resetHard() {
		grid = new Grid(width, height);
		hardMode = new HardMode(grid, context);
		Painter.paint(grid, context);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
