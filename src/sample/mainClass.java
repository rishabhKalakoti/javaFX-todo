package sample;
import javafx.application.*;

import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class mainClass extends Application  {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource( "/sample/view/login.fxml"));
		primaryStage.setTitle("TheWeek");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
	}
	public static void main(String args[])
	{
		launch(args);
	}
}
