package application;

import java.util.ArrayList;

public class Order 
{
	private int orderNumber;
	private String customerName;
	private ArrayList<Sandwich> orderSandwiches;
	private double orderPrice;
	
	private final double salesTax = 0.075;
	
	public Order(int orderNumber, String customerName, ArrayList<Sandwich> orderSandwiches) 
	{
		setOrderNumber(orderNumber);
		setCustomerName(customerName);
		setOrderSandwiches(orderSandwiches);
		setOrderPrice(calcOrderPrice());
	}

	public int getOrderNumber() 
	{
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) 
	{
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() 
	{
		return customerName;
	}
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public ArrayList<Sandwich> getOrderSandwiches() 
	{
		return orderSandwiches;
	}
	public void setOrderSandwiches(ArrayList<Sandwich> orderSandwiches) 
	{
		this.orderSandwiches = orderSandwiches;
	}

	public double getOrderPrice() 
	{
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) 
	{
		this.orderPrice = orderPrice;
	}
	
	public double calcOrderPrice()
	{
		double orderPrice = 0;
		
		for (Sandwich sandwich : orderSandwiches)
		{
			orderPrice += sandwich.getSandwichCost();
		}
		
		orderPrice += (orderPrice * salesTax);
		
		return orderPrice;
	}
	
	public String toString()
	{
		String orderString = "The order has the following sandwiches: \n";
		
		for (Sandwich sandwich : orderSandwiches)
		{
			orderString += sandwich.getSandwichName() + ": $" + sandwich.getSandwichCost() + "\n";
		}
		
		orderString += "Order Total: $" + orderPrice;
		
		return orderString;
	}

}
