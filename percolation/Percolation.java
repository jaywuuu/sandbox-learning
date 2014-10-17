/*
 * Percolation.class
 * 
 * Model structure for simulating percolation in systems.
 * Constructor accepts integer N to initialize a grid of N x N blocked
 * sites.  
 * 
 * author: Jason Wu
 */
public class Percolation {
    private int N;
    private boolean [][] sites;
    private WeightedQuickUnionUF sitesUF;
    private WeightedQuickUnionUF auxUF; // needed to prevent backwash
    
    
    public Percolation(int N) {
        // Throw exception if N is <= 0
        if (N <= 0) throw new 
            IllegalArgumentException("N cannot be less than or equal to 0.");
        
        this.N = N;
        sites = new boolean[N][N];
        
        // Init all sites to blocked (boolean false)
        for (int i = 0; i<N; i++) {
            for (int j = 0; j<N; j++) {
                sites[i][j] = false;
            }
        }
     
        // Initialize unionfind object.  Have extra two sites to symbolize
        // sites connected to the top or bottom.
        // N^2 is associated with an open top site.
        // N^2+1 is associated with an open bottom site.
        // **Note: backwash is due to virtual bottom site connected to
        // all bottom sites.  So, in this case, to prevent backwashing
        // (when isFull returns true) we'll use a second UF object to keep
        // track of sites only connected to the top virtual site.  
        // I can't think of another way to do this without using a for loop
        // to check whether a new union results in a component with a site
        // on the bottom row.
        sitesUF = new WeightedQuickUnionUF(gridSize()+2);
        auxUF = new WeightedQuickUnionUF(gridSize()+1);
    }
    
    // In hindsight, it may have been easier to have extra rows and columns
    // on the perimeter of the grid.
    public void open (int i , int j) {
        // Throws exception if index is out of bounds.
        // Out of bounds if > N or < 1
        if (i < 1 | i > N+1) throw new 
            IndexOutOfBoundsException("Index i out of bounds.");
        if (j < 1 | j > N+1) throw new 
            IndexOutOfBoundsException("Index j out of bounds.");
        
        // open if blocked
        if (!this.isOpen(i, j)) {
            sites[i-1][j-1] = true;
        
            // Check to see if surrounding sites are also open.
            // If so, connect the elements using WeightedQuickUnionUF
            // Check bounds
            // Above
            if (i > 1) {
                if (this.isOpen(i-1, j)) {
                    sitesUF.union(xyTo1D(i, j), xyTo1D(i-1,j));
                    auxUF.union(xyTo1D(i, j), xyTo1D(i-1,j));
                }
            }
            // If top row, connectto N^2 element (root of top open sites).
            else {
                sitesUF.union(xyTo1D(i, j), gridSize());
                auxUF.union(xyTo1D(i, j), gridSize());
            }
            
            // Below
            if (i < N) {
                if (this.isOpen(i+1, j)) {
                    sitesUF.union(xyTo1D(i, j), xyTo1D(i+1,j));
                    auxUF.union(xyTo1D(i, j), xyTo1D(i+1,j));
                }
            }
            // If this is the bottom row, connect to N^2+1 element
            else {
                 sitesUF.union(xyTo1D(i, j), gridSize()+1);
            }
            
            // To the right.
            if (j > 1) {
                if (this.isOpen(i, j-1)) {
                    sitesUF.union(xyTo1D(i, j), xyTo1D(i,j-1));
                    auxUF.union(xyTo1D(i, j), xyTo1D(i,j-1));
                }
            }
            
            // To the left.
            if (j < N) {
                if (this.isOpen(i, j+1)) {
                    sitesUF.union(xyTo1D(i, j), xyTo1D(i,j+1));
                    auxUF.union(xyTo1D(i, j), xyTo1D(i,j+1));
                }
            }
        }
    }
    
    public boolean isOpen(int i, int j) {
        // Throws exception if index is out of bounds.
        // Out of bounds if > N or < 1
        if (i < 1 | i > N+1) throw new 
            IndexOutOfBoundsException("Index i out of bounds.");
        if (j < 1 | j > N+1) throw new 
            IndexOutOfBoundsException("Index j out of bounds.");
        
        // return true if open, false if blocked.
        return sites[i-1][j-1];
    }
    
    // isFull returns true when we're connected to the root node that
    // symbolizes a top connection, i.e. N^2 element in the data structure.
    public boolean isFull(int i, int j) {
        // Throws exception if index is out of bounds.
        // Out of bounds if > N or < 1
        if (i < 1 | i > N+1) throw new 
            IndexOutOfBoundsException("Index i out of bounds.");
        if (j < 1 | j > N+1) throw new 
            IndexOutOfBoundsException("Index j out of bounds.");
        
        // Cross reference with auxillary UF structure.
        return (sitesUF.connected(xyTo1D(i,j), gridSize()) && 
                auxUF.connected(xyTo1D(i,j), gridSize()));
    }
    
    public boolean percolates() {
        // Percolates if N^2 element of union find data structure is connected
        // to N^2+1 element.  (top connected to bottom root respectively).
        if (sitesUF.connected(gridSize(), gridSize()+1)) return true;
        
        return false;
    }

    private int xyTo1D(int i, int j) {
        int retval;
        
        retval = (i-1)*N + j-1;
        
        return retval;
    }
    
    private int gridSize() {
        return (this.N*this.N);
    }
    
    // Quick testing
    public static void main(String [] args) {
        int N_in = Integer.parseInt(args[0]);
        Percolation perc = new Percolation(N_in);
        print_grid(perc);
        perc.open(1,1);
        StdOut.printf("Opening (1,1)...\n");
        print_grid(perc);
        perc.open(1,perc.N);
        StdOut.printf("Opening (1,%d)...\n", perc.N);
        print_grid(perc);
        StdOut.printf("(1,1) connected to top: ");
        StdOut.println(perc.sitesUF.connected(
                                              perc.xyTo1D(1,1),
                                              perc.gridSize()));
        StdOut.printf("Opening entire first column...\n");
        for (int i = 0; i<perc.N; i++) perc.open(i+1, 1);
        print_grid(perc);
        StdOut.printf("(%d,1) connected to bottom: ", perc.N);
        StdOut.println(perc.sitesUF.connected(
                                              perc.xyTo1D(perc.N,1),
                                              perc.gridSize()+1));
        StdOut.printf("Percolates: %b\n", perc.percolates());
        StdOut.printf("Is (%d,1) full: %b\n", 
                      (int)perc.N/2, perc.isFull((int)perc.N/2,1));
        
    }
    
    private static void print_grid(Percolation perc) {
        for (int i = 0; i<perc.N; i++) {
            for (int j = 0; j<perc.N; j++) {
                StdOut.print(perc.isOpen(i+1,j+1));
                StdOut.printf(" ");
            }
            StdOut.printf("\n");
        }
    }
}
