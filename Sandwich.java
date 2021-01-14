package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sandwich 
{
	private String sandwichName;
	private Meat sandwichMeat;
	private Bread sandwichBread;
	private ArrayList<Ingredient> sandwichIngredients;
	private double sandwichCost;
	
	//Pre-Named sandwich constructor
	public Sandwich(String sandwichName, Meat sandwichMeat, Bread sandwichBread, ArrayList<Ingredient> sandwichIngredients)
	{
		setSandwichName(sandwichName);
		setSandwichMeat(sandwichMeat);
		setSandwichBread(sandwichBread);
		setSandwichIngredients(sandwichIngredients);
		//calculate the cost based on selected ingredients
		setSandwichCost(calcSandwichCost());
	}
	//Un-Named sandwich constructor
	public Sandwich(Meat sandwichMeat, Bread sandwichBread, ArrayList<Ingredient> sandwichIngredients)
	{
		setSandwichMeat(sandwichMeat);
		setSandwichBread(sandwichBread);
		setSandwichIngredients(sandwichIngredients);
		setSandwichCost(calcSandwichCost());
		setSandwichName(sandwichMeat.getIngredientName() + " Sandwich");
	}

	public String getSandwichName() 
	{
		return sandwichName;
	}
	public void setSandwichName(String sandwichName) 
	{
		this.sandwichName = sandwichName;
	}
	
	public Meat getSandwichMeat() 
	{
		return sandwichMeat;
	}
	public void setSandwichMeat(Meat sandwichMeat) 
	{
		this.sandwichMeat = sandwichMeat;
	}

	public Bread getSandwichBread() 
	{
		return sandwichBread;
	}
	public void setSandwichBread(Bread sandwichBread) 
	{
		this.sandwichBread = sandwichBread;
	}

	public ArrayList<Ingredient> getSandwichIngredients() 
	{
		return sandwichIngredients;
	}
	public void setSandwichIngredients(ArrayList<Ingredient> sandwichIngredients) 
	{
		this.sandwichIngredients = sandwichIngredients;
	}
	
	public double getSandwichCost()
	{
		return sandwichCost;
	}
	public void setSandwichCost(double sandwichCost)
	{
		this.sandwichCost = sandwichCost;
	}
	
	public double calcSandwichCost()
	{
		double meatCost = sandwichMeat.getIngredientPrice();
		double breadCost = sandwichBread.getIngredientPrice();
		double ingredientCost = 0;
		for (Ingredient ingredient : sandwichIngredients)
		{
			ingredientCost += ingredient.getIngredientPrice();
		}
		
		double sandwichCost = meatCost + breadCost + ingredientCost;
		
		return sandwichCost;
	}
	
	public String sandwichCostString()
	{
		DecimalFormat currency = new DecimalFormat("#,##0.00");
		return currency.format(calcSandwichCost());
	}
	
	public String sandwichIngredientsString()
	{
		String ingredientString = "";
		for(Ingredient ingredient : getSandwichIngredients())
		{
			ingredientString += "- " + ingredient.toString() + "\n";
		}
		return ingredientString;
	}
	
	public String toString()
	{
		String sandwichString =
				getSandwichName() + "\n" +
				"Bread: " + getSandwichBread().toString() + "\n" +
				"Meat: " + getSandwichMeat().toString() + "\n" +
				"Add-Ons:\n" + sandwichIngredientsString() +
				"Total: $" + sandwichCostString();
		return sandwichString;
	}
}
