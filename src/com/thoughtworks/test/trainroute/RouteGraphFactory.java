package com.thoughtworks.test.trainroute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.test.trainroute.AdjListGraph.AdjEdge;
import com.thoughtworks.test.trainroute.DirectedGraph.Node;

public class RouteGraphFactory {

	private static RouteGraphFactory factory;
	private RouteGraphFactory() {
		
	}
	
	public static RouteGraphFactory getInstance() {
		if (factory == null) {
			factory = new RouteGraphFactory();
		}
		
		return factory;
	}
	
	public DirectedGraph create(String[] nodeNames, String[] edges) {
		Map<String, Node> nodeMap = new HashMap<String, Node>();
		for (String name : nodeNames) {
			if (!nodeMap.containsKey(name)) {
				nodeMap.put(name, new Node(name));
			}			
		}		
		
		Map<Node, List<AdjEdge>> adjMap = new HashMap<Node, List<AdjEdge>>();
		
		for (String edge : edges) {
			String startNodeName = edge.substring(0, 1);   // example: "A" of "AB3"
			String endNodeName = edge.substring(1, 2);    // example: "B" of "AB3"
			int edgeWeight = Integer.parseInt(edge.substring(2));    // example: "3" of "AB3"
			
			Node startNode = nodeMap.get(startNodeName);
			Node endNode = nodeMap.get(endNodeName);
			if (startNode != null && endNode != null) {
				
			} 
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
		
		return new AdjListGraph(nodeMap, adjMap);
	}
	
}
