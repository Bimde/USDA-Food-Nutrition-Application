package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

	public static FoodBinaryTree<FoodPacket> parseNutrients(FoodBinaryTree<FoodPacket> main, String fileName)
			throws IOException {
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			String[] values = split(line, FoodPacket.HEADERS[FoodPacket.NUT_DATA].length);
			int key = Integer.parseInt(values[0]);
			NutrientList nutrients = new NutrientList();
			while (line != null && Integer.parseInt(values[0]) == key) {
				nutrients.add(new Nutrient(values));
				line = in.readLine();
				values = split(line, FoodPacket.HEADERS[FoodPacket.NUT_DATA].length);
			}
			main.get(key).addNutrientPacket(new NutrientPacket(nutrients));
		}
		in.close();
		return main;
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
