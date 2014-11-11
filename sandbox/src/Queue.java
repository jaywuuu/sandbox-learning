/*
 * Simple implementation of a queue
 */
public class Queue<Item> {
	private int size;
	private Node first;
	private Node last;
	
	public Queue() {
		size = 0;
		first = null;
		last = null;
	}
	
	public Queue(Item item) {
		size = 1;
		first = new Node(item);
		last = first;
	}
	
	public int size() {
		return this.size;
	}
	
	public void enqueue(Item it) {
		if (it == null) return;
		if (first == null || size == 0) first = new Node(it);
		else { 
			Node n = first;
			// advance until empty
			while (n.next != null) { n = n.next; }
			// add new node at the end of the queue
			n.next = new Node(it);
		}
		size++;
	}
	
	// returns null if no items in queue
	public Item dequeue() {
		if (size == 0 || first == null) return null;
		Item item = first.item;
		first = first.next;
		size--;
		return item;
	}
	
	public Item peek() {
		if (size == 0 || first == null) return null;
		return first.item;
	}
	
	private class Node {
		private Item item;
		private Node next;
		
		private Node (Item it) {
			this.item = it;
			this.next = null;
		}
		
		private Node (Item it, Node n) {
			this.item = it;
			this.next = n;
		}
 	}
}
