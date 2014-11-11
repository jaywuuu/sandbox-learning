/*
 * Simple implementation of a stack using linked list
 */
public class Stack<Item> {
	private int size;
	private Node last;
	
	public Stack() {
		size = 0;
		last = null;
	}
	
	public Stack(Item it) {
		size = 1;
		last = new Node(it);
	}
	
	public int size() {
		return size;
	}
	
	public void push(Item it) {
		if (it == null) return;
		if (size == 0 || last == null) last = new Node(it);
		else {
			Node n = new Node(it, last);
			last = n;
		}
		size++;
	}
	
	public Item pop() {
		if (size == 0 || last == null) return null;
		Item item = last.item;
		last = last.prev;
		size--;
		return item;
	}
	
	public Item peek() {
		if (size == 0 || last == null) return null;
		return last.item;
	}
	
	private class Node {
		private Item item;
		private Node prev;
		
		private Node () {
			item = null;
			prev = null;
		}
		
		private Node(Item it) {
			item = it;
			prev = null;
		}
		
		private Node(Item it, Node p) {
			item = it;
			prev = p;
		}
	}
}
