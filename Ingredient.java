package application;

import java.text.DecimalFormat;

public class Ingredient 
{
	private String ingredientName;
	private double ingredientPrice;
	
	public Ingredient(String ingredientName, double ingredientPrice)
	{
		setIngredientName(ingredientName);
		setIngredientPrice(ingredientPrice);
	}
	
	public String getIngredientName()
	{
		return ingredientName;
	}
	public void setIngredientName(String ingredientName)
	{
		this.ingredientName = ingredientName;
	}
	
	public double getIngredientPrice()
	{
		return ingredientPrice;
	}
	public void setIngredientPrice(double ingredientPrice)
	{
		this.ingredientPrice = ingredientPrice;
	}
	
	public String toString()
	{
		DecimalFormat currency = new DecimalFormat("#,##0.00");
		
		return ingredientName + " ($" + currency.format(ingredientPrice) + ")";
	}
}
