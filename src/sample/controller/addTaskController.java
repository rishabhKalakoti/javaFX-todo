package sample.controller;

import javafx.fxml.FXML;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.layout.AnchorPane;

import javafx.scene.input.MouseEvent;


public class addTaskController {
	@FXML
    private AnchorPane addNote;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXTextArea noteDesc;

    @FXML
    private JFXDatePicker noteDate;

	// Event Listener on JFXButton[#addBtn].onMouseClicked
	@FXML
	public void addNoteFunc(MouseEvent event) throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/theweek","root","");
    	
    	Statement s = con.createStatement();
    	s.executeUpdate("insert into notes(user,description,date) values('"+ globals.username +"', '"+noteDesc.getText()+"', '"+noteDate.getValue()+"')");
    	System.out.println("Note Inserted.");
		
	}
}
