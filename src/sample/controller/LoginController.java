package sample.controller;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
public class LoginController {
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;
	    
	    @FXML
	    private AnchorPane rootPane;

	    
	    @FXML
	    private JFXTextField loginUsername;

	    @FXML
	    private JFXPasswordField loginPassword;

	    @FXML
	    private JFXButton loginButton;

	    @FXML
	    private JFXButton loginSignupButton;
	    
	    public void Login(ActionEvent event) throws Exception
	    {
	    	if(loginUsername.getText().equals("superuser") && loginPassword.getText().equals("pass") )
	    	{
	    		AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/userview.fxml"));
	    		rootPane.getChildren().setAll(pane);
	    		
	    	}
	    	else
	    	{
	    		System.out.println("Login Failed");
	    	}
	    	
	    }
	    
	       
	       }
	    

