import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	private int weight;
	private List<Integer> firstSet = new ArrayList<Integer>();
	private List<Integer> secondSet = new ArrayList<Integer>();

	// Read in solution file initialize corresponding weight and sets
	public Solution(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));

			setWeight(scanner.nextInt());
			List<Integer> temp = new ArrayList<Integer>();
			while (scanner.hasNextInt()) {
				temp.add(scanner.nextInt());
			}
			scanner.close();
			// Splits list into two sets
			setFirstSet(temp.subList(0, temp.size() / 2));
			setSecondSet(temp.subList(temp.size() / 2, temp.size()));

		} catch (FileNotFoundException e) {
			System.out.println("The specified file could not be found");
		}
	}

	public List<Integer> getFirstSet() {
		return firstSet;
	}

	public void setFirstSet(List<Integer> firstSet) {
		this.firstSet = firstSet;
	}

	public List<Integer> getSecondSet() {
		return secondSet;
	}

	public void setSecondSet(List<Integer> secondSet) {
		this.secondSet = secondSet;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}