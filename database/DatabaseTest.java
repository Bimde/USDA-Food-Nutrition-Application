package database;

import java.io.IOException;

import database.datastrucutres.BinaryTree;

public class DatabaseTest {

	public static void main(String[] args) throws IOException {
		FoodBinaryTree binary = Parser.parse("NUT_DATA.txt", FoodPacket.NUT_DATA);
		binary.print();
		FoodPacket test = binary.get(2001);
		System.out.println(test.getKey()+": "+test.getValue("NutrDesc"));
	}

}
