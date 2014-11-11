/* Graph API
 * helps builds graphs of V vertices
 */

public class Graph {
	private Bag<Integer>[] adj;			// array of list types of adjacent verticies
	private int vCount;					// number of vertices
	private int edgeCount;				// number of edges
	
	// initialize graph with v vertices
	public Graph(int v) {
		vCount = v;
		adj = new Bag[v];
		for (int i = 0;i < v; i++) {
			adj[i] = new Bag<Integer>();
		}
		edgeCount = 0;
	}
	
	// Connect 2 vertices v and w
	public void connect(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		edgeCount++;
	}
	
	public boolean isConnected(int v, int w) {
		return true;
	}
}
