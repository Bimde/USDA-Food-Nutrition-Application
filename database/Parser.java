package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import database.datastrucutres.BinaryTree;

public class Parser {

	public static FoodBinaryTree<FoodPacket> parse(String fileName, int type) throws IOException {
		String[] headers = FoodPacket.HEADERS[type];
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		FoodBinaryTree<FoodPacket> tree = new FoodBinaryTree<FoodPacket>();
		while (line != null) {
			String[] values = split(line, headers.length);
			tree.add(new FoodPacket(values, headers));
			line = in.readLine();
		}
		in.close();
		return tree;
	}

	public static FoodBinaryTree<NutrientPacket> parseNutrients(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		FoodBinaryTree<NutrientPacket> tree = new FoodBinaryTree<NutrientPacket>();
		while (line != null) {
			String[] values = split(line, FoodPacket.HEADERS[FoodPacket.NUT_DATA].length);
			String key = values[0];
			FoodBinaryTree<Nutrient> nutrients = new FoodBinaryTree<Nutrient>();
			while (line != null && values[0] == key) {
				nutrients.add(new Nutrient(values));
				line = in.readLine();
				values = split(line, FoodPacket.HEADERS[FoodPacket.NUT_DATA].length);
			}
			tree.add(new NutrientPacket(nutrients, Integer.parseInt(key)));
		}
		in.close();
		return tree;
	}

	// public static FoodBinaryTree parseNutData(String[] headers, File file)
	// throws IOException {
	// BufferedReader in = new BufferedReader(new FileReader(file));
	// String line = in.readLine();
	// FoodBinaryTree tree = new FoodBinaryTree();
	// while (line != null) {
	// String[] values = split(line, headers.length);
	// tree.add(new FoodPacket(values, headers));
	// line = in.readLine();
	// }
	// in.close();
	// return tree;
	// }

	private static String[] OLDsplit(String line, int items) {
		String[] data = new String[items];
		int index = 0;
		for (char i : line.toCharArray()) {
			if (i == '^') {
				if (data[index] != null && data[index].charAt(0) == '~')
					data[index] = data[index].substring(1, data[index].length() - 1);
				index++;
			} else {
				if (data[index] == null)
					data[index] = i + "";
				else
					data[index] += i;
			}
		}
		if (data[index] == null)
			data[index] = "";

		return data;
	}

	private static String[] split(String line, int items) {
		if(line == null)
			return null;
		String[] data = new String[items];
		int index = 0, length = line.length(), lastCarrot = 0;
		for (int i = 0; i < length; i++) {
			boolean done = false;
			for (int j = i; !done && j < length; j++) {
				if (line.charAt(j) == '^') {
					if (line.charAt(i) == '~')
						data[index] = line.substring(i + 1, j - 1);
					else
						data[index] = line.substring(i, j);
					i = j;
					done = true;
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
		return data;

	}
}
