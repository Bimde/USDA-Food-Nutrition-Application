package database;

import database.datastrucutres.BinaryTree;

public class Nutrient implements KeyCompare<Nutrient> {

	BinaryTree<Head> nutrients;
	private int key;

	public Nutrient(String[] nutrients) {
		this.addNutrients(nutrients);
	}

	private void addNutrients(String[] nutrients) {
		this.key = Integer.parseInt(nutrients[1]);
		this.nutrients = new BinaryTree<Head>();
		for (int i = 1; i < nutrients.length; i++) {
			this.nutrients.add(new Head(nutrients[i], FoodPacket.HEADERS[FoodPacket.NUT_DATA][i]));
		}
	}
	
	public String getValue(String header) {
		return this.nutrients.get(new Head(FoodPacket.HEADERS[FoodPacket.NUT_DATA][1], "")).getValue();
	}

	@Override
	public int compareTo(Nutrient other) {
		return other.getKey() - this.key;
	}
	
	@Override
	public int compareTo(int key) {
		return key-this.key;
	}

	public int getKey() {
		return this.key;
	}
}
