/*
 * Simple implementation of a single linked list.
 */

/*
 * Adding an node takes linear time since we don't keep track of the last node.
 * Getting the size of the node also takes linear time since it's not being stored.
 */
public class SingleLL<Item> {
	Node first;

	public SingleLL() {
		first = null;
	}

	/* private class for single linked list */
	private class Node {
		Item item;
		Node next;

		public Node(Item item) {
			this.item = item;
			this.next = null;
		}
	}

	/* return number of elements in linked list *
	 * O(n) time.
	 */
	public int size() {
		if (first == null) return 0;
		Node last = this.first;
		int size = 1;
		while (last.next != null) {
			last = last.next;
			size++;
		}
		return size;
	}
	
	/* O(n) time */
	public void reverse() {
		if (first == null) return;		// no nodes
		if (first.next == null) return; // only one node
		Node i = first;
		Node prev = first;
		while (i != null) {
			Node oldNext = i.next;
			if (i == first) i.next = null;
			else i.next = prev;
			prev = i;
			i = oldNext;
		}
		first = prev;
	}

	/* O(n) time */
	public void add(Item item) {
		Node newNode = new Node(item);
		if (first == null) {
			first = newNode;
		}
		else {
			Node last = this.findLast();
			last.next = newNode;
		}
	}
	
	/* constant time */
	public Item removeFirst() {
		Item ret = first.item;
		first = first.next;
		return ret;
	}
	
	/* O(n) time */
	public Item removeLast() {
		// find second last node.
		Node secondLast = first;
		while (secondLast.next.next != null) 
			secondLast = secondLast.next;
		
		Item ret = secondLast.next.item;
		secondLast.next = null;
		return ret;
	}
	
	/* O(n) */
	public void printList() {
		if (first == null) return;
		Node current = first;
		while (current.next != null) {
			System.out.print(current.item + ", ");
			current = current.next;
		}
		System.out.print(current.item + "\n");
	}

	/* follow next pointer until you get to the last.  O(n)*/
	private Node findLast() {
		/* should probably handle exception when there are no elements. */
		if (this.first == null) return null;
		Node last = this.first;
		while (last.next != null) {
			last = last.next;
		}
		return last;
	}
}
