package database;

/**
 * !!!!!!!!!!!!!!!!!!!!!!ASK MANGAT IF WE SHOULD PUT JD COMMENTS FOR CONSTRUCTORS!!!!!!!!!!!!!!!!
 */
import database.datastrucutres.BinaryTree;
import database.datastrucutres.FoodPacket;
import database.datastrucutres.FoodPacketBinaryTree;
import database.datastrucutres.FoodPacketList;
import database.datastrucutres.LinkedSearchable;
import database.datastrucutres.IndependantSearchable;
import database.datastrucutres.LinkedList;

/**
 * Main object, creates and stores all USDA files' information
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class Database {

	/**
	 * Represents index in array of generalized food groups
	 */
	public static final int GRAINS = 0, MEAT = 1, VEGETABLES = 2, DAIRY = 3, OTHER = 4;

	/**
	 * Generalized food group categories
	 */
	public static final String[][] groups = { { "0800", "2000" },
			{ "0500", "0700", "1000", "1200", "1300", "1500", "1700" }, { "0900", "1100", "1600" }, { "0100" },
			{ "0200", "0300", "0400", "0600", "1400", "1800", "1900", "2100", "2200", "2500", "3500", "3600" } };

	/**
	 * Contains the information searchable by the user
	 */
	private FoodPacketBinaryTree main, foodGroups, nutrientDef;

	/**
	 * Contains langual information (used primarily for searching)
	 */
	private BinaryTree<LinkedSearchable> languals;

	/**
	 * Initializes and loads data into all data structures
	 */
	public Database() {
		long time = System.currentTimeMillis();

		// Load specified files into data structure

		// Food descriptions
		try {
			this.main = Parser.parse("FOOD_DES.txt", FoodPacketBinaryTree.FOOD_DES);
		} catch (Exception e) {
			System.err.println("Error loading 'FOOD_DES.txt'");
			e.printStackTrace();
		}

		// Nutrient definitions
		try {
			this.nutrientDef = Parser.parse("NUTR_DEF.txt", FoodPacketBinaryTree.NUTR_DEF);
		} catch (Exception e) {
			System.err.println("Error loading 'NUTR_DEF.txt'");
			e.printStackTrace();
		}

		// Food group descriptions
		try {
			this.foodGroups = Parser.parse("FD_GROUP.txt", FoodPacketBinaryTree.FD_GROUP);
		} catch (Exception e) {
			System.err.println("Error loading 'FD_GROUP.txt'");
			e.printStackTrace();
		}

		// Langual information
		try {
			this.languals = Parser.parseLanguals(this.main);
		} catch (Exception e) {
			System.err.println("Error loading 'LANGUAL.txt'");
			e.printStackTrace();
		}

		// Foot notes
		try {
			Parser.parseFootNotes(this.main);
		} catch (Exception e) {
			System.err.println("Error loading 'FOOTNOTE.txt'");
			e.printStackTrace();
		}

		// Nutrient data for each food item
		try {
			Parser.parseNutrients(this.main);
		} catch (Exception e) {
			System.err.println("Error loading 'NUT_DATA.txt'");
			e.printStackTrace();
		}
		System.err.println("Time taken: " + (System.currentTimeMillis() - time) / 1000.0);
	}

	/**
	 * Finds all items in tree which contain specified query in the long
	 * description, short description, common name or primary key
	 * 
	 * @param query
	 *            The string to search for
	 * @return LinkedList of FoodPacket objects for all matches
	 */
	public FoodPacketList search(String query) {
		return this.main.search(query.replaceAll("^[,\\s]+", "").split("[,\\s]+"),
				new String[] { "Long_Desc", "Short_Desc", "ComName", "NDB_No" }, true);
	}

	/**
	 * Finds all items in tree which contain specified query in the specified
	 * fields
	 * 
	 * @param query
	 *            The string to search for
	 * @param header
	 *            The field to look at in the database
	 * @param useLanguals
	 *            Whether or not to search using langual information
	 * @return LinkedList of FoodPacket objects for all matches
	 */
	public FoodPacketList search(String[] queries, String[] headers, boolean useLanguals) {
		return this.main.search(queries, headers, useLanguals);
	}

	/**
	 * PREFEREED METHOD: Due to efficiency of comparing integers instead to
	 * strings Finds the description of the requested food group using key in
	 * intriguer format
	 * 
	 * @param no
	 *            Food group number
	 * @return The description of the requested food group
	 */
	public String getFoodGroup(int no) {
		return this.foodGroups.get(no).getValue("FdGrp_Desc");
	}

	/**
	 * Finds the description of the requested food group using key in string
	 * format
	 * 
	 * @param no
	 *            Food group number
	 * @return The description of the requested food group
	 */
	public String getFoodGroup(String no) {
		return this.foodGroups.get(Integer.parseInt(no)).getValue("FdGrp_Desc");
	}

	/**
	 * PREFEREED METHOD: Due to efficiency of comparing integers instead to
	 * strings Finds the information associated with the requested nutrient
	 * number
	 * 
	 * @param key
	 *            the nutrient key (primary key in 'NUTR_DEF' file), ex. '301'
	 * @return The information associated with the requested nutrient number
	 */
	public FoodPacket getNutrientData(int key) {
		return this.nutrientDef.get(key);
	}

	/**
	 * Finds the information associated with the requested nutrient number
	 * 
	 * @param key
	 *            the nutrient key (primary key in 'NUTR_DEF' file), ex. '301'
	 * @return The information associated with the requested nutrient number
	 */
	public FoodPacket getNutrientData(String key) {
		return this.nutrientDef.get(Integer.parseInt(key));
	}

	/**
	 * Find the langual information associated with the supplied keys
	 * 
	 * @param keys
	 *            String array of primary keys from 'LANGDESC' file, ex. 'A0107'
	 * @return LinkedList of same length as keys array (if keys are valid) of
	 *         langual descriptions
	 */
	public LinkedList<String> getLanguals(String[] keys) {
		LinkedList<String> list = new LinkedList<String>();
		if (keys == null || this.languals == null)
			return list;
		for (String key : keys) {
			list.add(this.languals.get(new IndependantSearchable(key)).getValue());
		}
		return list;
	}

	/**
	 * ** MOST EFFICIENT way to retrieve info if you have primary key** **
	 * Useful for getting reference data based of reference key Finds a
	 * FoodPacket based on supplied primary key
	 * 
	 * @param key
	 * @return
	 */
	public FoodPacket getFood(int key) {
		return this.main.get(key);
	}

	/**
	 * Allows user to add food item to the database (saves item in both
	 * datastructures (memory) and permanant files (*.txt files)
	 * 
	 * @param description
	 *            The important information to add, pertaining to the
	 *            ADD_FOOD_SPECIFICATIONS public static array
	 */
	public void addFood(String[] descriptions) {

	}
}
