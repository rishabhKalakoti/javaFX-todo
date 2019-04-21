package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class profileController {
	@FXML
	private AnchorPane rootPane;
	@FXML
	private AnchorPane pane;
	@FXML
    private ImageView userviewAdd;

    @FXML
    private ImageView clickprofile;

    @FXML
    private Label username;
    


    @FXML
    void clickProfile(MouseEvent event) throws Exception{
    	AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/userview.fxml"));
		rootPane.getChildren().setAll(pane);
    		
    }
    @FXML
    void signout(ActionEvent event) throws Exception {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/login.fxml"));
		rootPane.getChildren().setAll(pane);
    }

}
