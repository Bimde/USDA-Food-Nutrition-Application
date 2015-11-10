package database;

import java.io.IOException;

public class DatabaseTest {

	public static void main(String[] args) throws IOException {
		long time = System.currentTimeMillis();
//		FoodBinaryTree<FoodPacket> binary = Parser.parse("FOOD_DES.txt", FoodPacket.FOOD_DES);
//		binary.print();
		time = System.currentTimeMillis();
		FoodBinaryTree<NutrientPacket> binary2 = Parser.parseNutrients("NUT_DATA.txt");
		binary2.print();
		System.out.println(System.currentTimeMillis()-time);
		NutrientPacket test = binary2.get(2001);
		System.out.println(test.getKey() + ": " + test.getNutrients().get(203).getValue("Nutr_Val"));
	}

}
