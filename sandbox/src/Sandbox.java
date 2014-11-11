import java.util.Hashtable;
import java.util.Iterator;

import org.junit.Test;

/*
 * playing around with stuff.
 * random functions that do stuff.
 */

public class Sandbox {
	private static final int LL_SIZE 					= 9; 					// size of linked list 
	private static final int ONECOUNT_TEST_VAL 			= 7;					// input to OneCounter
	private static final int BAG_SIZE					= 10;					// size of bag
	private static final int HASHTABLE_SIZE				= 20;					// initial size of hash table
	private static final String HASHTABLE_TEST_STRING	= "aabbabbbaacdaeefsad"; // string to process
	
	public static void main(String[] args) {
		testSingleLL();
	}
	
	@Test
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
	
	@Test
	public static void testOneCounter() {
		OneCounter counter = new OneCounter(ONECOUNT_TEST_VAL);
		counter.countDecimal();
		counter.countBinary();
		
		System.out.println("Ones in decimal: " + Integer.toString(counter.getDecCount()));
		System.out.println("Ones in binary: " + Integer.toString(counter.getBinCount()));
	}
	
	@Test
	public static void testBag() {
		Bag<Integer> bag = new Bag<Integer>();
		for (int i = 0; i < BAG_SIZE; i++) bag.add(i);
		
		System.out.println("size of bag: " + bag.size());
		
		for (int i = 0; i < BAG_SIZE; i++) System.out.print(bag.get(i) + " ");
		
		System.out.println();
		for (Iterator<Integer> it = bag.iterator(); it.hasNext();) {
			System.out.print(it.next() + " ");
		}
	}
	
	@Test
	public static void testArrayBag() {
		Bag<Integer>[] bagArray = (Bag<Integer>[]) new Bag[23];
		for (int i = 0; i < 23; i++) {
			bagArray[i] = new Bag<Integer>();
		}
	}
	
	@Test
	public static void practiceHashTable() {
		int strlength = HASHTABLE_TEST_STRING.length();
		Hashtable<String, Integer> htable = new Hashtable<>(HASHTABLE_SIZE);
		for (int i = 0; i < strlength; i++) {
			String a = HASHTABLE_TEST_STRING.substring(i, i+1);
			if (htable.containsKey(a)) htable.put(a, htable.get(a)+1);
			else htable.put(a, 1);
		}
		System.out.println("Number of unique characters: " + htable.size());
		
		for (String s : htable.keySet()) {
			System.out.println(s + " counts: " + htable.get(s));
		}
	}
	
	@Test
	public static void testBST() {
		BST<Integer, Integer> bst = new BST<>();
		System.out.println("size of bst: " + bst.size());
	}
	
}
