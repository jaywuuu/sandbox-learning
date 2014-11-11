/* Double-linked list 
 * 
 */
public class DoubleLL<Item> {
	private Node first;
	private int size;
	
	public DoubleLL() {
		first = null;
		size = 0;
	}
	
	public DoubleLL(Item it) {
		first = new Node(it, null, null);
		size = 1;
	}
	
	public void add(Item it) {
		if (it == null) return;
		if (first == null) {
			first = new Node(it, null, null);
		}
		else {
			Node n = first;
			while (n.next != null) { n = n.next; }
			Node newNode = new Node(it, null, n);
			n.next = newNode;
		}
	}
	
	private class Node {
		public Item item;
		private Node next;
		private Node prev;
		
		public Node(Item it, Node next, Node prev) {
			this.item = it;
			this.next = next;
			this.prev = prev;
		}
	}
}
