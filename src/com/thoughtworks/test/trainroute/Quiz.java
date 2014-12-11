package com.thoughtworks.test.trainroute;

import com.thoughtworks.test.trainroute.DirectedGraph.Node;

public class Quiz {

	private GraphFileInput input;
	private QuestionOutput output;
	
	public Quiz(GraphFileInput input, QuestionOutput output) {
		this.input = input;
		this.output = output;
	}

	public void shortestRouteQuestion2() {

		DirectedGraph g = AdjListGraphFactory.getInstance().create(input.getEdge());
				
		int distance = g.shortestDistance(g.getNodeByName("C"), g.getNodeByName("C"));
		output.print(String.valueOf(distance));				
	}

	public void shortestRouteQuestion() {

		DirectedGraph g = AdjListGraphFactory.getInstance().create(input.getEdge());
				
		int distance = g.shortestDistance(g.getNodeByName("A"), g.getNodeByName("C"));
		output.print(String.valueOf(distance));
	
	}

	public void distanceQuestion(String[] routeNodes) {

		DirectedGraph g = AdjListGraphFactory.getInstance().create(input.getEdge());
		
		Node[] stops = new Node[routeNodes.length];
		for (int i = 0; i < stops.length; i++) {
			stops[i] = new Node(routeNodes[i]);
		}
		
		int distance = g.distance(stops);
		if (distance > 0) {
			output.print(String.valueOf(distance));
		}
		else {
			output.print("NO SUCH ROUTE");
		}
	}
	
	public void routeNumberQuestion() {

		DirectedGraph g = AdjListGraphFactory.getInstance().create(input.getEdge());
				
		int routeNum = g.numOfRouteWithMaxNodes(g.getNodeByName("C"), g.getNodeByName("C"), 3, false);
		output.print(String.valueOf(routeNum));
	}
	
	public void routeNumberQuestion2() {

		DirectedGraph g = AdjListGraphFactory.getInstance().create(input.getEdge());
				
		int routeNum = g.numOfRouteWithMaxNodes(g.getNodeByName("A"), g.getNodeByName("C"), 4, true);
		output.print(String.valueOf(routeNum));
	}
	
	public void routeNumbereQuestion3() {

		DirectedGraph g = AdjListGraphFactory.getInstance().create(input.getEdge());
				
		int routeNum = g.numOfRouteWithMaxDistance(g.getNodeByName("C"), g.getNodeByName("C"), 30);
		output.print(String.valueOf(routeNum));
		
	}


}
