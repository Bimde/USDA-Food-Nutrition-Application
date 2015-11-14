package database;

import java.io.IOException;
import java.util.Scanner;

import database.datastrucutres.FoodPacketList;

/**
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
class DatabaseTest {

	public static void main(String[] args) throws IOException {
		Database test = new Database();
		Scanner in = new Scanner(System.in);
		while (true) {
			FoodPacketList list = test.search(in.nextLine());
			list.print();
			System.out.println("done");
			String[] temp = list.getHead().getItem().getLanguals();
			String[] languals = test.getLanguals(temp).toArray();
			System.out.println(list.getSize());
			if (languals != null)
				for (String i : languals)
					System.out.println(i);
		}
	}
}
