package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OrderMenuController implements Initializable 
{
	//Checkboxes
	@FXML private CheckBox lettuceCheckBox;
	@FXML private CheckBox tomatoCheckBox;
	@FXML private CheckBox pickleCheckBox;
	@FXML private CheckBox swissCheeseCheckBox;
	@FXML private CheckBox americanCheeseCheckBox;
	
	//ChoiceBoxes
	@FXML private ComboBox<String> meatChoiceBox;
	@FXML private ComboBox<String> breadChoiceBox;
//	@FXML private ChoiceBox<String> meatChoiceBox;
//	@FXML private ChoiceBox<String> breadChoiceBox;
	
	//Labels
	@FXML private Label sandwichOrderLabel;
	@FXML private Label selectedBreadLabel;
	@FXML private Label selectedMeatLabel;
	
	//Buttons
	@FXML private Button calcTotalButton;
	@FXML private Button confirmOrderButton;
	
	//Lists
	private ArrayList<Ingredient> selectableIngredients;
	private ArrayList<Bread> selectableBreads;
	private ArrayList<Meat> selectableMeats;
	private ArrayList<CheckBox> ingredientCheckBoxes;
	
	//Properties
	private Sandwich orderSandwich;
	
	//Constants
	//Ingredient Prices
	private final double basicIngredientPrice = 0.15;
	//Bread Prices
	private final double basicBreadPrice = 0.5;
	private final double premiumBreadPrice = 1.0;
	//Meat Prices
	private final double basicMeatPrice = 2.25;
	private final double premiumMeatPrice = 3.25;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		sandwichOrderLabel.setText("$0.00");
		initializeIngredients();
		initializeBreads();
		initializeMeats();
	}
	
	//Initialize all selectable ingredients
	private void initializeIngredients()
	{
		//initialize the selectableIngredients list
		selectableIngredients = new ArrayList<Ingredient>();
		ingredientCheckBoxes = new ArrayList<CheckBox>();
		//create each ingredient object as selectable from the menu
		addIngredient("Lettuce", basicIngredientPrice, lettuceCheckBox);
		addIngredient("Tomato", basicIngredientPrice, tomatoCheckBox);
		addIngredient("Pickle", basicIngredientPrice, pickleCheckBox);
		addIngredient("Swiss Cheese", basicIngredientPrice, swissCheeseCheckBox);
		addIngredient("American Cheese", basicIngredientPrice, americanCheeseCheckBox);
	}
	
	
	
	//Initialize all selectable breads
	private void initializeBreads()
	{
		//initialize the selectableBreads list
		selectableBreads = new ArrayList<Bread>();
		//create each bread object as selectable from the menu
		addBread("White", basicBreadPrice);
		addBread("Full Grain", basicBreadPrice);
		addBread("Rye", premiumBreadPrice);
		
		for(Bread bread : selectableBreads)
			breadChoiceBox.getItems().add(bread.getIngredientName());
		breadChoiceBox.setValue(breadChoiceBox.getItems().get(0));
		breadSelectionUpdate();
	}
	
	//Initialize all selectable meats
	private void initializeMeats()
	{
		selectableMeats = new ArrayList<Meat>();
		
		addMeat("Ham", basicMeatPrice);
		addMeat("Turkey", basicMeatPrice);
		addMeat("Roast Beef", premiumMeatPrice);
		addMeat("Veggie", premiumMeatPrice);
		
		for(Meat meat : selectableMeats)
			meatChoiceBox.getItems().add(meat.getIngredientName());
		meatChoiceBox.setValue(meatChoiceBox.getItems().get(0));
		meatSelectionUpdate();
	}
	
	//constructs Ingredient objects and adds them to the selectableIngredient list
	private void addIngredient(String ingredientName, double ingredientPrice, CheckBox ingredientCheckBox)
	{
		Ingredient newIngredient = new Ingredient(ingredientName, ingredientPrice);
		
		ingredientCheckBox.setText(newIngredient.getIngredientName());
		selectableIngredients.add(newIngredient);
		ingredientCheckBoxes.add(ingredientCheckBox);
	}
	private void addBread(String breadName, double breadPrice)
	{
		Bread newBread = new Bread(breadName, breadPrice);
		selectableBreads.add(newBread);
	}
	private void addMeat(String meatName, double meatPrice)
	{
		Meat newMeat = new Meat(meatName, meatPrice);
		selectableMeats.add(newMeat);
	}
	
	//Actions
	public void calcTotal()
	{
		meatSelectionUpdate();
		Meat meatSelection = matchMeat(meatChoiceBox.getValue().toString());
		breadSelectionUpdate();
		Bread breadSelection = matchBread(breadChoiceBox.getValue().toString());
		ArrayList<Ingredient> ingredientSelections = matchIngredients();
		Sandwich orderSandwich = new Sandwich(meatSelection, breadSelection, ingredientSelections);
		
		setOrderSandwich(orderSandwich);
		sandwichOrderLabel.setText(orderSandwich.sandwichCostString());
	}
	
	public void confirmOrderButtonPushed(ActionEvent event) throws IOException
	{
		//Show OrderTotal scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderTotal.fxml"));
		Parent orderParent = loader.load();
		Scene orderTotalScene = new Scene(orderParent);
		
		
		//pass order details to new scene
		OrderTotalController orderTotalController = loader.getController();
		orderTotalController.setOrderDetailsLabel(orderSandwich.toString());
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(orderTotalScene);
		window.show();
		
	}
	
	public void meatSelectionUpdate()
	{
		Meat meatSelection = matchMeat(meatChoiceBox.getValue().toString());
		
		selectedMeatLabel.setText(meatSelection.toString());
	}
	public void breadSelectionUpdate()
	{
		Bread breadSelection = matchBread(breadChoiceBox.getValue().toString());

		selectedBreadLabel.setText(breadSelection.toString());
	}
	
	
	//Matching the strings from the selections to their objects
	private Bread matchBread(String selectedBreadString)
	{
		for (Bread bread : selectableBreads)
		{
			if(selectedBreadString == bread.getIngredientName())
				return bread;
		}
		
		breadChoiceBox.setValue(selectableBreads.get(0).getIngredientName());
		return selectableBreads.get(0);
	}
	private Meat matchMeat(String selectedMeatString)
	{
		for (Meat meat : selectableMeats)
		{
			if(selectedMeatString == meat.getIngredientName())
				return meat;
		}
		
		meatChoiceBox.setValue(selectableMeats.get(0).getIngredientName());
		return selectableMeats.get(0);
	}
	private ArrayList<Ingredient> matchIngredients()
	{
		ArrayList<Ingredient> orderIngredients = new ArrayList<Ingredient>();
		
		
		for (CheckBox checkBox : ingredientCheckBoxes)
		{
			if(checkBox.isSelected() == true)
				for (Ingredient ingredient : selectableIngredients)
				{
					if (ingredient.getIngredientName() == checkBox.getText())
						orderIngredients.add(ingredient);
				}
		}
		
		if(orderIngredients.size() == 0)
			orderIngredients.add(new Ingredient("No Add-Ons", 0));
		return orderIngredients;
	}
	
	public Sandwich getOrderSandwich()
	{
		return orderSandwich;
	}
	public void setOrderSandwich(Sandwich newSandwich)
	{
		this.orderSandwich = newSandwich;
	}

}
