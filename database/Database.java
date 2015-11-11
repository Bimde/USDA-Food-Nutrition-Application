package database;

public class Database {

	private FoodBinaryTree<FoodPacket> main, foodGroups, nutrientDef;

	// TODO SEARCH METHOD MUST RETURN A BINARYTREE OF RESULTS
	// TODO BINARYTREE RETURNED MUST BE ABLE TO BE SEARCHED BY CATEGORY (ex. low
	// fat)
	public Database() {
		long time = System.currentTimeMillis();
		try {
			this.main = Parser.parse("FOOD_DES.txt", FoodPacket.FOOD_DES);
		} catch (Exception e) {
			System.err.println("Error loading 'FOOD_DES.txt'");
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis() - time) / 1000.0);
		try {
			Parser.parseNutrients(this.main, "NUT_DATA.txt");
		} catch (Exception e) {
			System.err.println("Error loading 'NUT_DATA.txt'");
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis() - time) / 1000.0);
		try {
			this.nutrientDef = Parser.parse("NUTR_DEF.txt", FoodPacket.NUTR_DEF);
		} catch (Exception e) {
			System.err.println("Error loading 'NUTR_DEF.txt'");
			e.printStackTrace();
		}
		try {
			this.foodGroups = Parser.parse("FD_GROUP.txt", FoodPacket.FD_GROUP);
		} catch (Exception e) {
			System.err.println("Error loading 'FD_GROUP.txt'");
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis() - time) / 1000.0);
	}
}
