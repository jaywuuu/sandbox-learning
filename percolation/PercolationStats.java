/*
 * PercolationStats.class
 * 
 * Usage: java PercolationStats <N> <T> <file>
 * Initializes a Percolation grid of size N x N.
 * Repeats percolation experiment T times and calculates the mean,
 * standard deviation, and confidence interval for percoluation to occur.
 * 
 * author: Jason Wu
 */
public class PercolationStats {
    private int N;
    private int T;
    private Percolation perc;
    private double [] x; // estimation of mean/fraction.
    
    public PercolationStats(int N, int T) {
        if (N<=0 || T<= 0) throw new 
            IllegalArgumentException("N or T is <= 0");
        
        this.N = N;
        this.T = T;
        
        perc = new Percolation(N);
        x = new double[T];
    }
    
    public double mean() {
        double m = StdStats.mean(x);
        return m;
    }
    
    public double stddev() {
        double std = 1;
        if (T == 1) return Double.NaN;
        std = StdStats.stddev(x);
        return std;
    }
    
    public double confidenceLo() {
        double confLo = 0;
        confLo = mean() - ((1.96*stddev())/Math.sqrt(T));
        return confLo;
    }
    
    public double confidenceHi() {
        double confHi = 0;
        confHi = mean() + ((1.96*stddev())/Math.sqrt(T));
        return confHi;
    }
    
    public static void main(String[] args) {
        int N_in = 0;
        int T_in = 0;
        String outputFile = "";
        boolean outToFile = false;
        int count = 0; // count number of open sites;
        Out outFile = new Out();
  
        // Check args are there
        if (args.length > 0) {
            try {
                N_in = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                StdOut.printf("First argument not a number.\n");
                System.exit(1);
            }
            
            try {
                T_in = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                StdOut.printf("Second argument not a number.\n");
                System.exit(1);
            }
        }
        
        // Get third argument if it exists for output file for visualizer.
        if (args.length >= 3) {
            outputFile = args[2];
            outToFile = true;
        }
        
        // Don't do anything if N <= 0 or T <= 0
        if (N_in <= 0 || T_in <= 0) return;
        
        PercolationStats percStats = new PercolationStats(N_in, T_in);
        
        // Perform experiment T times and record the results.
        for (int t = 0; t < percStats.T; t++) {
            // reset count
            count = 0;

            if (outToFile) {
                outFile = new Out(outputFile + Integer.toString(t) + ".txt");
                outFile.println(percStats.N);
            }
            
            // Loop until percolates
            while (!percStats.perc.percolates()) {
                // Open a site at random.
                int randi = StdRandom.uniform(percStats.N)+1;
                int randj = StdRandom.uniform(percStats.N)+1;
            
                // Increase counter if a new site is opened.
                if (!percStats.perc.isOpen(randi,randj)) count++;
                percStats.perc.open(randi, randj);
                
                // Record randi and randj to file for visualizer.
                if (outToFile) {
                    outFile.print(randi);
                    outFile.printf(" ");
                    outFile.println(randj);
                }
            
            }

            double fraction = (double) count/(double) percStats.gridSize();
            percStats.x[t] = fraction;
            
            // We're done with this percolation data structure.  Create
            // a new one.  Hope garbage collection does it's job.
            percStats.perc = new Percolation(percStats.N);
        } 
        
        // output
        StdOut.printf("mean                    = ");
        StdOut.println(percStats.mean());
        StdOut.printf("stddev                  = ");
        StdOut.println(percStats.stddev());
        StdOut.printf("95%% confidence interval = ");
        StdOut.print(percStats.confidenceLo());
        StdOut.printf(", ");
        StdOut.println(percStats.confidenceHi());
    }
    
    private int gridSize() {
        return (this.N*this.N);
    }
}