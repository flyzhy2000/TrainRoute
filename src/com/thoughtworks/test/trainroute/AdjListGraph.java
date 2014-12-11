package com.thoughtworks.test.trainroute;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjListGraph implements DirectedGraph{

	public static class AdjEdge{
		final Node node;
		final int weight;
		
		public AdjEdge(Node edgeEndNode, int edgeWeight) {
			node = edgeEndNode;
			weight = edgeWeight;
		}
	}
	
	
	private Map<Node, List<AdjEdge>> adjMap;
	private Set<Node> nodeSet;
	private Map<String, Node> nodeMap;
	
	public AdjListGraph(Map<String, Node> nodeMap, Map<Node, List<AdjEdge>> adjancentNodesMap) {
		this.adjMap = adjancentNodesMap;
		this.nodeMap = nodeMap;
	}
	
	@Override
	public int distance(Node[] stops) {
		int distance = 0;
		
		if (stops.length < 2) {
			return 0;
		} 
		
		for (int i = 1; i < stops.length; i++) {
			Node start = stops[i-1];
			Node end = stops[i];
			List<AdjEdge> adjNodeList = adjMap.get(start);
			if (adjNodeList == null) {
				return 0;    // there's no such route
			}
			else {
				boolean found = false;
				for (AdjEdge adjNode : adjNodeList) {
					if (adjNode.node.equals(end)) {
						distance += adjNode.weight;    // the route found!
						found = true;
						break;
					}
				}
				if (!found) {
					return 0;    // there's no such route
				}
			}
		}
		return distance;
	}

	@Override
	public Node getNodeByName(String name) {		
		return nodeMap.get(name);
	}

	// if there's a direct edge that goes from start to end
	@Override
	public boolean edgeExist(Node start, Node end) {
		List<AdjEdge> edgeList = adjMap.get(start);
		if (edgeList != null && edgeList.size() > 0) {
			for (AdjEdge edge : edgeList) {
				if (edge.node.equals(end)) {
					return true;
				}
			}
		}
		
		return false;
	}	
	
	@Override
	public int edgeDistance(Node start, Node end) {
		List<AdjEdge> edgeList = adjMap.get(start);
		if (edgeList != null && edgeList.size() > 0) {
			for (AdjEdge edge : edgeList) {
				if (edge.node.equals(end)) {
					return edge.weight;
				}
			}
		}		
		
		return 0;    // if there's no such a direct edge that goes from start to end
	}

	@Override
	public int numOfRouteWithMaxNodes(Node start, Node end, int maxStop, boolean exactly) {
		if (maxStop == 0) {
			return 0;
		}
		
		// found a direct edge that goes from start to end
		boolean found = (exactly && (maxStop == 1) && edgeExist(start, end))
						|| (!exactly && (maxStop > 0) && edgeExist(start, end));
		
		int num = 0;  // number of routes that goes from the end of current edge to the final end 
		for (AdjEdge edge : adjMap.get(start)) {
			num += numOfRouteWithMaxNodes(edge.node, end, maxStop-1, exactly);
		}
		
		return (found)? (num+1) : num;
	}

	@Override
	public int numOfRouteWithMaxDistance(Node start, Node end, int maxDistance) {
		if (maxDistance <= 0) {
			return 0;
		}
		
		int distance = edgeDistance(start, end);
		boolean found = (distance > 0) && (distance <= maxDistance);
		
		int num = 0;
		for (AdjEdge edge : adjMap.get(start)) {
			num += numOfRouteWithMaxDistance(edge.node, end, maxDistance - edge.weight);
		}
		
		return (found)? (num+1) : num;
	}

	// use dijkstra algorithm to calculate the shortest distance from "start" to "end"
	@Override
	public int shortestDistance(Node start, Node end) {  
		Set<Node> fixedSet = new HashSet<Node>();     // nodes whose shortest distance to "start" are already fixed
		
		Map<Node, Integer> distanceToNodes = new HashMap<Node, Integer>();   // pair of <B, 5> means it's a distance of 5 from "start" to B
		for (AdjEdge edge : adjMap.get(start)) {   // init
 			distanceToNodes.put(edge.node, edge.weight);
		}
				
		while(fixedSet.size() < nodeMap.size()) {
			Node n = nearestUnfixedNode(distanceToNodes, fixedSet);
			if (n.equals(end)) {
				break;     // found
			}
			fixedSet.add(n);
			
			// update distance map if there's a shorter route found
			int d = distanceToNodes.get(n);
			List<AdjEdge> edgesOfNearest = adjMap.get(n);
			for (AdjEdge e : edgesOfNearest) {
				if (!distanceToNodes.containsKey(e.node)
					|| (d + e.weight < distanceToNodes.get(e.node)) ) {
					distanceToNodes.put(e.node, d + e.weight);
				}
			}
			
		}
		
		return distanceToNodes.get(end);
	}
	
	private Node nearestUnfixedNode(Map<Node, Integer> distanceToNodes, Set<Node> fixedSet) {
		Node nearestNode = null;
		int smallestDistance = 65535;
		
		for (Node n : distanceToNodes.keySet()) {
			if (!fixedSet.contains(n) && distanceToNodes.get(n) < smallestDistance) {
				smallestDistance = distanceToNodes.get(n);
				nearestNode = n;
			}
		} 
		
		return nearestNode;
	}

}
