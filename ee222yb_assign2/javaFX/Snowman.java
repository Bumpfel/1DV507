package ee222yb_assign2.javaFX;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Snowman extends Application {

	public static void main(String[] args) {
		launch();
	}
	
	public void start(Stage stage) {
		Group picture = new Group();
		
		//Background
		Rectangle ground = new Rectangle(800, 500, Color.WHITE);
		Rectangle sky = new Rectangle(800, 400, Color.DEEPSKYBLUE); // Letting this overlap the ground instead of setting positions
		Circle sun = new Circle(675, 100, 70, Color.YELLOW);
		
		Group background = new Group(ground, sky, sun);
		
		//Snowman
		Circle head = new Circle(400, 215, 25, Color.WHITE);
		Circle torso = new Circle(400, 270, 40, Color.WHITE);
		Circle bottom = new Circle(400, 350, 60, Color.WHITE);
		
		Circle button1 = new Circle(400, 250, 3, Color.BLACK);
		Circle button2 = new Circle(400, 265, 3, Color.BLACK);
		Circle button3 = new Circle(400, 280, 3, Color.BLACK);
		
		Circle rightEye = new Circle(390, 210, 3);
		Circle leftEye = new Circle(410, 210, 3);
		Line mouth = new Line(392, 219, 408, 219);
		
		Rectangle hatBottom = new Rectangle(373, 193, 60, 6);
		Rectangle hatTop = new Rectangle(385, 175, 40, 20);
		Rectangle hatTrim = new Rectangle(384, 191, 40, 3);
		hatBottom.setRotate(10);
		hatTop.setRotate(10);
		hatTrim.setRotate(10);
		hatTrim.setFill(Color.BLUEVIOLET);
		Group hat = new Group(hatBottom, hatTop, hatTrim);

		Line rightUpperArm = new Line(367, 255, 330, 240);
		Line rightForearm = new Line(330, 240, 320, 210);
		Line cane = new Line(270, 230, 365, 200);
		Line caneTip = new Line(270, 230, 275, 228.5);
		rightUpperArm.setStrokeWidth(2);
		rightForearm.setStrokeWidth(2);
		cane.setStrokeWidth(5);
		caneTip.setStrokeWidth(5);
		caneTip.setStroke(Color.WHITE);
		Group rightArm = new Group(rightUpperArm, rightForearm, cane, caneTip);
		
		Line leftUpperArm = new Line(433, 255, 465, 260);
		Line leftForearm = new Line(465, 260, 490, 245);
		leftUpperArm.setStrokeWidth(2);
		leftForearm.setStrokeWidth(2);
		Group leftArm = new Group(leftUpperArm, leftForearm);
		
		Group snowman = new Group(head, torso, bottom, rightEye, leftEye, hat, mouth, button1, button2, button3, rightArm, leftArm);

		picture.getChildren().addAll(background, snowman);
		Scene scene = new Scene(picture, 800, 500);
		stage.setTitle("Snowman");
		stage.setScene(scene);
		stage.show();
	}

}
