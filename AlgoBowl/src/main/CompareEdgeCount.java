package main;

import java.util.Comparator;

public class CompareEdgeCount implements Comparator<Node> {

	@Override
	public int compare(Node x, Node y) {

		if (x.getNumEdges() < y.getNumEdges()) {
			return 1;
		}
		if (x.getNumEdges() > y.getNumEdges()) {
			return -1;
		}
		
		return 0;
	}

}
