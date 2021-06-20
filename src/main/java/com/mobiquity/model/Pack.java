package com.mobiquity.model;

import com.mobiquity.exception.APIException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mobiquity.model.UtilException.rethrowFunction;

public class Pack {
	private int weight;
	private List<Item> items;
	
	public Pack(String inputString) throws APIException {
		String[] arr = inputString.split(":");
		if (arr.length != 2) {
			throw new APIException(String.format("The input line has %d fields divided by colon. Must be two", arr.length));
		}
		
		try {
			weight = Integer.parseInt(arr[0].trim());
		} catch (NumberFormatException e) {
			throw new APIException(String.format("First field in input string must be an integer, got %s", arr[0]));
		}
		
		items = Arrays.stream(arr[1].split(" ")).filter(p -> !p.isBlank()).map(rethrowFunction(Item::new)).filter(p -> p.fitsToPackage(weight)).collect(Collectors.toList());
	}
	
	public String calculate() {
		double total = items.stream().map(p -> p.getWeight()).reduce((double) 0, Double::sum);
		if (total <= weight) {
			return Combinations.toString(items);
		}
		Combinations combinations = new Combinations(items, weight);
		combinations.calculate();
		return combinations.toString();
	}
}
