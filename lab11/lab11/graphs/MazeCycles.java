package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int s;
    private int[] alterEdge;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = 0;
        alterEdge = new int[maze.V()];
        alterEdge[0] = 0;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        Stack<Integer> tmp = new Stack<>();
        tmp.push(s);
        marked[s] = true;
        announce();

        while (!tmp.isEmpty()) {
            int temp = tmp.pop();

            for (int x : maze.adj(temp)) {
                if (!marked[x]) {
                    marked[x] = true;
                    alterEdge[x] = temp;
                    continue;
                }

                if (marked[x] == true && x != alterEdge[temp]) {
                    alterEdge[alterEdge[x]] = x;
                    alterEdge[x] = temp;
                    change(x);
                    announce();
                    return;
                }
            }
        }
    }

    // Helper methods go here
    private void change(int x) {
        int tmp = x;
        while (alterEdge[tmp] != x) {
            edgeTo[tmp] = alterEdge[tmp];
            tmp = alterEdge[tmp];
        }
        edgeTo[tmp] = x;
        announce();
    }
}

