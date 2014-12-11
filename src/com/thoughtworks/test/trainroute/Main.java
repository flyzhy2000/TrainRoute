package com.thoughtworks.test.trainroute;

import com.thoughtworks.test.trainroute.DirectedGraph.Node;

public class Main {

	private static final String[] sNodes = { "A", "B", "C", "D", "E" };
	private static final String[] sEdges = { "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
	
	public static void main(String[] args) {
		// 1. The distance of the route A-B-C.
		distanceQuestion(1, new String[]{ "A", "B", "C" });
		
		// 2. The distance of the route A-D.
		distanceQuestion(2, new String[]{ "A", "D"});
		
		// 3. The distance of the route A-D-C.
		distanceQuestion(3, new String[]{ "A", "D", "C" });
		
		// 4. The distance of the route A-E-B-C-D.
		distanceQuestion(4, new String[]{ "A", "E", "B", "C", "D" });
		
		// 5. The distance of the route A-E-D.
		distanceQuestion(5, new String[]{ "A", "E", "D" });
		
		routeNumberQuestion();
		
		routeNumberQuestion2();
		
		routeNumbereQuestion3();
	}

	private static void distanceQuestion(int questionNO, String[] routeNodes) {

		DirectedGraph g = RouteGraphFactory.getInstance().create(sNodes, sEdges);
		
		Node[] stops = new Node[routeNodes.length];
		for (int i = 0; i < stops.length; i++) {
			stops[i] = new Node(routeNodes[i]);
		}
		
		int distance = g.distance(stops);
		if (distance > 0) {
			System.out.println("Output #" + questionNO + ": " + distance);
		}
		else {
			System.out.println("Output #" + questionNO + ": NO SUCH ROUTE");
		}
	}
	
	private static void routeNumberQuestion() {

		DirectedGraph g = RouteGraphFactory.getInstance().create(sNodes, sEdges);
				
		int routeNum = g.numOfRouteWithMaxNodes(g.getNodeByName("C"), g.getNodeByName("C"), 3, false);
		System.out.println("Output #6: " + routeNum);
	}
	
	private static void routeNumberQuestion2() {

		DirectedGraph g = RouteGraphFactory.getInstance().create(sNodes, sEdges);
				
		int routeNum = g.numOfRouteWithMaxNodes(g.getNodeByName("A"), g.getNodeByName("C"), 4, true);
		System.out.println("Output #7: " + routeNum);
	}
	
	private static void routeNumbereQuestion3() {

		DirectedGraph g = RouteGraphFactory.getInstance().create(sNodes, sEdges);
				
		int routeNum = g.numOfRouteWithMaxDistance(g.getNodeByName("C"), g.getNodeByName("C"), 30);
		System.out.println("Output #9: " + routeNum);
		
	}

}
