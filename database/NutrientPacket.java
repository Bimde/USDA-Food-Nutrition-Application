package database;

import database.datastrucutres.BinaryTree;

public class NutrientPacket implements KeyCompare<NutrientPacket> {

	private FoodBinaryTree<Nutrient> nutrients;
	private int key;

	public NutrientPacket(FoodBinaryTree<Nutrient> nutrients, int foodNo) {
		this.nutrients = nutrients;
		this.key = foodNo;
		System.out.println(this.key);
	}

	@Override
	public int compareTo(NutrientPacket packet) {
		return packet.getKey() - this.getKey();
	}

	@Override
	public int compareTo(int key) {
		return key - this.getKey();
	}

	@Override
	public int getKey() {
		return this.key;
	}
	
	public FoodBinaryTree<Nutrient> getNutrients() {
		return this.nutrients;
	}
}
