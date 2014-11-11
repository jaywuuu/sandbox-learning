import sun.org.mozilla.javascript.internal.Node;


public class BST<K extends Comparable<K>, V> {
	private int size;
	private Node root;
	
	public BST() {
		size = 0;
		root = null;
	}
	
	public BST(K key, V val) {
		root.key = key;
		root.value = val;
		size = 1;
	}
	
	public int size() {
		return size;
	}
	
	public void put(K key, V val) {
		root = put(root, key, val);
		size++;
	}
	
	public Node put(Node x, K key, V val) {
		if (x == null) return new Node(key, val, null, null);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = put(x.left, key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else x.value = val;
		return x;
	}
	
	// get value given a key
	public V getValue(K key) {
		return getValue(root, key);
	}
	
	private V getValue(Node x, K key) {
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x.value;
		// go left
		else if (cmp < 0) return getValue(x.left, key);
		else if (cmp > 0) return getValue(x.right, key);
		
		return x.value;
	}
	
	// keep going left until we get to null.
	public V getMinValue() {
		Node current = root;
		while (current.left != null) { current = current.left; }
		return current.value;
	}
	
	// keep going right until null
	public V getMaxValue() {
		Node current = root;
		while ( current.right != null) { current = current.right; }
		return current.value;
	}
	
	
	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;
		
		private Node(K k, V v, Node l, Node r) {
			key = k;
			value = v;
			left = l;
			right = r;
		}
	}
}
