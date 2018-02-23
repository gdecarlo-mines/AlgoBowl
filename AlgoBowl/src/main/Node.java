package main;

public class Node {
	
	private int identity;
	private int numEdges;
	
	public Node(int identity, int numEdges) {
		super();
		this.identity = identity;
		this.numEdges = numEdges;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public int getNumEdges() {
		return numEdges;
	}
	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}
	@Override
	public String toString() {
		return Integer.toString(identity);
	}
	
	

}
