package sample.controller;

import java.io.*;
import java.io.IOException;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class userviewController {

	//@FXML
    //private ResourceBundle resources;

    //@FXML
   // private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane holderPane;

    @FXML
    private AnchorPane dataPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView userviewAdd;

    @FXML
    private ImageView profile;

    @FXML
    private Label username;

    @FXML
    private JFXHamburger ham1;

        
    @FXML
    private static HamburgerSlideCloseTransition trans;
    
    @FXML 
    private VBox box;
    private static boolean loaded = false;
    @FXML	
    public void initialize()
    {
    	if(loaded==false)
    	{
    		loaded=true;
	    	System.out.println(ham1);
	    	trans = new HamburgerSlideCloseTransition(ham1);
	    	trans.setRate(-1);
	    	try {
	    		VBox box = FXMLLoader.load(getClass().getResource("/sample/view/drawerContent.fxml"));
	    		drawer.setSidePane(box);
	    	}
			catch(Exception ex)
	    	{
				;
				//ex.printStackTrace();
	    	}
    	}
    	
    }
    
    @FXML
    void clickedAdd(MouseEvent event)  {
    	System.out.println(dataPane);
    	try {
    	AnchorPane data = FXMLLoader.load(getClass().getResource( "/sample/view/addTask.fxml"));
		dataPane.getChildren().setAll(data);
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Error!");
    	}
    }
    
    @FXML
    void clickedHam(MouseEvent event) {
    	
    	trans.setRate(trans.getRate()*-1);
    	trans.play();
    	if(drawer.isOpened())
    	{
    		drawer.close();
    	}
    	else
    		drawer.open();
		
    }
    
    @FXML
    void clickProfile(MouseEvent event) throws Exception {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/profile.fxml"));
		rootPane.getChildren().setAll(pane);
    }

}
