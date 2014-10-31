/*
 * playing around with stuff.
 * random functions that do stuff.
 */

public class Sandbox {
	private static final int LL_SIZE 				= 9; 		// size of linked list 
	private static final int ONECOUNT_TEST_VAL 		= 7;	// input to OneCounter
	
	public static void main(String[] args) {
		testOneCounter();
	}
	
	public static void testSingleLL() {
		SingleLL<String> linkedList = new SingleLL<>();
		for (int i = 0; i < LL_SIZE; i++) linkedList.add(Integer.toString(i+1));
		
		// print linked list.
		System.out.println("Linked list: ");
		linkedList.printList();
		
		// reverse order
		linkedList.reverse();
		
		// print again
		System.out.println("Reversed order: ");
		linkedList.printList();
	}
	
	public static void testOneCounter() {
		OneCounter counter = new OneCounter(ONECOUNT_TEST_VAL);
		counter.countDecimal();
		counter.countBinary();
		
		System.out.println("Ones in decimal: " + Integer.toString(counter.getDecCount()));
		System.out.println("Ones in binary: " + Integer.toString(counter.getBinCount()));
	}
}
