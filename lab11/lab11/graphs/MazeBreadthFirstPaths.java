package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        targetFound = false;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> temp = new Queue<>();

        temp.enqueue(s);
        announce();

        while (!temp.isEmpty()) {
            int tempNum = temp.dequeue();
            marked[tempNum] = true;
            announce();

            if (tempNum == t) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }


            for (int x : maze.adj(tempNum)) {
                if (!marked[x]) {
                    temp.enqueue(x);
                    edgeTo[x] = tempNum;
                    distTo[x] = distTo[tempNum] + 1;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

