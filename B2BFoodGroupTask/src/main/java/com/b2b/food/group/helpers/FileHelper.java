package com.b2b.food.group.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {

	public static String readJsonFile(String path) {

		String output = "";

		try (FileReader reader = new FileReader(path); BufferedReader br = new BufferedReader(reader)) {

			// read line by line
			String line;
			while ((line = br.readLine()) != null) {
				output += line;
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return output;
	}
	
	

}
