package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import database.datastrucutres.BinaryTree;

public class Parser {

	public static FoodBinaryTree parse(String fileName, String[] headers)
			throws IOException {
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		FoodBinaryTree tree = new FoodBinaryTree();
		int noOfItems = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '^')
				noOfItems++;
		}
		noOfItems++;
		while (line != null) {
			String[] values = split(line, noOfItems);
			tree.add(new FoodPacket(values, headers));
			line = in.readLine();
		}
		in.close();
		return tree;
	}

	private static String[] split(String line, int items) {
		String[] data = new String[items];
		int index = 0;
		for (char i : line.toCharArray()) {
			if (i == '^') {
				if (data[index] != null && data[index].charAt(0) == '~')
					data[index] = data[index].substring(1, data[index].length()-1);
				index++;
			} else {
				if (data[index] == null)
					data[index] = i + "";
				else
					data[index] += i;
			}
		}
		if(data[index] == null)
			data[index] = "";

		return data;
	}
}
