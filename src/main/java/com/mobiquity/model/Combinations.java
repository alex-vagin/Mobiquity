package com.mobiquity.model;

import java.util.*;
import java.util.stream.Collectors;

public class Combinations {
	private final List<Item> items;
	private final double maxWeight;
	private final List<Item> combinations;
	private final List<Item> bestCombination;
	private double bestCost;
	private double bestWeight;
	
	public Combinations(List<Item> items, double maxWeight) {
		this.items = items;
		this.maxWeight = maxWeight;
		combinations = new ArrayList<>(items.size() - 1);
		bestCombination = new ArrayList<>();
	}
	
	private void calculate(int currentIndex, double currentWeight, double currentCost) {
		if (currentWeight > maxWeight) {
			return;
		}

		if ((currentCost > bestCost) ||
			((currentCost == bestCost) && (currentWeight < bestWeight) )) {
			bestCost = currentCost;
			bestWeight = currentWeight;
			bestCombination.clear();
			bestCombination.addAll(combinations);
		}
		
		if (combinations.size() == items.size() - 1) {
			return;
		}
		
		for (int i = currentIndex + 1; i < items.size(); i++) {
			Item item = items.get(i);
			combinations.add(item);
			calculate(i, currentWeight + item.getWeight(), currentCost + item.getCost());
			combinations.remove(item);
		}
	}
	
	public void calculate() {
		calculate(-1, 0, 0);
	}
	
	@Override
	public String toString() {
		bestCombination.sort(Comparator.comparingInt(Item::getIndex));
		return toString(bestCombination);
	}
	
	public static String toString(List<Item> items) {
		return items.isEmpty() ? "-" : items.stream().map(p -> Integer.toString(p.getIndex())).collect(Collectors.joining(","));
	}
}
