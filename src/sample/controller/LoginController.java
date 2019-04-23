package sample.controller;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
	    @FXML
	    public void initialize()
	    {
	    	RequiredFieldValidator validator = new RequiredFieldValidator();
	    	loginUsername.getValidators().add(validator);
	    	loginPassword.getValidators().add(validator);
	    	validator.setMessage("NO INPUT");
	    	
	    }
	    public void Login(ActionEvent event) throws Exception
	    {
	    	loginUsername.validate();
	    	loginPassword.validate();
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	//System.out.println("Ok");
	    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
	    	//System.out.println("Ok");
	    	Statement s = con.createStatement();
	    	
	    	ResultSet rs = s.executeQuery("select * from users where uname='"+loginUsername.getText()+"' and pass='"+loginPassword.getText()+"'");
	    	
	    	if(rs.next())
	    	{
	    		globals.username = loginUsername.getText();
	    		AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/userview.fxml"));
	    		rootPane.getChildren().setAll(pane);
	    		
	    	}
	    	else
	    	{
	    		System.out.println("Login Failed");
	    	}
	    	
	    }
	    
	    public void GoToSignup(ActionEvent event) throws Exception
	    {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/Signup.fxml"));
    		rootPane.getChildren().setAll(pane);
	    }
	    
	       
	       }
	    

