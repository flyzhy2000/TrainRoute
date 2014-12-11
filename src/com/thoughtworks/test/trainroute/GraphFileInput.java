package com.thoughtworks.test.trainroute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphFileInput {

	private String[] edgeDesc;
	
	public GraphFileInput(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = reader.readLine();
		
		if (line.indexOf(":") > 0) {
			String desc = line.substring(line.indexOf(":") + 1);
			edgeDesc = desc.trim().split(","); 
		}
	}
	
	public String[] getEdge() {
		return edgeDesc;
	}
}
