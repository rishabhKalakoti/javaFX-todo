package sample.controller;
import sample.model.*;
import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.skins.JFXButtonSkin;
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
    private JFXButton week;
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
    		//updateAll();
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
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	
    	// -----------
    	VBox vb = new VBox();
    	vb.setPrefWidth(460);
    	Statement s = con.createStatement();
    	ResultSet rs = s.executeQuery("select * from notes where user='" + globals.username + "' order by date");
    	// HBox outer[] = new HBox[news.size()];
    	while(rs.next())
    	{
    		System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
    		HBox hb = new HBox();
    		hb.setPrefWidth(460);
    		Label l = new Label();
    		l.setText(rs.getString(3) + " (" + rs.getString(4) + ")");
    		l.setPrefWidth(350);
    		l.setPrefHeight(28);
    		//l.setEditable(false);
    		l.setStyle("-fx-font-family:Georgia;-fx-font-size: 14px;");
    		l.setFocusTraversable(false);
    		hb.getChildren().add(l);
    		JFXButton rem = new JFXButton();
    		rem.setStyle("-fx-background-color: #039be5; -fx-font-family: Georgia; -fx-font-size:14px;");
    		rem.setText("Remove");
    		rem.setButtonType(ButtonType.RAISED);
    		String id = rs.getString(1);
    		rem.setUserData(id);
    		rem.setOnMouseClicked((removeFromList));
    		hb.setSpacing(5);
            hb.setPadding(new Insets(5, 5, 5, 5));
            hb.setBorder(new Border(new BorderStroke(Color.rgb(3,155,229), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
    		hb.getChildren().add(rem);
    		if(dateFormat.format(date).compareTo(rs.getString(4)) > 0)
    			vb.getChildren().add(new Text("--------------------PENDING--------------------"));
    		
    		vb.getChildren().add(hb);
    		
    	}
    	ScrollPane scroll = new ScrollPane();

    	scroll.setMaxHeight(250);
    	scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    	scroll.setMinWidth(460);
    	vb.setMaxWidth(450);
    	scroll.setContent(vb);
    	System.out.println(box);
    	System.out.println(box.getScene());
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
    void loadToday(ActionEvent event) throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
    	
    	// -----------
    	VBox vb = new VBox();
    	vb.setPrefWidth(450);
    	Statement s = con.createStatement();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	System.out.println(dateFormat.format(date));
    	ResultSet rs = s.executeQuery("select * from notes where user='" + globals.username + "' and date<='"+
    			dateFormat.format(date) +"' order by date");
    	
    	while(rs.next())
    	{
    		//System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
    		HBox hb = new HBox();
    		hb.setPrefWidth(460);
    		Label l = new Label();
    		l.setText(rs.getString(3) + " (" + rs.getString(4) + ")");
    		l.setPrefWidth(350);
    		l.setPrefHeight(28);
    		//l.setEditable(false);
    		l.setFocusTraversable(false);
    		l.setStyle(" -fx-font-family:Georgia; -fx-font-size: 14px;");
    		hb.getChildren().add(l);
    		JFXButton rem = new JFXButton();
    		rem.setText("Remove");
    		rem.setStyle("-fx-background-color: #039be5; -fx-font-family: Georgia; -fx-font-size:14px;");
    		rem.setButtonType(ButtonType.RAISED);
    		String id = rs.getString(1);
    		rem.setUserData(id);
    		rem.setOnMouseClicked((removeFromList));
    		hb.setSpacing(5);
            hb.setPadding(new Insets(5, 5, 5, 5));
            hb.setBorder(new Border(new BorderStroke(Color.rgb(3,155,229), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
    		hb.getChildren().add(rem);
    		System.out.println(dateFormat.format(date));
    		
    		if(dateFormat.format(date).compareTo(rs.getString(4)) > 0)
    			vb.getChildren().add(new Text("--------------------PENDING--------------------"));
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
    void loadWeek(ActionEvent event) throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
    	
    	// -----------
    	VBox vb = new VBox();
    	vb.setPrefWidth(450);
    	Statement s = con.createStatement();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	try{
    	   //Setting the date to the given date
    	   c.setTime(date);
    	}catch(Exception e){
    		e.printStackTrace();
    	 }
    	c.add(Calendar.DAY_OF_MONTH, 7);  
    	//Date after adding the days to the given date
    	String newDate = dateFormat.format(c.getTime());  
    	//System.out.println(dateFormat.format(date));
    	//System.out.println(newDate);
    	ResultSet rs = s.executeQuery("select * from notes where user='" + globals.username + "' and date<'"+
    			newDate +"' and date>='" + 
    			dateFormat.format(date) +"' order by date");
    	
    	while(rs.next())
    	{
    		//System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
    		HBox hb = new HBox();
    		hb.setPrefWidth(460);
    		Label l = new Label();
    		l.setText(rs.getString(3) + " (" + rs.getString(4) + ")");
    		l.setPrefWidth(350);
    		l.setPrefHeight(28);
    		//l.setEditable(false);
    		l.setFocusTraversable(false);
    		l.setStyle(" -fx-font-family:Georgia; -fx-font-size: 14px;");
    		hb.getChildren().add(l);
    		JFXButton rem = new JFXButton();
    		rem.setText("Remove");
    		rem.setStyle("-fx-background-color: #039be5; -fx-font-family: Georgia; -fx-font-size:14px;");
    		rem.setButtonType(ButtonType.RAISED);
    		String id = rs.getString(1);
    		rem.setUserData(id);
    		rem.setOnMouseClicked((removeFromList));
    		hb.setSpacing(5);
            hb.setPadding(new Insets(5, 5, 5, 5));
            hb.setBorder(new Border(new BorderStroke(Color.rgb(3,155,229), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
    		hb.getChildren().add(rem);
    		System.out.println(dateFormat.format(date));
    		
    		if(dateFormat.format(date).compareTo(rs.getString(4)) > 0)
    			vb.getChildren().add(new Text("--------------------PENDING--------------------"));
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
    void signout(ActionEvent event) throws Exception {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource( "/sample/view/login.fxml"));
    	globals.username="";
		AnchorPane rootPane =(AnchorPane) box.getScene().lookup("#rootPane");
    	
    	rootPane.getChildren().setAll(pane);
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
