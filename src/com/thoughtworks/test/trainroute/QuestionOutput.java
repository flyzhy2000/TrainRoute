package com.thoughtworks.test.trainroute;

import java.io.File;

public class QuestionOutput {

	private static int sNO = 1;
	
	public QuestionOutput() {}
	
	public QuestionOutput(File f) {
		// to be added
	}
	
	public void print(String answer) {
		System.out.println("Output #" + sNO + ": " + answer);
		sNO++;
	}
}
