package database;

import java.io.IOException;

public class DatabaseTest {

	public static void main(String[] args) throws IOException {
		long time = System.currentTimeMillis();
		FoodBinaryTree<FoodPacket> binary = Parser.parse("FOOD_DES.txt", FoodPacket.FOOD_DES);
		binary.print();
		binary = Parser.parseNutrients(binary, "NUT_DATA.txt");
//		NutrientPacket test = binary.get(2001).getNutrientPacket();
//		System.out.println("2001" + ": " + test.getNutrients().get(203).getValue("Nutr_Val"));
//		System.out.println(System.currentTimeMillis() - time);	
		FoodBinaryTree<FoodPacket> binary2 = Parser.parse("NUTR_DEF.txt", FoodPacket.NUTR_DEF);
//		System.out.println(binary2.get(203).getValue("NutrDesc"));
		System.out.println(System.currentTimeMillis() - time);	
	}

}
