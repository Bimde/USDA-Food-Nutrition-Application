package database;

import database.datastrucutres.BinaryTree;
import database.datastrucutres.FoodPacket;
import database.datastrucutres.FoodPacketBinaryTree;
import database.datastrucutres.FoodPacketList;
import database.datastrucutres.Head;
import database.datastrucutres.LinkedList;

/**
 * Main object, creates and stores all USDA files' information
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class Database {

	// TODO FILL THESE IN
	public static final int GRAINS = 0, MEAT = 1, VEGETABLES = 2, DAIRY = 3,
			OTHER = 4;
	public static final String[][] groups = {
			{ "0800", "2000" },
			{ "0500", "0700", "1000", "1200", "1300", "1500", "1700" },
			{ "0900", "1100", "1600" },
			{ "0100", "0200" },
			{ "0300", "0400", "0600", "1400", "1800", "1900", "2100", "2200",
					"2500", "3500", "3600" } };

	private FoodPacketBinaryTree main, foodGroups, nutrientDef;
	private BinaryTree<Head> languals;

	public Database() {
		long time = System.currentTimeMillis();
		try {
			this.main = Parser.parse("FOOD_DES.txt",
					FoodPacketBinaryTree.FOOD_DES);
		} catch (Exception e) {
			System.err.println("Error loading 'FOOD_DES.txt'");
			e.printStackTrace();
		}
		try {
			this.nutrientDef = Parser.parse("NUTR_DEF.txt",
					FoodPacketBinaryTree.NUTR_DEF);
		} catch (Exception e) {
			System.err.println("Error loading 'NUTR_DEF.txt'");
			e.printStackTrace();
		}
		try {
			this.foodGroups = Parser.parse("FD_GROUP.txt",
					FoodPacketBinaryTree.FD_GROUP);
		} catch (Exception e) {
			System.err.println("Error loading 'FD_GROUP.txt'");
			e.printStackTrace();
		}
		try {
			this.languals = new BinaryTree<Head>();
			Parser.parseLanguals(this.main, this.languals);
		} catch (Exception e) {
			System.err.println("Error loading 'LANGUAL.txt'");
			e.printStackTrace();
		}
		// try {
		// Parser.parseFootNotes(this.main);
		// } catch (Exception e) {
		// System.err.println("Error loading 'FOOTNOTE.txt'");
		// e.printStackTrace();
		// }
		System.out.println((System.currentTimeMillis() - time) / 1000.0);

		try {
			Parser.parseNutrients(this.main);
		} catch (Exception e) {
			System.err.println("Error loading 'NUT_DATA.txt'");
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis() - time) / 1000.0);
	}

	/**
	 * Finds all items in tree which contain specified query
	 * 
	 * @param query
	 *            The string to search for
	 * @return LinkedList of FoodPacket objects for all matches
	 */
	public FoodPacketList search(String query, boolean useLanguals) {
		return this.main.search(query, new String[] { "Long_Desc",
				"Short_Desc", "ComName", "NDB_No" }, useLanguals);
	}

	/**
	 * Finds all items in tree which contain specified query
	 * 
	 * @param query
	 *            The string to search for
	 * @param header
	 *            The field to look at in the database
	 * @return LinkedList of FoodPacket objects for all matches
	 */
	public FoodPacketList search(String query, String header, boolean useLanguals) {
		return this.main.search(query, new String[] {header}, useLanguals);
	}

	public String getFoodGroup(String no) {
		return this.foodGroups.get(Integer.parseInt(no)).getValue("FdGrp_Desc");
	}

	public FoodPacket getNutrientData(String key) {
		return this.nutrientDef.get(Integer.parseInt(key));
	}

	public String getFoodGroup(int no) {
		return this.foodGroups.get(no).getValue("FdGrp_Desc");
	}

	public FoodPacket getNutrientData(int key) {
		return this.nutrientDef.get(key);
	}

	public LinkedList<String> getLanguals(String[] keys) {
		LinkedList<String> list = new LinkedList<String>();
		if(keys == null || this.languals == null)
			return list;
		for (String key : keys) {
			list.add(this.languals.get(new Head(key, "")).getValue());
		}
		return list;
	}
}
