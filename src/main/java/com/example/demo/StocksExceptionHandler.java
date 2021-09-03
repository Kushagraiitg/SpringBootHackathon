package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StocksExceptionHandler {
	public static boolean main(String stkName) throws Exception {
		String fileName = "symbols.csv";
		File file = new File(fileName);
		// this gives you a 2-dimensional array of strings
		List<List<String>> lines = new ArrayList<>();
		Scanner inputStream;
		boolean test = false;
		try {
			inputStream = new Scanner(file);
			while (inputStream.hasNext()) {
				String line = inputStream.next();
				String[] values = line.split(",");
				// this adds the currently parsed line to the 2-dimensional string array
				lines.add(Arrays.asList(values));
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int lineNo = 1;
		
		for (List<String> line : lines) {
			int columnNo = 1;
			for (String value : line) {
				if (stkName.equals(value)) {
					test = true;
				}
			}
			lineNo++;
		}
		if (!test) {
			return false;
		}
		return true;
		// the following code lets you iterate through the 2-dimensional array
	}
}