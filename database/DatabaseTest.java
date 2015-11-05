package database;

import java.io.IOException;

import database.datastrucutres.BinaryTree;

public class DatabaseTest {

	public static void main(String[] args) throws IOException {
		BinaryTree<FoodPacket> binary = Parser.parse("FOOD_DES.txt");
	}

}
