
public class Board {
	private int N;
	private int[][] blocks;
	
	public Board(int[][] blocks) {
		this.N = blocks[0].length;
		
		// check bounds.
		if (N < 2 || N > 128) return;
		
		this.blocks = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.blocks[i][j] = blocks[i][j];
			}
		}
	}
	
	public int dimension() {
		return N;
	}
	
	public int hamming() {
		int priority = 0;
		
		// Count number of blocks out of place.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != 0 && blocks[i][j] != getGoalBlock(i, j)) {
					priority++;
				}
			}
		}
		
		return priority;
	}
	
	public int manhattan() {
		int priority = 0;
		
		// count distance out of place per block
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != 0) {
					priority += getGoalPositionRow(blocks[i][j]) - i 
							+ getGoalPositionCol(blocks[i][j]) - j;
				}
			}
		}
		
		return priority;
	}
	
	public boolean isGoal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != getGoalBlock(i, j)) return false;
			}
		}
		return true;
	}
	
	public Board twin() {
		Board tw = new Board(blocks);
		
		// look through rows for adjacent blocks
		for (int i = 0; i < tw.dimension(); i++) {
			for (int j = 0; j < tw.dimension()-1; j++) {
				if (blocks[i][j] != 0 && blocks[i][j+1] != 0) {
					// swap if not blank
					tw.blocks[i][j] = blocks[i][j+1];
					tw.blocks[i][j+1] = blocks[i][j];
				}
			}
		}
		return tw;
	}
	
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (this.getClass() != y.getClass()) return false;
		Board that = (Board) y;
		if (this.N != that.N) return false;
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if (this.blocks[i][j] != that.blocks[i][j]) return false;
			}
		}
		return true;
	}
	
	public Iterable<Board> neighbours() {
		Queue<Board> it = new Queue<Board>();
		return it;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	// returns the block that should be in the blocks[i][j] position.
	private int getGoalBlock(int i, int j) {
		return i * N + j + 1;
	}
	
	// return the row where the block belongs
	private int getGoalPositionRow(int block) {
		return (block - 1)/N;
	}
	
	// return the column where the block belongs
	private int getGoalPositionCol(int block) {
		return block - getGoalPositionRow(block)*N - 1;
	}
}
