package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import database.datastrucutres.BinaryTree;
import database.datastrucutres.FoodPacket;
import database.datastrucutres.FoodPacketBinaryTree;
import database.datastrucutres.Head;
import database.datastrucutres.Nutrient;
import database.datastrucutres.NutrientList;

/**
 * Static class to parse files into appropriate data structure
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
class Parser {

	public static FoodPacketBinaryTree parse(String fileName, int type)
			throws Exception {
		String[] headers = FoodPacketBinaryTree.HEADERS[type];
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		FoodPacketBinaryTree tree = new FoodPacketBinaryTree();
		while (line != null) {
			String[] values = split(line, headers.length);
			tree.add(new FoodPacket(values, headers));
			line = in.readLine();
		}
		in.close();
		return tree;
	}

	public static void parseNutrients(FoodPacketBinaryTree main)
			throws Exception {
		File file = new File("NUT_DATA.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			String[] values = split(
					line,
					FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.NUT_DATA].length);
			int key = Integer.parseInt(values[0]);
			NutrientList nutrients = new NutrientList();
			while (line != null && Integer.parseInt(values[0]) == key) {
				nutrients.add(new Nutrient(values));
				line = in.readLine();
				values = split(
						line,
						FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.NUT_DATA].length);
			}
			main.get(key).addNutrients(nutrients);
		}
		in.close();
	}

	public static void parseFootNotes(FoodPacketBinaryTree main)
			throws Exception {
		File file = new File("NUT_DATA.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			String[] values = split(
					line,
					FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.FOOTNOTE].length);
			int key = Integer.parseInt(values[0]);
			main.get(key).addFootNote(values[1]);
			line = in.readLine();
		}
		in.close();
	}

	public static void parseLanguals(FoodPacketBinaryTree main, BinaryTree<Head> langualDescriptions)
			throws Exception {
		File file = new File("LANGUAL.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			String[] values = split(
					line,
					FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.LANGUAL].length);
			int key = Integer.parseInt(values[0]);
			main.get(key).addLanguals(values[1]);
			line = in.readLine();
		}
		in.close();
		file = new File("LANGDESC.txt");
		in = new BufferedReader(new FileReader(file));
		line = in.readLine();
		while (line != null) {
			String[] values = split(
					line,
					FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.LANGDESC].length);
			langualDescriptions.add(new Head(values[0], values[1]));
			line = in.readLine();
		}
	}

	private static String[] split(String line, int items) {
		if (line == null)
			return null;
		String[] data = new String[items];
		int index = 0, length = line.length(), lastCarrot = 0;
		for (int i = 0; i < length; i++) {
			boolean done = false;
			for (int j = i; !done && j < length; j++) {
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
}
