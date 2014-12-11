package com.thoughtworks.test.trainroute;

import java.io.File;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		GraphFileInput defaultInput;
		try {
			defaultInput = new GraphFileInput(new File("./testdata.txt"));
		} catch (IOException e) {
			System.out.println("test data not ready");
			return;
		}
		QuestionOutput defaultOutput = new QuestionOutput();
		Quiz q = new Quiz(defaultInput, defaultOutput);
		
		// 1. The distance of the route A-B-C.
		q.distanceQuestion(new String[]{ "A", "B", "C" });
		
		// 2. The distance of the route A-D.
		q.distanceQuestion(new String[]{ "A", "D"});
		
		// 3. The distance of the route A-D-C.
		q.distanceQuestion(new String[]{ "A", "D", "C" });
		
		// 4. The distance of the route A-E-B-C-D.
		q.distanceQuestion(new String[]{ "A", "E", "B", "C", "D" });
		
		// 5. The distance of the route A-E-D.
		q.distanceQuestion(new String[]{ "A", "E", "D" });
		
		q.routeNumberQuestion();
		
		q.routeNumberQuestion2();
		
		q.shortestRouteQuestion();
		
		q.shortestRouteQuestion2();
		
		q.routeNumbereQuestion3();
	}
}
