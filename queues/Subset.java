/*
 * Randomized Queues and Deques
 * Subset.java client program.
 */

public class Subset {
    private RandomizedQueue<String> rq = new RandomizedQueue<String>();
    private int k;
    
    public static void main(String[] args) {
        Subset sub = new Subset();
        
        // process argument
        sub.k = Integer.parseInt(args[0]);
        
        // Read in Strings and save to randomized queue
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            sub.rq.enqueue(str);
        }
        
        for (int i = 0; i < sub.k; i++) StdOut.println(sub.rq.dequeue());
    }
}
