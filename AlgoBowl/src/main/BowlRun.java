package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class BowlRun {

	
	private int numNodes;
	private ArrayList<Edge> edges;
	private ArrayList<Integer> list;
	private ArrayList<Integer> edgeCount;
	private ArrayList<Node> listNodes;
	private Set<Integer> nodes;
	private Set<Node> set1;
	private Set<Node> set2;
	private Map<Integer, ArrayList<Integer>> myMap;
	private ArrayList<Integer> traces;
	private int totalWeight;
	private ArrayList<Integer> missingNodes;
	
	PriorityQueue<Node> myPQ;
	Comparator<Node> comparator;
	

	public BowlRun() {
		edges = new ArrayList<Edge>();
		list = new ArrayList<Integer>();
		nodes = new HashSet<Integer>();
		listNodes = new ArrayList<Node>();
		edgeCount = new ArrayList<Integer>();
		set1 = new HashSet<Node>();
		set2 = new HashSet<Node>();
		myMap = new HashMap<Integer, ArrayList<Integer>>();
		traces = new ArrayList<Integer>();
		missingNodes = new ArrayList<Integer>();
		comparator = new CompareEdgeCount();
		myPQ = new PriorityQueue<Node>(10, comparator);

		totalWeight = 0;
	}

	public void readData() {

		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(new File("inputData.txt"));
			numNodes = scan.nextInt();
			scan.nextInt();
			while (scan.hasNext()) {
				Edge edge = new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt());
				edges.add(edge);
			}

		} catch(Exception e) {
			System.out.println("Error reading file");
		}
	}

	public void createListFindNodes() {

		for (int i = 0; i < edges.size(); i++) {

			list.add(edges.get(i).getSource());
			list.add(edges.get(i).getDestination());

			nodes.add(edges.get(i).getSource());
			nodes.add(edges.get(i).getDestination());
		}

	}
	
	private void findMissingNodes() {
		// TODO Auto-generated method stub
		for (int i = 1; i < numNodes+1; i++) {
			if (!nodes.contains(i)) {
				missingNodes.add(i);
			}
		}
		
	}

	public void createListOfNodes() {
		Integer[] arr = nodes.toArray(new Integer[nodes.size()]);

		//for loop to find number of edges each node has and add it to edgeCount
		for (int i = 0; i < arr.length; i++) {
			int count = 0;

			traces = new ArrayList<Integer>();
			for (int j = 0; j < list.size(); j++) {


				if (arr[i] == list.get(j)) {
					count++;

					if (j % 2 == 0) {
						traces.add(list.get(j+1));
					}
					else if (j % 2 != 0) {
						traces.add(list.get(j-1));
					}
				}
				myMap.put(arr[i], traces);
			}		

			edgeCount.add(count);
		}

		//Create list of nodes
		for (int i = 0; i < arr.length; i++) {
			listNodes.add(new Node(arr[i], edgeCount.get(i)));
		}

	}

	public void updateNodeList() {


		for (Node n : listNodes) {
			myPQ.add(n);
		}


	}

	public void createSets() {
		int goal = numNodes/2;
		int counter = 0;
	
		while (counter < goal) {

			Node pivot = myPQ.peek();

			set1.add(myPQ.remove());
			if (set1.size() >= goal) {
				break;
			}

			for (int i = 0; i < pivot.getNumEdges(); i++) {
		
				for (Node n : listNodes) {
					if (n.getIdentity() == myMap.get(pivot.getIdentity()).get(i)) {
						set1.add(n);
					}
				}
				if (set1.size() >= goal) {
					break;
				}
			}

			if (set1.size() >= goal) {
				break;
			}
			counter++;
		}
		
		for (Node n : listNodes) {
			if (!set1.contains(n)) {
				set2.add(n);
			}
		}
		
		
	}
	
	public void cutEdges() {
		
		for (Node n : set1) {
			for (Node o : set2) {
				
				for (Edge e: edges) {
					if ((e.getNodePair().contains(n.getIdentity())) && (e.getNodePair().contains(o.getIdentity()))) {			
						totalWeight += e.getWeight();
					}
				}	
			}
		}
	}
	
	public void writeToFile() throws FileNotFoundException {
	
		ArrayList<Integer> setOne = new ArrayList<Integer>();
		ArrayList<Integer> setTwo = new ArrayList<Integer>();
		
		System.out.println("Missing: " + missingNodes.toString());
		for (Node n : set1) {
			setOne.add(n.getIdentity());
		}
		for (Node n : set2) {
			setTwo.add(n.getIdentity());
		}
		
		PrintWriter writer = new PrintWriter("outputFile.txt");
		writer.println(totalWeight);
		
		for (int i = 0; i < setOne.size() - 1; i++) {
			writer.print(setOne.get(i) + " ");
		}
		writer.print(setOne.get(setOne.size()-1));
		writer.println();
		for (int i = 0; i < setTwo.size() - 1; i++) {
			writer.print(setTwo.get(i) + " ");
		}
		
		for (int i = 0; i < missingNodes.size() ; i++) {
			writer.print(missingNodes.get(i) + " ");
		}
		writer.print(setTwo.get(setTwo.size() - 1));
		writer.close();
	}

	public static void main(String[] args) throws FileNotFoundException {

		BowlRun run = new BowlRun();
		run.readData();
		run.createListFindNodes();
		run.findMissingNodes();
		run.createListOfNodes();
		run.updateNodeList();
		run.createSets();
		run.cutEdges();
		run.writeToFile();
		System.out.println("List of Nodes: " + run.listNodes.toString());
		System.out.println("Total Nodes: " + run.listNodes.size());
		System.out.println("Set1: " + run.set1.toString() + "\nSize: " + run.set1.size());
		System.out.println("Set2: " + run.set2.toString() + "\nSize: " + run.set2.size());
		System.out.println("Total Weight: " + run.totalWeight);
		System.out.println("Missing Nodes: " + run.missingNodes.toString());
		System.out.println("Size: " + run.missingNodes.size());
	}

	
}

