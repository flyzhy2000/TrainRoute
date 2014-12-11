package com.thoughtworks.test.trainroute;

public interface DirectedGraph {

	public static class Node {
		final String name;	
		
		public Node(String nodeName) {
			name = nodeName;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Node) {
				Node other = (Node)obj;
				return name.equals(other.name);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}
	}
	
	public Node getNodeByName(String name);
	
	public boolean edgeExist(Node start, Node end);
	
	public int edgeDistance(Node start, Node end);
	
	public int distance(Node[] stops);
	
	public int numOfRouteWithMaxNodes(Node start, Node end, int maxStop, boolean exactly);
	
	public int numOfRouteWithMaxDistance(Node start, Node end, int maxDistance);
	
	public int shortestDistance(Node start, Node end);
	
	
}
