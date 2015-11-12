package database;

import java.io.IOException;
import java.util.Scanner;

import database.datastrucutres.FoodPacket;
import database.datastrucutres.LinkedList;

public class DatabaseTest {

	public static void main(String[] args) throws IOException {
		Database test = new Database();
		Scanner in = new Scanner(System.in);
		while (true) {
			LinkedList<FoodPacket> list = test.search(in.nextLine());
			list.print();
		}
	}
}
