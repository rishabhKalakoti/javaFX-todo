package sample.controller;

import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.control.Label;

public class userviewController {

	//@FXML
    //private ResourceBundle resources;

    //@FXML
   // private URL location;
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
    private AnchorPane rootPane;

    @FXML
    private AnchorPane holderPane;

    @FXML
    public AnchorPane dataPane;
    
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
    
    private static boolean loaded = false;
    
    @FXML	
    public void initialize() throws Exception
    {
    	
    	if(loaded==false)
    	{
    		loaded=true;
	    	System.out.println(ham1);
	    	trans = new HamburgerSlideCloseTransition(ham1);
	    	trans.setRate(-1);
    		VBox box = FXMLLoader.load(getClass().getResource("/sample/view/drawerContent.fxml"));
    		drawer.setSidePane(box);
	    	
    	}
    	
    }
    EventHandler<MouseEvent> removeFromList = new EventHandler<MouseEvent>() 
    {
        @Override
        public void handle( final MouseEvent ME ) 
        {
            Object obj = ME.getSource();  // you can also try ME.getTarget()

            if ( obj instanceof Button )
            {
                String id = (String)((Button) obj).getUserData();
                try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
					
					Integer i  = Integer.parseInt(id);
					System.out.println("delete from `notes` where `notes`.`id`=" + i);
					Statement s = con.createStatement();
					
	            	s.executeUpdate("delete from `notes` where `notes`.`id`=" + i);
	            	updateAll();
                } catch (Exception e) {
					System.out.println("Query Error.");
				}
                
            	
            }
        }
    };
    void updateAll() throws Exception
    {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
    	
    	// -----------
    	VBox vb = new VBox();
    	Statement s = con.createStatement();
    	ResultSet rs = s.executeQuery("select * from notes where user='" + globals.username + "' order by date");
    	// HBox outer[] = new HBox[news.size()];
    	while(rs.next())
    	{
    		System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
    		HBox hb = new HBox();
    		Text l = new Text();
    		l.setText(rs.getString(3) + " (" + rs.getString(4) + ")");
    		l.setWrappingWidth(350);
    		hb.getChildren().add(l);
    		Button rem = new Button();
    		rem.setText("Remove");
    		String id = rs.getString(1);
    		rem.setUserData(id);
    		rem.setOnMouseClicked((removeFromList));
    		hb.setSpacing(5);
            hb.setPadding(new Insets(5, 5, 5, 5));
            hb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
    		hb.getChildren().add(rem);
    		vb.getChildren().add(hb);
    		
    	}
    	ScrollPane scroll = new ScrollPane();

    	scroll.setMaxHeight(250);
    	scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    	scroll.setMinWidth(460);
    	vb.setMaxWidth(450);
    	scroll.setContent(vb);
    	AnchorPane dataPane =(AnchorPane) box.getScene().lookup("#dataPane");
    	System.out.println(scroll);
    	System.out.println(dataPane);
    	dataPane.getChildren().setAll(scroll);

    }
    @FXML
    void loadAll(ActionEvent event) throws Exception{
    	updateAll();
    }

    @FXML
    void loadToday(ActionEvent event) {
    	;
    }
    
    @FXML
    void clickedAdd(MouseEvent event) throws Exception {
    	//System.out.println(getClass().getResource( "/sample/view/addTask.fxml"));
    	System.out.println(dataPane);
    	AnchorPane data= FXMLLoader.load(getClass().getResource( "/sample/view/addTask.fxml"));
		dataPane.getChildren().setAll(data);
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
