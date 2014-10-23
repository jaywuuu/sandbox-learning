/*
 * Randomized Queues and Deques
 */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // Keep track of both first and last nodes to maintain constant time
    // look-up.
    private Node first;
    private Node last;
    
    // Keep track of number of nodes added or removed so we can maintain
    // constant time look-up of the size of the deque.
    private int size;
    
    /* 
     * construct an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
    
    /*
     * is the deque empty?
     */
    public boolean isEmpty() {
        return (first == null || last == null);
    }
    
    /*
     * return the number of items on the deque
     */
    public int size() {
       return size;
    }
    
    /*
     * insert the item at the front
     */
    public void addFirst(Item item) {
        checkItemNull(item);
        
        // 1.  Keep a link to the first node.
        // 2.  Create a mew node and save to first.
        // 3.  Set the old first node.prev to the new first node.
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) last = first;
        else oldFirst.prev = first;
        
        // increment node count
        size++;
    }
    
    /*
     * insert the item at the end
     */
    public void addLast(Item item) {
        checkItemNull(item);
        
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        
        // increment node count
        size++;
    }
    
    /*
     * delete and return the item at the front
     */
    public Item removeFirst() {    
        // throw excpetion if empty
        checkEmpty();
        
        // 1.  Save a copy of first to be returned later.
        // 2.  Set first to old first's next node
        Item item = first.item;
        first = first.next;
        
        if (isEmpty()) last = null;
        else first.prev = null;
        
        // decrement node count
        size--;
        
        return item;
    }
    
    /* 
     * delete and return the item at the back
     */
    public Item removeLast() {
        checkEmpty();
        
        Item item = last.item;
        last = last.prev;
        
        if (isEmpty()) first = null;
        else last.next = null;
        
        size--;
        
        return item;
    }
    
    /*
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    /*
     * private iterator class.
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() { 
            return current != null; 
        }
        
        public void remove() {
            // return exception as this is unsupported.
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            // if no more items, throw exception.
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    /* 
     * throws exception if Item item is null
     */
    private void checkItemNull(Item item) {
        if (item == null) throw new NullPointerException();
    }
    
    /*
     * throw an excpetion if attempting to remove an item from an empty deque.
     */
    private void checkEmpty() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
    }
    
    /*
     * We keep track of both next and previous nodes to make it easy and fast
     * to remove the last item.
     */
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
     /*
     * unit testing
     * 
     * usage: Deque <test case> [data]
     * 
     * <test case>
     * 0: base test case instantiating a deque with [data] and remove items.
     * 1: test remove first item exception.
     * 2: test remove last item exception.
     * 3: test add null item exception to first.
     * 4: test add null item exception to last.
     * 5: test iterator remove.
     * 6: test iterator no element exception from iterator.next().
     * 7: add 1 item, remove 1 item.
     */
    public static void main(String[] args) {
        // process arguments <test case> [extra]
        int testCase = Integer.parseInt(args[0]);
        
        // Instantiate deque
        Deque<String> deque = new Deque<String>();
        
        deque.printStatus();
        
        // test case 0.
        if (testCase == 0) {
            for (int i = 1; i < args.length; i++) {
                deque.addLast(args[i]);
            }
            deque.printStatus();

            // First and last item.
            StdOut.print("First item: ");
            StdOut.println(deque.first.item);
            StdOut.print("Last item: ");
            StdOut.println(deque.last.item);
            
            // use iterator to print items.
            StdOut.println("Testing nested iterators.");
            for (Iterator<String> s = deque.iterator(); s.hasNext();) {
                for (Iterator<String> i = deque.iterator(); i.hasNext();) {
                    StdOut.println(i.next());
                }
                s.next();
            }
            
            // remove an item, print an item.
            StdOut.println("Removing items..");
            while (deque.size() > 0) {
                StdOut.println(deque.last.item);
                deque.removeLast();
            }
            
            deque.printStatus();
        }
        
        // Check exceptions.
        if (testCase == 1) deque.removeFirst();
        else if (testCase == 2) deque.removeLast();
        else if (testCase == 3) deque.addFirst(null);
        else if (testCase == 4) deque.addLast(null);
        else if (testCase == 5) deque.iterator().remove();
        else if (testCase == 6) deque.iterator().next();
        else if (testCase == 7) {
            deque.addFirst("First");
            deque.printStatus();
            StdOut.println("first " + deque.first.item);
            StdOut.println("last: " + deque.last.item);
            deque.removeLast();
            deque.printStatus();
        }
    }

    private void printStatus() {
        // Current size
        StdOut.printf("deque size: %d\n", size());
        
        // Is empty?
        StdOut.printf("deque empty? %b\n", isEmpty());
    }
}