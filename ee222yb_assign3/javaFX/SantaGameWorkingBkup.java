// Höll på med mått sist, se till att de funkar om scenens storlek ändras


package ee222yb_assign3.javaFX;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SantaGameWorkingBkup extends Application {

	private String path = "ee222yb_assign3\\javaFX\\sprites\\";
	private static final double WIDTH = 1400, HEIGHT = (590.0/1200) * WIDTH, SANTA_SIZE = 33, UPDATE_FREQUENCY = 50;
	
	private double xSpeed = (double) SPEED_MOD * UPDATE_FREQUENCY;
	private static final double DEFAULT_JUMP = 7;

	// Settings
	private static final double SPEED_MOD = 0.3; // Change this to adjust speed
	private static int JUMP_DELAY = 100; // change to adjust jump delay (milliseconds)
	private static int SLIDE_DELAY = 500; // change to adjust slide delay (milliseconds)
	
	private ImageView santa;
	private int runIndex = 0;
	private int jumpIndex = 0;
	private int slideIndex = 0;
	private double someCalc = DEFAULT_JUMP;
	private int direction = 1; // 1 or -1
	private long eventStart;
	
	public static void main(String[] args) {
		launch();
	}

	public void start(Stage stage) {
		Image bgImg = new Image(path + "BG.png");
		ImageView background = new ImageView(bgImg);
		background.setFitWidth(WIDTH);
		background.setPreserveRatio(true);

		ImageView snowman = new ImageView(new Image(path + "SnowMan.png"));
		snowman.setFitWidth(WIDTH / 12); // 100
		snowman.setPreserveRatio(true);
		snowman.setY((405.0 / 590) * HEIGHT); // 405
		
		Image idle = new Image(path + "Idle (1).png");
		santa = new ImageView(idle);
		santa.setFitWidth(WIDTH / 6); // 200
		santa.setPreserveRatio(true);
		santa.setY((390.0 / 590) * HEIGHT); // 390
		santa.setX(WIDTH / 6); // 200 // Santa start position

		Image[] santaRun = new Image[11];
		for(int i = 0; i < santaRun.length; i ++)
			santaRun[i] = new Image(path + "santa\\Run (" + (i + 1) + ").png");
		Image[] santaJump = new Image[15];
		for(int i = 0; i < santaJump.length; i ++)
			santaJump[i] = new Image(path + "santa\\Jump (" + (i + 1) + ").png");
		Image[] santaSlide = new Image[11];
		for(int i = 0; i < santaSlide.length; i ++)
			santaSlide[i] = new Image(path + "santa\\Slide (" + (i + 1) + ").png");
				
		ImageView ground = new ImageView(new Image(path + "ground.png"));
		ground.setFitWidth(WIDTH);
		ground.setPreserveRatio(true);
		ground.setY((510.0 / 590) * HEIGHT);
		
		ImageView tree = new ImageView(new Image(path + "Tree_2.png"));
		tree.setFitWidth(WIDTH / 12	); // 100
		tree.setPreserveRatio(true);
		tree.setX((1100.0 / 1200) * WIDTH); // 1100
		tree.setY((390.0 / 590) * HEIGHT);
		
		Group root = new Group(background, ground, snowman, tree, santa);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.setTitle("The Santa game");
//		stage.setResizable(false);
		stage.show();

		
		scene.setOnKeyPressed(key -> {
			// Slide. Cannot be aborted
			if(key.getCode() == KeyCode.DOWN && eventStart + (UPDATE_FREQUENCY * santaSlide.length) + SLIDE_DELAY < System.currentTimeMillis()) {
				eventStart = System.currentTimeMillis();
				KeyFrame k = new KeyFrame(Duration.millis(UPDATE_FREQUENCY), event -> {
					santa.setImage(santaSlide[slideIndex ++]);
					if(santa.getX() >= 15 && santa.getX() <= WIDTH - 220)
						santa.setX(santa.getX() + (xSpeed * direction));
					if(slideIndex == santaSlide.length) {
						slideIndex = 0;
						santa.setImage(idle);
					}
				});
				Timeline t = new Timeline(k);
				t.setCycleCount(santaSlide.length);
				t.play();
			}
			else if(key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.LEFT) {
				if(key.getCode() == KeyCode.RIGHT && direction != 1)
					direction = 1;
				else if(key.getCode() == KeyCode.LEFT && direction != (-1))
					direction = -1;
				// Reset run array index
				if(runIndex == santaRun.length)
					runIndex = 0;
				//turning around
				if(santa.getScaleX() != direction) {
					runIndex = 0;
					santa.setScaleX(direction);
					santa.setImage(idle);
					santa.setX(santa.getX() + (SANTA_SIZE * direction));
				}
				// run as long as santa is within play area
				else if(santa.getX() >= 15 && santa.getX() <= WIDTH - 220) {
					santa.setImage(santaRun[runIndex ++]);
					santa.setX(santa.getX() + (xSpeed * direction));
				}
				// when santa gets to an edge
				else
					santa.setImage(idle);
			}
			else if(key.getCode() == KeyCode.UP && eventStart + (UPDATE_FREQUENCY * santaJump.length) + JUMP_DELAY < System.currentTimeMillis()) {
				eventStart = System.currentTimeMillis();
				KeyFrame k = new KeyFrame(Duration.millis(UPDATE_FREQUENCY), event -> {
					santa.setImage(santaJump[jumpIndex ++]);
					santa.setY(santa.getY() - (someCalc --));
					if(someCalc < -(santaJump.length / 2))
						someCalc = santaJump.length / 2;
					if(santa.getX() >= 15 && santa.getX() <= WIDTH - 220)
						santa.setX(santa.getX() + (xSpeed * direction));
					if(jumpIndex == santaJump.length) {
						jumpIndex = 0;
						santa.setImage(idle);
					}
				});
				Timeline t = new Timeline(k);
				t.setCycleCount(santaJump.length);
				t.play();
			}
			
		});	
	}
	
	
}