package database;

import java.io.IOException;

import database.datastrucutres.FoodPacket;
import database.datastrucutres.LinkedList;

public class DatabaseTest {

	public static void main(String[] args) throws IOException {
		 Database test = new Database();
		 LinkedList<FoodPacket> list = test.search("");
		 list.print();
	}

}
