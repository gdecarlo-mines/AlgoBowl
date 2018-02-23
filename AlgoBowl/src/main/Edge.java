package main;

import java.util.HashSet;
import java.util.Set;

public class Edge {
	
	private int source;
	private int destination;
	
	private int weight;
	
	private Set<Integer> nodePair;

	public Edge(int a, int b, int w) {
		weight = w;
		nodePair = new HashSet<Integer>();
		nodePair.add(a);
		nodePair.add(b);
		source = a;
		destination = b;
	}

	public Set<Integer> getNodePair() {
		return nodePair;
	}

	public void setNodePair(Set<Integer> nodePair) {
		this.nodePair = nodePair;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	
	
}
