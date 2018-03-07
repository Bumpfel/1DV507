package ee222yb_assign2.javaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CompoundInterest extends Application {

	public static void main(String[] args) {
		launch();
	}
	
	public void start(Stage stage) {
		// Heading
		VBox top = new VBox();
		Label heading = new Label("Compound Interest");
		heading.setFont(new Font(27));
		top.getChildren().add(heading);
	
		// Labels, text fields and buttons
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		
		TextField amountField = new TextField();
		TextField interestField = new TextField();
		TextField yearsField = new TextField();
		Button calculate = new Button("Calculate");
		Button clear = new Button("Clear");
		
		grid.add(new Label("Start amount:"), 0, 0);
		grid.add(new Label("Interest:"), 0, 1);
		grid.add(new Label("Number of years:"), 0, 2);
		grid.add(amountField, 1, 0);
		grid.add(interestField, 1, 1);
		grid.add(yearsField, 1, 2);
		grid.add(calculate, 0, 3);
		grid.add(clear, 0, 4);
		
		// Results
		VBox bottom = new VBox();
		Label result = new Label();
		bottom.getChildren().add(result);

		// Layout and Scene
		VBox layout = new VBox();
		layout.setPadding(new Insets(5));
		layout.getChildren().addAll(top, grid, bottom);
		
		Scene scene = new Scene(layout);
//		stage.setHeight(270);
//		stage.setWidth(300);
		stage.setScene(scene);
		stage.setTitle("Compound Interest");
		stage.setResizable(false);
		stage.show();
		
		// Calculate event (not quite done)
		calculate.setOnAction(event -> {
			try {
				double amount = Double.parseDouble(amountField.getText());
				double interest = Double.parseDouble(interestField.getText());
				double years = Double.parseDouble(yearsField.getText());
				if(years <= 0)
					throw new NumberFormatException("years");
				
				long calculation = (long) Math.round(amount * Math.pow((1 + (interest / 100)), years));
				if(calculation >= Long.MAX_VALUE)
					throw new NumberFormatException("overflow");
				result.setText("In total that will be " + calculation);
			}
			catch(NumberFormatException ex) {
//				result.wrapTextProperty();
				if(ex.getMessage() == "overflow")
					result.setText("Error: Cannot handle numbers higher than " + Long.MAX_VALUE);
				else if(ex.getMessage() == "empty String")
					result.setText("Error: Fields cannot be empty");
				else if(ex.getMessage() == "years")
					result.setText("Error: Years must be greater than 0");
				else
//					result.setText(ex.getMessage());
					result.setText("Error: Fields may only contain numbers");
			}
		});
		
		// Clear event
		clear.setOnAction(event -> {
			amountField.clear();
			interestField.clear();
			yearsField.clear();
			result.setText("");
		});
	}

}
