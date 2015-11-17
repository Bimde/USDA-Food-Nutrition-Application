package database;
//package database;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//import database.FoodPacket;
//import database.FoodPacketList;
//import database.ListNode;
//import database.Nutrient;
//import database.NutrientList;
//
///**
// * 
// * @author Bimesh De Silva
// * @version Final (November 2015)
// * 
// */
//class DatabaseTest {
//
//	public static void main(String[] args) throws IOException {
//		Database test = new Database();
//		Scanner in = new Scanner(System.in);
//		FoodPacketList list2 = test.getAllNutrients();
//		FoodPacket[] list3 = list2.toArray();
//		for (int i = 0; i < list3.length; i++) {
//			System.out.println(list3[i]);
//		}
//		System.out.println("done2");
//
////		// How to add a food item example
////		test.addFood("0100", "sweet, potatoe, ugly", "sweet potatoe", "",
////				new String[][] { { "301", "12.05040" }, { "502", "43.9" } });
//
//		while (true) {
//			FoodPacketList list = test.search(in.nextLine());
//			list.print();
//			System.out.println("done");
//			if (list.getSize() != 0) {
//				String[] temp = list.getHead().getItem().getLanguals();
//				String[] languals = test.getLanguals(temp).toArray();
//				System.out.println(list.getSize());
//				if (languals != null)
//					for (String i : languals)
//						System.out.println(i);
//			}
//			
//			//System.out.println(list.getHead().getItem().getNutrients().get(318).getValue("NutrDesc"));
//
////			NutrientList nutrients = list.getHead().getItem().getNutrients();
////			ListNode<Nutrient> temp = nutrients.getHead();
////			while (temp != null) {
////				System.out.println(temp.getItem());
////				temp = temp.getNext();
////			}
//		}
//	}
//}
