package com.mobiquity.model;

import com.mobiquity.exception.APIException;

import java.text.DecimalFormat;

public class Item {
	private static final DecimalFormat format = new DecimalFormat("€#,##0.00");
	
	private int index;
	private double weight;
	private double cost;
	
	static {
		format.setParseBigDecimal(true);
	}
	
	public Item(String inputValue) throws APIException {
		final String[] arr = inputValue.replaceAll("[ ()]", "").split(",");
		if (arr.length != 3) {
			throw new APIException(String.format("The input line has %d fields divided by comma. Must be three. [%s]", arr.length, inputValue));
		}
	
		try {
			index = Integer.parseInt(arr[0]);
			weight = Double.parseDouble(arr[1]);
			cost = Double.parseDouble(arr[2].replaceAll("[^\\d.,]",""));
		} catch (NumberFormatException e) {
			throw new APIException("Error during parse package item: ".concat(e.getLocalizedMessage()));
		}
	}
	
	public boolean fitsToPackage(double packageWeight) {
		return weight <= packageWeight;
	}
	
	public int getIndex() {
		return index;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getCost() {
		return cost;
	}
	
	public double getCostByWeight() {
		return weight / cost;
	}
}
