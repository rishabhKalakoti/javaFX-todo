package sample.controller;

import sample.controller.userviewController; 
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
public class drawerContentController {

    @FXML
    private VBox box;

    @FXML
    private AnchorPane nav;

    @FXML
    private JFXButton today;

    @FXML
    private JFXButton allTasks;

    @FXML
    private JFXButton labels;
    
    @FXML
    private JFXButton signout;
    
    @FXML
    private AnchorPane dataPane;

    
    @FXML
    void loadAll(ActionEvent event) throws Exception{
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
    	
    	// -----------
    	VBox vb = new VBox();
    	Statement s = con.createStatement();
    	ResultSet rs = s.executeQuery("select * from notes where user='" + globals.username + "'");
    	// HBox outer[] = new HBox[news.size()];
    	while(rs.next())
    	{
    		System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
    		Label l = new Label();
    		l.setText(rs.getString(2) + " " + rs.getString(3));
    		vb.getChildren().add(l);
    		
    	}
    	ScrollPane scroll = new ScrollPane();
    	scroll.setContent(vb);
    	System.out.println(Parent.class);
    	
    	System.out.println(scroll);
    	System.out.println(dataPane);
    	//dataPane.getChildren().setAll(scroll);
    }

    @FXML
    void loadToday(ActionEvent event) {
    	;
    }

}
