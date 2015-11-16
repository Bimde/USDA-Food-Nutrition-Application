package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import database.datastrucutres.BinaryTree;
import database.datastrucutres.FoodPacket;
import database.datastrucutres.FoodPacketBinaryTree;
import database.datastrucutres.IndependantSearchable;
import database.datastrucutres.Nutrient;
import database.datastrucutres.NutrientList;
import database.datastrucutres.Searchable;

/**
 * Static class to parse files into appropriate data structure
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
class Parser {

	public static final boolean[][] NUMERIC = new boolean[][] {
			{ false, false, false, false, false, false, false, false, true, false, true, true, true, true, },
			{ false, false, true, true, true, false, false, false, false, true, true, true, true, true, true, false,
					true, false } };

	/**
	 * **General parsing method for majority of files Inputs data from specified
	 * text file into FoodPacketBinaryTree
	 * 
	 * @param fileName
	 *            The file to load data from
	 * @param type
	 *            The number associated with the file (obtainable from
	 *            FoodPacketBinaryTree class), ex.
	 *            'FoodPacketBinaryTree.FOOD_DES'
	 * @return The FoodPacketBinaryTree containing all information from file
	 * @throws Exception
	 *             If file is not found or any errors occur when loading data
	 *             into BinaryTree
	 */
	public static FoodPacketBinaryTree parse(String fileName, int type) throws Exception {
		String[] headers = FoodPacketBinaryTree.HEADERS[type];
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		FoodPacketBinaryTree tree = new FoodPacketBinaryTree();
		// Add each line of data into BinaryTree as FoodPacket objects
		while (line != null) {
			String[] values = split(line, headers.length);
			tree.add(new FoodPacket(values, type));
			line = in.readLine();
		}
		in.close();
		return tree;
	}

	/**
	 * ***ONLY loads data from 'NUT_DATA.txt'*** Specific loading method for
	 * loading nutrient information into already existing BinaryTree
	 * 
	 * @param main
	 *            The BinaryTree to load information into
	 * @throws Exception
	 *             If file is not found or any errors occur when loading data
	 *             into BinaryTree
	 */
	public static void parseNutrients(FoodPacketBinaryTree main) throws Exception {
		File file = new File("NUT_DATA.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			String[] values = split(line, FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.NUT_DATA].length);

			// Create list of all nutrients for each food item using specified
			// primary key
			int key = Integer.parseInt(values[0]);
			NutrientList nutrients = new NutrientList();

			// Continues adding items to list as long as the primary key (key
			// which identifies which food the nutrient is in) is the same
			while (line != null && Integer.parseInt(values[0]) == key) {

				// Strip the 'NDB_No' from the Nutrient object to eliminate
				// duplicate data
				String[] tempData = Arrays.copyOfRange(values, 1, values.length);

				nutrients.add(new Nutrient(tempData));
				line = in.readLine();
				values = split(line, FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.NUT_DATA].length);
			}
			// When key changes, add list of nutrients to specified food
			// object
			main.get(key).addNutrients(nutrients);
		}
		in.close();
	}

	/**
	 * ***ONLY loads data from 'FOOTNOTE.txt'*** Specific loading method for
	 * loading footnote information into already existing BinaryTree
	 * 
	 * @param main
	 *            The BinaryTree to load information into
	 * @throws Exception
	 *             If file is not found or any errors occur when loading data
	 *             into BinaryTree
	 */
	public static void parseFootNotes(FoodPacketBinaryTree main) throws Exception {
		File file = new File("FOOTNOTE.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			String[] values = split(line, FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.FOOTNOTE].length);

			// Use primary key to locate food object and add the footnote
			// description to it
			int key = Integer.parseInt(values[0]);
			main.get(key).addFootNote(values[4]);
			line = in.readLine();
		}
		in.close();
	}

	/**
	 * ***ONLY loads data from 'LANGUAL.txt' and 'LANGDESC.txt'*** Specific
	 * loading method for loading langual references into already existing
	 * BinaryTree and langual descriptions into new BinaryTree
	 * 
	 * @param main
	 *            The BinaryTree to load information into
	 * @return BinaryTree containing langual descriptions (which could be
	 *         referenced using Langual key, ex. 'A0107')
	 * @throws Exception
	 *             If file is not found or any errors occur when loading data
	 *             into BinaryTree
	 */
	public static BinaryTree<Searchable> parseLanguals(FoodPacketBinaryTree main) throws Exception {

		// Load the primary keys (ex. A2001) into the FoodPacket objects in
		// provided BinaryTree
		File file = new File("LANGUAL.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			String[] values = split(line, FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.LANGUAL].length);
			int key = Integer.parseInt(values[0]);
			main.get(key).addLanguals(values[1]);
			line = in.readLine();
		}
		in.close();

		// Create a new BinaryTree to link the primary keys from the langual
		// descriptions file to the langual descriptions (allows user to get
		// langual descriptions by providing primary key)
		BinaryTree<Searchable> langualDescriptions = new BinaryTree<Searchable>();
		file = new File("LANGDESC.txt");
		in = new BufferedReader(new FileReader(file));
		line = in.readLine();
		while (line != null) {
			String[] values = split(line, FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.LANGDESC].length);
			langualDescriptions.add(new IndependantSearchable(values[0], values[1]));
			line = in.readLine();
		}
		return langualDescriptions;
	}

	/**
	 * 
	 * @param data
	 *            String array of data to write to file
	 * @param fileName
	 *            File to write data to
	 * @param fileNo
	 *            Index from FoodPacketBinaryTree class associated with file,
	 *            for example, use 'FoodPacketBinaryTree.FOOD_DES' for
	 *            'FOOD_DES.txt'
	 * @throws IOException
	 *             If the named file exists but is a directory rather than a
	 *             regular file, does not exist but cannot be created, or cannot
	 *             be opened for any other reason (from FileWriter)
	 */
	public static void addToFile(String[] data, String fileName, int fileNo) throws IOException {
		// Adds data to the file instead of overwriting it
		BufferedWriter out = new BufferedWriter(new FileWriter(new File(fileName), true));

		// Calls the join method to convert the string array into a string using
		// the boolean array to format the fields as numeric or text
		out.write(join(data, NUMERIC[fileNo]));

		// Add a new line to the end of the file to maintain a consistent format
		// with the provided USDA files
		out.newLine();
		out.close();
	}

	/**
	 * Splits a line of text into a String array using '^' as the delimiter Also
	 * removes all trailing and leading '^' characters from the strings
	 * 
	 * @param line
	 *            The line to break up
	 * @param items
	 *            The number of items in the String (used as the length of the
	 *            String array)
	 * @return String array of length 'items' containing fragments from provided
	 *         line
	 */
	private static String[] split(String line, int items) {
		if (line == null)
			return null;
		String[] data = new String[items];
		int index = 0, length = line.length(), lastCarrot = 0;

		// Loop through each line and add break information into array using i
		// as the start of the string and j as the position of the found
		// delimiter
		for (int i = 0; i < length; i++) {
			boolean done = false;
			for (int j = i; !done && j < length; j++) {
				// Remove the '~' characters from the string if they exist
				if (line.charAt(j) == '^') {
					if (line.charAt(i) == '~')
						data[index] = line.substring(i + 1, j - 1);
					else {
						data[index] = line.substring(i, j);
					}
					i = j;
					done = true;
					if (data[index] == null)
						data[index] = "";
					index++;
					lastCarrot = j;
				}
			}
		}

		// Deals with last case if there is no trailing '^' character
		if (length - 1 != lastCarrot) {
			if (line.charAt(lastCarrot + 1) == '~')
				data[index] = line.substring(lastCarrot + 2, line.length() - 1);
			else
				data[index] = line.substring(lastCarrot + 1);
		}
		if (data[data.length - 1] == null)
			data[data.length - 1] = "";
		return data;
	}

	/**
	 * Joins a provided string array into a single string using the provided
	 * boolean array to specify is a field is numeric or text <br>
	 * <br>
	 * Formatting used: Each field is separated by a '^' character but only text
	 * fields are surrounded by '~' characters
	 * 
	 * @param data
	 * @param numeric
	 * @return
	 */
	public static String join(String[] data, boolean[] numeric) {
		String output = "";
		for (int i = 0; i < data.length; i++) {
			// Add the '~' characters only if the field is not numeric
			if (!numeric[i]) {
				output += "~" + data[i] + "~^";
			} else {
				output += data[i] + "^";
			}
		}
		// Removes trailing '^' character if it exists to remain consistent with
		// USDA formatting
		if (output.charAt(output.length() - 1) == '^')
			return output.substring(0, output.length() - 1);
		return output;
	}
}
