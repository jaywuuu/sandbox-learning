import java.util.Iterator;

/*
 * Bag data structure where order doesn't matter implemented using double linked
 * list.
 * Iterable.
 */

public class Bag<Item> implements Iterable<Item> {
	private int size;
	private Node first;
	private Node last;
	
	public Bag() {
		size = 0;
		first = null;
		last = null;
	}
	
	public Bag(Item it) {
		size = 1;
		first = new Node(it);
		last = first;
	}
	
	public int size() {
		return size;
	}
	
	public void add(Item it) {
		if (it == null) return;
		if (size == 0 || first == null) {
			first = new Node(it);
			last = first;
		}
		else {
			last.next = new Node(it, null, last);
			if (size == 1) first.next = last.next;
			last = last.next;
		}
		size++;
	}
	
	// return item based on index
	public Item get(int idx) {
		if (idx < 0 || idx >= size) return null;
		Node n = first;
		// count until we reach idx, then stop and return that item
		for (int i = 0; i < idx; i++) n = n.next;
		return n.item;
	}
	
	// remove item based on index;
	// TODO: handle end cases for removing links
	public Item remove(int idx) {
		if (idx < 0 || idx >= size) return null;
		Node n = first;
		for (int i = 0; i < idx; i++) n = n.next;
		Item it = n.item;
		// hook up
		n.prev.next = n.next;
		n.next.prev = n.prev; 
		return it;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new BagIterator();
	}
	
	private class BagIterator implements Iterator<Item> {
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public Item next() {
			Item it = current.item;
			current = current.next;
			return it;
		}

		// does nothing
		@Override
		public void remove() {
			return;
		}

	}
	
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
		
		private Node(Item it) {
			item = it;
			next = null;
			prev = null;
		}
		
		private Node(Item it, Node n, Node p) {
			item = it;
			next = n;
			prev = p;
		}
	}
}
