import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestSet {
	
	public static void main(String[] args) {

		testAddAndSize(); 
		
		testContains();

		testRemove();
		
		testExceptions();
		
	}
	
	public static void testAddAndSize () {
		LinkedSet<Integer> set = new LinkedSet<Integer>();
		
		int s1 = set.size();

		set.add(25);
		set.add(11);
		set.add(37);
		
		int s2 = set.size();
		
		set.add(25);
		
		int s3 = set.size();
		
		boolean b1 = s1 == 0;
		boolean b2 = set.size() == 3;
		boolean b3 = set.size() == 3;
		
		if (b1 && b2 && b3) {
			System.out.println("testAddAndSize - Looks good!");
		} else {
			System.out.println("testAddAndSize - Not quite");
			System.out.println("\nExpected output:");
			System.out.println("Set {} size: 0");
			System.out.println("Set {25, 11, 37} size: 3");
			System.out.println("Set {25, 11, 37} size: 3");
			System.out.println("\nYour output:");
			System.out.println("Set {} size: " + s1);
			System.out.println("Set {25, 11, 37} size: " + s2);
			System.out.println("Set {25, 11, 37} size: " + s3);
			System.out.println();
		}
	}
	
	public static void testContains () {
		LinkedSet<Integer> set = new LinkedSet<Integer>();
		
		boolean b1 = !set.contains(11);
		
		set.add(25);
		set.add(11);
		set.add(37);
		
		boolean b2 = set.contains(11);
		boolean b3 = !set.contains(49);
		
		if (b1 && b2 && b3) {
			System.out.println("testContains - Looks good!");
		} else {
			System.out.println("testContains - Not quite");
			System.out.println("\nExpected output:");
			System.out.println("Set {} contains(11): false");
			System.out.println("Set {25, 11, 37} contains(11): true");
			System.out.println("Set {25, 11, 37} contains(49): false");
			System.out.println("\nYour output:");
			System.out.println("Set {} contains(11): " + b1);
			System.out.println("Set {25, 11, 37} contains(11): " + b2);
			System.out.println("Set {25, 11, 37} contains(49): " + b3);
			System.out.println();
		}
	}
	
	public static void testRemove () {
		LinkedSet<Integer> set = new LinkedSet<Integer>();

		set.add(25);
		set.add(11);
		set.add(37);
		
		set.remove(11);
		set.remove(25);
		
		boolean b1 = set.size() == 1;
		boolean b2 = !set.contains(11);
		boolean b3 = set.contains(37);
		
		if (b1 && b2 && b3) {
			System.out.println("testRemove - Looks good!");
		} else {
			System.out.println("testRemove - Not quite");
			System.out.println("\nExpected output:");
			System.out.println("Set {37} size(): 1");
			System.out.println("Set {37} contains(11): false");
			System.out.println("Set {37} contains(37): true");
			System.out.println("\nYour output:");
			System.out.println("Set {37} size(): " + set.size());
			System.out.println("Set {37} contains(11): " + b2);
			System.out.println("Set {37} contains(37): " + b3);
			System.out.println();
		}
	}
	
	public static void testExceptions () {
		LinkedSet<Integer> set = new LinkedSet<Integer>();

		set.add(25);
		set.add(11);
		set.add(37);
		
		boolean b1 = false;
		Exception ex = null;
		
		try {
			set.remove(5);
		} catch (SetException e) {
			b1 = true;
		} catch (Exception e) {
			ex = e;
			b1 = false;
		}

		if (b1) {
			System.out.println("testExceptions - Looks good!");
		} else {
			System.out.println("testExceptions - Not quite");
			System.out.println("\nExpected exception type: SetException");
			if (ex != null) System.out.println("Your exception type: " + ex.getClass());
			else System.out.println("Your exception type: null");
		}
	}

}
