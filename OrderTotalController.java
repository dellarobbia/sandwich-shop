package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class OrderTotalController implements Initializable 
{
	@FXML Label orderDetailsLabel;
	
	Sandwich orderSandwich;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		orderDetailsLabel.setText("Loading Order Details...");
	}
	
	public void setOrderDetailsLabel(String orderDetails)
	{
		orderDetailsLabel.setText(orderDetails);
	}
	
	public void closeProgram(ActionEvent event)
	{
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

}
