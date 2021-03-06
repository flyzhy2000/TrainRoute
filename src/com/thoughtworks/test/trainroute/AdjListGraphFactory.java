package com.thoughtworks.test.trainroute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.test.trainroute.AdjListGraph.AdjEdge;
import com.thoughtworks.test.trainroute.DirectedGraph.Node;

public class AdjListGraphFactory {

	private static AdjListGraphFactory factory;
	private AdjListGraphFactory() {
		
	}
	
	public static AdjListGraphFactory getInstance() {
		if (factory == null) {
			factory = new AdjListGraphFactory();
		}
		
		return factory;
	}
	
	public DirectedGraph create(String[] edges) {
		Set<Node> nodeSet = new HashSet<Node>();
		Map<Node, List<AdjEdge>> adjMap = new HashMap<Node, List<AdjEdge>>();
		
		for (String edge : edges) {
			edge = edge.trim();
			String startNodeName = edge.substring(0, 1);   // example: "A" of "AB3"
			String endNodeName = edge.substring(1, 2);    // example: "B" of "AB3"
			int edgeWeight = Integer.parseInt(edge.substring(2));    // example: "3" of "AB3"
			
			Node startNode = new Node(startNodeName);
			Node endNode = new Node(endNodeName);
			nodeSet.add(startNode);
			nodeSet.add(endNode);
			
			AdjEdge e = new AdjEdge(endNode, edgeWeight);
			if (!adjMap.containsKey(startNode)) {
				List<AdjEdge> adjEdges = new ArrayList<AdjEdge>();
				adjEdges.add(e);
				adjMap.put(startNode, adjEdges);
			}
			else {
				List<AdjEdge> adjEdges = adjMap.get(startNode);
				adjEdges.add(e);
			}
		}
		
		return new AdjListGraph(nodeSet, adjMap);
	}
	
}
