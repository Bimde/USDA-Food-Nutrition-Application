package database;

import database.datastrucutres.BinaryTree;

public class NutrientPacket{

	private FoodBinaryTree<Nutrient> nutrients;

	public NutrientPacket(FoodBinaryTree<Nutrient> nutrients) {
		this.nutrients = nutrients;
	}
	
	public FoodBinaryTree<Nutrient> getNutrients() {
		return this.nutrients;
	}
}
