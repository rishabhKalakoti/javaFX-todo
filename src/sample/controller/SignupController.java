package sample.controller;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SignupController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton signupButton;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXTextField uname;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private ImageView backButton;

    @FXML
    public void backToLogin(MouseEvent event) throws Exception
	    {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/Login.fxml"));
    		rootPane.getChildren().setAll(pane);
	    }
    public void Signup(ActionEvent event) throws Exception
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
    	
    	Statement s = con.createStatement();
    	System.out.println("insert into users values('"+fname.getText()+"' , '"+lname.getText()+"', '"+uname.getText()+"', '"+pass.getText()+"')");
    	s.executeUpdate("insert into users values('"+fname.getText()+"' , '"+lname.getText()+"', '"+uname.getText()+"', '"+pass.getText()+"')");
    	AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/Login.fxml"));
		rootPane.getChildren().setAll(pane);
    }

}
