package database;

import java.io.IOException;

import database.datastrucutres.BinaryTree;
import database.datastrucutres.FoodPacket;
import database.datastrucutres.FoodPacketBinaryTree;
import database.datastrucutres.FoodPacketList;
import database.datastrucutres.IndependantSearchable;
import database.datastrucutres.LinkedList;
import database.datastrucutres.Nutrient;
import database.datastrucutres.NutrientList;
import database.datastrucutres.Searchable;

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

	public static final String[][] HEADERS = {
			{ "NDB_No", "FdGrp_Cd", "Long_Desc", "Short_Desc", "ComName", "ManufacName", "Survey", "Ref_desc", "Refuse",
					"SciName", "N_Factor", "Pro_Factor", "Fat_Factor", "CHO_Factor" },
			{ "NDB_No", "Nutr_No", "Nutr_Val", "Num_Data_Pts", "Std_Error", "Src_Cd", "Derriv_Cd", "Ref_NDB_No",
					"Add_Nutr_Mark", "Num_Studies", "Min", "Max", "DF", "Low_EB", "Up_EB", "Stat_cmt", "AddMod_Date",
					"CC" },
			{ "NDB_No", "Seq", "Amount", "Msre_Desc", "Gm_Wgt", "Num_Data_Pts", "Std_Dev" },
			{ "Nutr_No", "Units", "Tagname", "NutrDesc", "Num_Dec", "SR_Order" }, { "FdGrp_Cd", "FdGrp_Desc" },
			{ "Factor_Code", "Description" }, { "NDB_No", "Factor_Code" },
			{ "NDB_No", "Footnt_No", "Footnt_Typ", "Nutr_No", "Footnt_Txt" } };

	public static final String[] ADD_FOOD = { "FdGrp_Cd", "Long_Desc", "ComName" };
	public static final String[] ADD_NUTRIENT = { "Nutr_No", "Nutr_Val" };

	/**
	 * Contains the information searchable by the user
	 */
	private FoodPacketBinaryTree main, foodGroups, nutrientDef;

	/**
	 * Contains langual information (used primarily for searching)
	 */
	private BinaryTree<Searchable> languals;

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
	 * strings <br>
	 * Finds the description of the requested food group using key in intriguer
	 * format
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
	 *            Food group number <br>
	 *            <b>MUST MATCH EXACTLY</b> for example: get("100") will not
	 *            return the food group description with key "0100"
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

	public FoodPacketList getAllNutrients() {
		return this.nutrientDef.toLinkedList();
	}

	/**
	 * Finds the information associated with the requested nutrient number
	 * 
	 * @param key
	 *            the nutrient key (primary key in 'NUTR_DEF' file), ex. "301"
	 *            in string form<br>
	 *            <b>MUST MATCH EXACTLY</b> for example: get("101") will not
	 *            return the info about the nutrient with key "0101"
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
	 * ** MOST EFFICIENT way to retrieve info if you have primary key **<br>
	 * Useful for getting reference data based of reference key <br>
	 * Finds a FoodPacket based on supplied primary key
	 * 
	 * @param key
	 *            Primary key from 'FOOD_DES.txt' file associated with desired
	 *            food
	 * @return FoodPacket object associated with the provided key
	 */
	public FoodPacket getFood(int key) {
		return this.main.get(key);
	}

	/**
	 * ** <b>THIS IS A PERMANENT ACTION</b> ** <br>
	 * 
	 * Allows the user to add a desired food with specified nutrients into the
	 * data-structures and files <br>
	 * <br>
	 * 
	 * <b>Use the format specified in the public static 'Database.ADD_NUTRIENT'
	 * array </b> for the structure of the individual arrays for the nutrient
	 * information
	 * 
	 * @param foodGroupCode
	 *            Specific food group code the food item falls under (primary
	 *            key from 'FD_GROUP.txt')
	 * @param description
	 *            Description of food, in key words, separated by commas
	 * @param commonName
	 *            Common names of the food item separated by commas (i.e 'Seeds,
	 *            shells')
	 * @param manufacturerName
	 *            (If applicable) Name of manufacturer of food item
	 * @param nutrientInfo
	 *            2-dimensional string array containing n arrays of the
	 *            'Nutr_No' and 'Nutr_Val', n being the number of associated
	 *            nutrients. '<b>Nutr_No</b>' is the primary key for the
	 *            'NUTR_DEF.txt' file and '<b>Nutr_Val</b>' is the amount of the
	 *            nutrient in 100g of the food item
	 */
	public void addFood(String foodGroupCode, String description, String commonName, String manufacturerName,
			String[][] nutrientInfo) {
		if (!Character.isUpperCase(description.charAt(0))) {
			try {
				description = Character.toUpperCase(description.charAt(0)) + description.substring(1);
			} catch (Exception e) {
				// If this capitalization didn't work, then leave the
				// description as it is
			}
		}
		String[] foodDescription = { (this.main.getLargestKey() + 1) + "", foodGroupCode, description, "", commonName,
				manufacturerName, "", "", "", "", "", "", "", "11/2015" };
		FoodPacket item = new FoodPacket(foodDescription, FoodPacketBinaryTree.FOOD_DES);
		this.main.add(item);
		NutrientList nutrients = new NutrientList();
		for (String[] values : nutrientInfo) {
			nutrients.add(new Nutrient(values));
			try {
				Parser.addToFile(new String[] { foodDescription[0], values[0], values[1], "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "" }, "NUT_DATA.txt", FoodPacketBinaryTree.NUT_DATA);
			} catch (IOException e) {
				System.err.println("Error adding nutrient data for food '" + description + "' to 'NUT_DATA.txt'");
				e.printStackTrace();
			}
		}
		item.addNutrients(nutrients);
		try {
			Parser.addToFile(foodDescription, "FOOD_DES.txt", FoodPacketBinaryTree.FOOD_DES);
		} catch (IOException e) {
			System.err.println("Error adding food '" + description + "' to 'FOOD_DES.txt'");
			e.printStackTrace();
		}
	}
}
