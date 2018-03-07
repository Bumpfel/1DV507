// Höll på med mått sist, se till att de funkar om scenens storlek ändras

package ee222yb_assign3.javaFX;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SantaGame extends Application {

	private String path = "ee222yb_assign3\\javaFX\\sprites\\";
	private static final double WIDTH = 1400, HEIGHT = (590.0/1200) * WIDTH, SANTA_SIZE = 33, UPDATE_FREQUENCY = 50;

	private double runSpeed = (double) SPEED_MOD * UPDATE_FREQUENCY;
	private double walkSpeed = runSpeed / 2;
	private static final double DEFAULT_JUMP = 7;

	// Settings
	private static final double SPEED_MOD = 0.3; // Change this to adjust speed
	private static int JUMP_DELAY = 100; // change to adjust jump delay (milliseconds)
	private static int SLIDE_DELAY = 500; // change to adjust slide delay (milliseconds)

	private Image idle = new Image(path + "santa\\Idle (1).png");
	private ImageView santa;
	private static final Image[] SANTA_RUN = new Image[11];
	private static final Image[] SANTA_WALK = new Image[13];
	private static final Image[] SANTA_JUMP = new Image[15];
	private static final Image[] SANTA_SLIDE = new Image[11];
	
//	private static enum Dir { LEFT, RIGHT };

	
	private int runIndex = 0;
	private int walkIndex = 0;
	private int jumpIndex = 0;
	private int slideIndex = 0;
	private double someCalc = DEFAULT_JUMP;
	private int direction = 1; // 1 or -1
	private long eventStart;
//	private boolean eventOngoing = false;

	private Timeline timeline;

	public static void main(String[] args) {
		
		launch();
	}

	public void start(Stage stage) {
		// Setup
		populateSantaRun();
		populateSantaWalk();
		populateSantaJump();
		populateSantaSlide();
	
		Image bgImg = new Image(path + "BG.png");
		ImageView background = new ImageView(bgImg);
		background.setFitWidth(WIDTH);
		background.setPreserveRatio(true);

		ImageView snowman = new ImageView(new Image(path + "SnowMan.png"));
		snowman.setFitWidth(WIDTH / 12); // 100
		snowman.setPreserveRatio(true);
		snowman.setY((405.0 / 590) * HEIGHT); // 405

		santa = new ImageView(idle);
		santa.setFitWidth(WIDTH / 6); // 200
		santa.setPreserveRatio(true);
		santa.setY((390.0 / 590) * HEIGHT); // 390
		santa.setX(WIDTH / 6); // 200 // Santa start position
		
		
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
			if(key.getCode() == KeyCode.DOWN)
				slide();
			if(key.getCode() == KeyCode.LEFT && key.isShiftDown())
				walk(Direction.LEFT);
			else if(key.getCode() == KeyCode.LEFT)
				run(Direction.LEFT);
			if(key.getCode() == KeyCode.RIGHT && key.isShiftDown())
				walk(Direction.RIGHT);
			else if(key.getCode() == KeyCode.RIGHT)
				run(Direction.RIGHT);
			if(key.getCode() == KeyCode.UP)
				jump();
		});	
	}
	
	private boolean eventIsAvailable(double delay) {
		if(eventStart + (UPDATE_FREQUENCY * SANTA_JUMP.length) + delay < System.currentTimeMillis())
			return true;
		return false;
	}
	
	private void run(Direction dir) {
		direction = 1;
		if(dir == Direction.LEFT)
			direction = -1;
		// Reset run array index
		if(runIndex == SANTA_RUN.length)
			runIndex = 0;
		//turning around
		if(santa.getScaleX() != direction) {
			runIndex = 0;
			santa.setScaleX(direction);
			santa.setImage(idle);
			santa.setX(santa.getX() + (SANTA_SIZE * direction));
		}
		else if(eventIsAvailable(0)) {
			// run as long as santa is within play area
			if(santa.getX() >= 15 && santa.getX() <= WIDTH - 220) {
				santa.setImage(SANTA_RUN[runIndex ++]);
				santa.setX(santa.getX() + (runSpeed * direction));
			}
			// when santa gets to an edge
			else
				santa.setImage(idle);
		}
	}
	
	private void walk(Direction dir) {
		direction = 1;
		if(dir == Direction.LEFT)
			direction = -1;
		// Reset run array index
		if(walkIndex == SANTA_WALK.length)
			walkIndex = 0;
		//turning around
		if(santa.getScaleX() != direction) {
			runIndex = 0;
			santa.setScaleX(direction);
			santa.setImage(idle);
			santa.setX(santa.getX() + (SANTA_SIZE * direction));
		}
		else if(eventIsAvailable(0)) {
			// run as long as santa is within play area
			if(santa.getX() >= 15 && santa.getX() <= WIDTH - 220) {
				santa.setImage(SANTA_WALK[walkIndex ++]);
				santa.setX(santa.getX() + (walkSpeed * direction));
			}
			// when santa gets to an edge
			else
				santa.setImage(idle);
		}
	}
	
	private void jump() {
		if(eventIsAvailable(JUMP_DELAY)) {
			eventStart = System.currentTimeMillis();
			KeyFrame k = new KeyFrame(Duration.millis(UPDATE_FREQUENCY), event -> {
				santa.setImage(SANTA_JUMP[jumpIndex ++]);
				santa.setY(santa.getY() - (someCalc --));
				if(someCalc < -(SANTA_JUMP.length / 2))
					someCalc = SANTA_JUMP.length / 2;
				if(santa.getX() >= 15 && santa.getX() <= WIDTH - 220)
					santa.setX(santa.getX() + (runSpeed * direction));
				if(jumpIndex == SANTA_JUMP.length) {
					jumpIndex = 0;
					santa.setImage(idle);
				}
			});
			timeline = new Timeline(k);
			timeline.setCycleCount(SANTA_JUMP.length);
			timeline.play();
		}
	}

	private void slide() {
		if(eventIsAvailable(SLIDE_DELAY)) {
			eventStart = System.currentTimeMillis();
			KeyFrame k = new KeyFrame(Duration.millis(UPDATE_FREQUENCY), event -> {
				santa.setImage(SANTA_SLIDE[slideIndex ++]);
				if(santa.getX() >= 15 && santa.getX() <= WIDTH - 220)
					santa.setX(santa.getX() + (runSpeed * direction));
				if(slideIndex == SANTA_SLIDE.length) {
					slideIndex = 0;
					santa.setImage(idle);
				}
			});
			timeline = new Timeline(k);
			timeline.setCycleCount(SANTA_SLIDE.length);
			timeline.play();
			}
		}

	private void populateSantaRun() {
		for(int i = 0; i < SANTA_RUN.length; i ++)
			SANTA_RUN[i] = new Image(path + "santa\\Run (" + (i + 1) + ").png");
	}

	private void populateSantaWalk() {
		for(int i = 0; i < SANTA_WALK.length; i ++)
			SANTA_WALK[i] = new Image(path + "santa\\Walk (" + (i + 1) + ").png");
	}

	private void populateSantaJump() {
		for(int i = 0; i < SANTA_JUMP.length; i ++)
			SANTA_JUMP[i] = new Image(path + "santa\\Jump (" + (i + 1) + ").png");
	}
	private void populateSantaSlide() {
		for(int i = 0; i < SANTA_SLIDE.length; i ++)
			SANTA_SLIDE[i] = new Image(path + "santa\\Slide (" + (i + 1) + ").png");
	}
}