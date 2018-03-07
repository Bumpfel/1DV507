package ee222yb_assign3.javaFX;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RunningSanta extends Application {

	private static final String PATH = "ee222yb_assign3\\javaFX\\sprites\\";
	private static final double SPEED_MOD = 0.2; // Change this to adjust speed
	private static final int WIDTH = 1200, HEIGHT = 590, UPDATE_FREQUENCY = 50;

	private static double xSpeed = (double) SPEED_MOD * UPDATE_FREQUENCY;
	
	private ImageView santa;
	private int santaIndex = 0;
	
	public static void main(String[] args) {
		launch();
	}

	public void start(Stage stage) {
		Image bgImg = new Image(PATH + "BG.png");
		ImageView background = new ImageView(bgImg);
		background.setFitWidth(WIDTH);
		background.setPreserveRatio(true);

		ImageView snowman = new ImageView(new Image(PATH + "SnowMan.png"));
		snowman.setFitWidth(100);
		snowman.setPreserveRatio(true);
		snowman.setY(405);
		
		Image[] runningSanta = new Image[11];
		for(int i = 0; i < runningSanta.length; i ++)
			runningSanta[i] = new Image(PATH + "\\santa\\Run (" + (i + 1) + ").png");
		santa = new ImageView(runningSanta[0]);
		santa.setFitWidth(200);
		santa.setPreserveRatio(true);
		santa.setY(390);
		santa.setX(0);
		
		ImageView ground = new ImageView(new Image(PATH + "ground.png"));
		ground.setFitWidth(WIDTH);
		ground.setPreserveRatio(true);
		ground.setY(510);
		
		ImageView tree = new ImageView(new Image(PATH + "Tree_2.png"));
		tree.setFitWidth(100);
		tree.setPreserveRatio(true);
		tree.setX(1100);
		tree.setY(390);
		
		Group root = new Group(background, ground, snowman, tree, santa);
		
		stage.setScene(new Scene(root, WIDTH, HEIGHT));
		stage.setTitle("Running Santa!");
		stage.setResizable(false);
		stage.show();

		KeyFrame k = new KeyFrame(Duration.millis(UPDATE_FREQUENCY), event -> {
			santa.setImage(runningSanta[santaIndex ++]);
			
			santa.setX(santa.getX() + xSpeed);
			if(santaIndex == runningSanta.length)
				santaIndex = 0;
			if(santa.getX() >= WIDTH - 220){
				santa.setScaleX(-1);
				santa.setX(santa.getX() - 33);
				xSpeed = -xSpeed;
			}
			else if(santa.getX() <= 15){
				santa.setScaleX(1);
				santa.setX(santa.getX() + 33);
				xSpeed = -xSpeed;
			}
		});
		Timeline t = new Timeline(k);
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();
	}
	
	
}
