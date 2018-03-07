package ee222yb_assign2.javaFX;

import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Yahtzee extends Application {
	private static final int MAX_ROLLS = 3;
	
	private static final int NR_OF_DICE = 5;
	private static final double CUT_SIZE = 78;
	private static final Image DICE_IMAGE = new Image("ee222yb_assign2\\JavaFX\\img\\dice.jpg");
	
	private int[] currentBoard = new int[NR_OF_DICE]; // Stores the die values of the current board, left to right
	private int rollsRemaining = MAX_ROLLS;
	private CheckBox[] checkboxes = new CheckBox[NR_OF_DICE];
	
	public static void main(String[] args) {
		launch();
	}
	
	public void start(Stage stage) {
		Label heading = new Label("Yahtzee");
		heading.setFont(new Font(40));
		
		GridPane main = new GridPane();
		main.setHgap(5);
		main.setVgap(5);
		
		// Creating place holders for the dice when the game starts. No point showing dice you can't use
		for(int i = 0; i < NR_OF_DICE; i ++)
 			main.add(new Rectangle(CUT_SIZE, CUT_SIZE, Color.WHITESMOKE), i, 0); // Shows blank rectangles instead of dice
		
		// Check-boxes
		for(int i = 0; i < checkboxes.length; i ++) {
			checkboxes[i] = new CheckBox();
			main.add(checkboxes[i], i, 1);
			checkboxes[i].setDisable(true);
			GridPane.setHalignment(checkboxes[i], HPos.CENTER);
		}
		
		// Buttons and result
		Button roll = new Button("Roll the dice!");
		Button stay = new Button("Stay");
		String defaultMsg = "Press \"Roll the dice!\" to start the game. You have " + rollsRemaining + " rolls left.";
		Label result = new Label(defaultMsg);
		Button reset = new Button("Reset game");
		reset.setDisable(true);
		stay.setDisable(true);
		
		VBox text = new VBox(result);
		
		GridPane buttons = new GridPane();
		buttons.setHgap(10);
		buttons.setVgap(10);
		buttons.add(roll, 0, 0);
		buttons.add(stay, 1, 0);
		buttons.add(reset, 0, 2);
		
		// Scene / stage
		VBox layout = new VBox(heading, main, text, buttons);
		layout.setPadding(new Insets(5));
		layout.setSpacing(10);

		Scene scene = new Scene(layout); //, 420, 270);
		stage.setScene(scene);
		stage.setTitle("Yahtzee");
		stage.setResizable(false);
		stage.show();
		
		// Roll dice event
		roll.setOnAction(event -> {
			if(rollsRemaining -- > 0) {
				for(int i = 0; i < NR_OF_DICE; i ++) {
					// Enables check-boxes, stay, and reset button (code only executes on the first roll)
					if(rollsRemaining == MAX_ROLLS - 1) {
						checkboxes[i].setDisable(false);
						reset.setDisable(false);
						stay.setDisable(false);
					}
					// Controls which dice should be rolled depending on which check-boxes are checked
					if(!checkboxes[i].isSelected())
						main.add(getRandomDie(i), i, 0);
				}
				result.setText("You have " + rollsRemaining + " roll(s) left.");
			}
			// If user has no more rolls, present results
			if(rollsRemaining == 0) {
				result.setText(getDiceResult());
				roll.setDisable(true);
				stay.setDisable(true);
			}
		});
		
		// Stay event
		stay.setOnAction(event -> {
			rollsRemaining = 0;
			result.setText(getDiceResult());
			roll.setDisable(true);
			stay.setDisable(true);
		});

		// Reset event
		reset.setOnAction(event -> {
			rollsRemaining = MAX_ROLLS;
			result.setText(defaultMsg);
			roll.setDisable(false);
			reset.setDisable(true);
			stay.setDisable(true);
			
			for(CheckBox box : checkboxes) {
				box.setSelected(false);
				box.setDisable(true);
			}
			for(int i = 0; i < NR_OF_DICE; i ++)
				main.add(new Rectangle(CUT_SIZE, CUT_SIZE, Color.WHITESMOKE), i, 0); // Blank rectangles
		});	
	}
	
	private ImageView getRandomDie(int diePositionIndex) {
		double[] startPos = { 0, CUT_SIZE, CUT_SIZE * 2 };
		
		int dieValue = new Random().nextInt(6);
		currentBoard[diePositionIndex] = dieValue + 1;
		
		// Calculates what to cut from the image
		int y = dieValue / 3;
		int x = dieValue;
		if(dieValue > 2)
			x -= 3;
		
		ImageView die = new ImageView(DICE_IMAGE);
		die.setViewport(new Rectangle2D(startPos[x], startPos[y], CUT_SIZE, CUT_SIZE));
		
		return die;
	}
	
	private String getDiceResult() {
		
		// Counts the number of occurrences of the same number, i.e. a roll of [ 1 1 4 5 6 ] would equal to [ 2, 0, 0, 1, 1, 1 ]
		int[] countedValues = new int[6];
		for(int i = 0; i < currentBoard.length; i ++) {
			countedValues[currentBoard[i] - 1] ++;
			checkboxes[i].setDisable(true);
		}
		
		int[] sortedValues = Arrays.copyOf(countedValues, countedValues.length);
		Arrays.sort(sortedValues); // Since we don't care about denominations, sorting the list is an easy way to determine the highest number of occurrences of a denomination
		
		String msg = "Nothing but a busted straight."; // default msg
		int last = sortedValues.length - 1;
		if(sortedValues[last] == 5)
			msg = "YAHTZEE!!!";
		else if(sortedValues[last] == 4)
			msg = "Four of a kind!";
		else if(sortedValues[last] == 3) {
			if(sortedValues[last] == 3  && sortedValues[last - 1] == 2) // Full house > three of a kind
				msg = "Full house!";
			else
				msg = "Three of a kind!";
		}
		else if(isConsecutive(countedValues, 5))
				msg = "Large Straight!";
		else if(isConsecutive(countedValues, 4))
			msg = "Small straight.";
		else if(sortedValues[last] == 2)
			msg = "Pair.";
		
		return msg;
	}
	
	// Checks if an int array has n consecutive index values > 0
	private boolean isConsecutive(int[] arr, int n) {
		int count = 0, i = 0;
		while(i < arr.length && n - count <= arr.length - i) { // loops through the array as long there are enough indexes left for the call to return true
			if(arr[i] > 0)
				count ++;
			else
				count = 0;
			if(count == n)
				return true;
			i ++;
		}
		return false;
	}
}
