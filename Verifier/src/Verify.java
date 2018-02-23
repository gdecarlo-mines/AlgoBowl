import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Verify {

	private int numNodes;
	private int numEdges;
	private Map<Edge, Integer> inputs = new HashMap<Edge, Integer>();

	// initialize verify class with number of nodes, edges, and input map from file
	public Verify(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));

			setNumNodes(scanner.nextInt());
			setNumEdges(scanner.nextInt());
			while (scanner.hasNextInt()) {
				// map each edge to its weight
				inputs.put(new Edge(scanner.nextInt(), scanner.nextInt()), scanner.nextInt());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("The specified file could not be found");
		}
	}

	// main driver function
	public static void main(String args[]) {

		String inputFile = "groupInputs/input_group19.txt";
		String solutionFile = "solnToGroupInputs/solution19.txt";

		Verify main = new Verify(inputFile);
		main.verifySolution(solutionFile);

		/*
		Map<Integer, String> allGroupSolns = new HashMap<Integer, String>();
		int numGroups = 28;
		for (int i = 1; i <= numGroups; i++) {
			allGroupSolns.put(i, "output_from_" + i + "_to_18.txt");
		}
		 */
		// System.out.println(allGroupSolns);
	}
	
	// condense verification of size and weight into one function call
	public boolean verifySolution(String solutionFile) {
		
		Solution solution = new Solution(solutionFile);
		
		if (verifyWeight(solution) & verifySizes(solution)) {
			System.out.println("Solution is valid");
			return true;
		}
		else {
			System.out.println("Solution is not valid");
			return false;
		}
	}

	// check that weight is valid based off solution sets
	public boolean verifyWeight(Solution solution) {
		//Solution solution = new Solution(solutionFile);
		List<Integer> nodeSet = solution.getFirstSet();
		int total = 0;
		int doubleCount = 0;

		// traverse through all keys within the map of edges and weights and if either X
		// or Y value of the key is not within the solution set then that edge(key) is considered
		// broken and the weight(value)
		// is added to a total sum
		for (Edge e : inputs.keySet()) {
			if (!nodeSet.contains(e.getStart()) || !nodeSet.contains(e.getTerminal())) {

				total += inputs.get(e);

			}
			// **NOTE the above branch will "break" edges where both X and Y are not in the
			// solution set
			// this is incorrect since that edge could still be valid in the other set for
			// the solution
			// By keeping a running total for the weights of those edges you can subtract
			// that value
			// from the total sum weight to get the correct weight for the solution
			if (!nodeSet.contains(e.getStart()) & !nodeSet.contains(e.getTerminal())) {
				// System.out.println(p.getX()+" "+p.getY());
				// System.out.println(nodeSet);
				doubleCount += inputs.get(e);
			}
			// System.out.println(doubleCount);
		}

		if (total - doubleCount == solution.getWeight()) {
			return true;
		} else {
			System.out.println("Weight of solution is incorrect");
			return false;
		}
	}
	
	// check that all the nodes are evenly distributed between the two sets of the solution
	public boolean verifySizes(Solution solution) {

		if ((solution.getFirstSet().size() == numNodes/2) & (solution.getSecondSet().size() == numNodes/2)) {
			return true;
		}
		else {
			System.out.println("Nodes are not evenly distributed");
			return false;
		}
	}

	public Map<Edge, Integer> getInputs() {
		return inputs;
	}

	public int getNumNodes() {
		return numNodes;
	}

	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}

	public int getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}

}