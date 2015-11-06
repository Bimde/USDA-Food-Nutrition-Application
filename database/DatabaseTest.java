package database;

import java.io.IOException;

import database.datastrucutres.BinaryTree;

public class DatabaseTest {

	public static void main(String[] args) throws IOException {
		FoodBinaryTree binary = Parser.parse("FOOD_DES.txt", FoodPacket.HEADERS[FoodPacket.FOOD_DES]);
		binary.print();
		FoodPacket test = binary.get(1001);
		System.out.println(test.getKey()+": "+test.getValue("Short_Desc"));
	}

}
