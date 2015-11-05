package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import database.datastrucutres.BinaryTree;

public class Parser {

	public static BinaryTree<FoodPacket> parse(String fileName)
			throws IOException {
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		BinaryTree<FoodPacket> tree = new BinaryTree<FoodPacket>();
		int noOfItems = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '^')
				noOfItems++;
		}
		noOfItems++;
		while (line != null) {
			tree.add(new FoodPacket(split(line, noOfItems)));
		}
		in.close();
		return tree;
	}

	private static String[] split(String line, int items) {
		line = "^"+line;
		String[] data = new String[items];
		int index = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '^') {
				int temp = i+1;
				while (temp < line.length() && line.charAt(temp) != '^') {
					temp++;
				}
				data[index] = line.substring(i + 1, temp);
				if (data[index].equals("~~"))
					data[index] = "";
				index++;
				i = temp + 1;
			}
		}
		return data;

	}
}
