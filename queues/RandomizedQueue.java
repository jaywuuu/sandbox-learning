/*
 * Randomized Queues and Deques
 * 
 */

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] item;
    private int size;
    
    // Construct empty queue
    public RandomizedQueue() {
        item = (Item[]) new Object[2]; // initially start with 2 empty
        size = 0;
    }
    
    // is queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }
    
    // return hte number of items in queue
    public int size() {
        return size;
    }
    
    // add item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        // resize if necesary
        if (size == this.item.length) resize(2*this.item.length);
        
        this.item[size] = item;
        size++;
    }
    
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        int rand = StdRandom.uniform(0, size); // get random number
        Item retval = item[rand]; // save that item to be returned
        item[rand] = item[size-1]; // copy over last item
        item[size-1] = null; // delete last item.
        size--;
        
        // resize if size is 1/4 of array size
        if (size > 0 && size == item.length/4) resize(item.length/2);
        
        return retval;
    }
    
    // return, but don't delete the item
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        int rand = StdRandom.uniform(0, size); // get random number
        return item[rand];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomIterator(); }
    
    private class RandomIterator implements Iterator<Item> {
        // for iterator implementation, we will create a copy of the item
        // array, shuffle, then return one by one.
        private int idx;
        private Item[] it;
        
        public RandomIterator() {
            idx = 0;
            it = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                it[i] = item[i];
            }
            StdRandom.shuffle(it);
        }
        
        public boolean hasNext() {
            return (idx < size);
        }
        
        public void remove() {
            // return exception as this is unsupported.
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            // throw exception if iterator is emtpy
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item retval = it[idx];
            idx++;
            return retval;
        }
    }
    
    // array resizing
    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = item[i];
        }
        item = newArray;
    }
    
    // Unit testing
    public static void main(String[] args) {
        RandomizedQueue rq = new RandomizedQueue();
        rq.printStatus();

        // take arguments and fill out array.
        for (String s : args) rq.enqueue(s);
        
        rq.printStatus();
        
        // sample.
        StdOut.println("Sampling...");
        for (int i = 0; i < rq.size(); i++) { 
            StdOut.println(rq.sample());
        }
        
        // use iterator
        StdOut.println("Testing iterators...");
        for (Iterator<String> i = rq.iterator(); i.hasNext();) {
            for (Iterator<String> j = rq.iterator(); j.hasNext();) {
                StdOut.println("j.next(): " + j.next());
            }
            StdOut.println("i.next(): " + i.next());
        }
        
        StdOut.println("Testing dequeue..");
        
        // dequeue.
        StdOut.println("dequeue: " + rq.dequeue());
        rq.printStatus();
        
        // dequeue the rest.
        int size = rq.size();
        for (int i = 0; i < size; i++) { 
            StdOut.println(rq.dequeue());
        }
        rq.printStatus();
    }
        
    private void printStatus() {
        StdOut.printf("size: %d \n", size());
        StdOut.printf("empty? %b \n", isEmpty());
    }
}