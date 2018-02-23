import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class VerificationTests {

	// Test initialization of solution file into solution class
	@Test
	public void solutionInitTest() {
		Solution main = new Solution("testSoln/valid_default_soln.txt");
		assertEquals(4, main.getWeight());
		List<Integer> set = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		assertTrue(set.equals(main.getFirstSet()));
		set = new ArrayList<Integer>(Arrays.asList(4, 5, 6));
		assertTrue(set.equals(main.getSecondSet()));

		main = new Solution("testSoln/valid_default_soln2.txt");
		assertEquals(2, main.getWeight());
		set = new ArrayList<Integer>(Arrays.asList(1, 4, 6));
		assertTrue(set.equals(main.getFirstSet()));
		set = new ArrayList<Integer>(Arrays.asList(2, 3, 5));
		assertTrue(set.equals(main.getSecondSet()));

		main = new Solution("testSoln/valid_Mehtallica_soln.txt");
		assertEquals(143, main.getWeight());
		set = new ArrayList<Integer>(Arrays.asList(2, 3, 8, 10, 12, 14, 17, 18, 19, 20));
		assertTrue(set.equals(main.getFirstSet()));
		set = new ArrayList<Integer>(Arrays.asList(1, 4, 5, 6, 7, 9, 11, 13, 15, 16));
		assertTrue(set.equals(main.getSecondSet()));

	}

	// test initialization of verify class with input file
	@Test
	public void verifyInitTest() {
		Verify main = new Verify("testInputs/default_input.txt");
		assertEquals(6, main.getNumNodes());
		assertEquals(8, main.getNumEdges());
		assertEquals(8, main.getInputs().size());

		main = new Verify("testInputs/input_Mehtallica.txt");
		assertEquals(20, main.getNumNodes());
		assertEquals(26, main.getNumEdges());
		assertEquals(26, main.getInputs().size());
	}

	// verify that nodes are evenly distributed into the two solution sets
	@Test
	public void verifySizeTest() {
		Verify main = new Verify("testInputs/default_input.txt");
		
		Solution solution = new Solution("testSoln/valid_default_soln.txt");
		assertTrue(main.verifySizes(solution));
		
		solution = new Solution("testSoln/invalid_default_soln.txt");
		assertTrue(!main.verifySizes(solution));
		
		main = new Verify("testInputs/input_Mehtallica.txt");
		
		solution = new Solution("testSoln/valid_Mehtallica_soln.txt");
		assertTrue(main.verifySizes(solution));
		
		
		
	}
	
	// verify the weights of solutions
	@Test
	public void verifyWeightTest() {
		Verify main = new Verify("testInputs/default_input.txt");

		Solution solution = new Solution("testSoln/valid_default_soln.txt");
		assertTrue(main.verifyWeight(solution));
		
		solution = new Solution("testSoln/valid_default_soln2.txt");
		assertTrue(main.verifyWeight(solution));
		
		solution = new Solution("testSoln/invalid_default_soln.txt");
		assertTrue(!main.verifyWeight(solution));
		

		main = new Verify("testInputs/input_Mehtallica.txt");

		solution = new Solution("testSoln/valid_Mehtallica_soln.txt");
		assertTrue(main.verifyWeight(solution));
		
		solution = new Solution("testSoln/valid_Mehtallica_soln2.txt");
		assertTrue(main.verifyWeight(solution));
		
		solution = new Solution("potential_Mehtallica_soln.txt");
		assertTrue(main.verifyWeight(solution));
	}

}