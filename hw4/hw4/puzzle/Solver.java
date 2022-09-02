package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.HashMap;

public class Solver {
    private MinPQ<searchNode> pq;
    private searchNode smallest;
    private HashMap<searchNode, Integer> cache;
    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        cache = new HashMap<>();
        searchNode init = new searchNode(initial);
        pq.insert(init);
        smallest = helper();
    }

    private searchNode helper() {
        while (pq.size() > 0) {
            searchNode current = pq.delMin();
            if (current.ws.isGoal()) {
                return current;
            }
            for (WorldState x : current.getWorldState().neighbors()) {
                if (x.equals(current.getWorldState())) {
                    continue;
                }
                if (current.getParent() != null) {
                    if (x.equals(current.getParent().getWorldState())) {
                        continue;
                    }
                }
                searchNode newSN = new searchNode(x, current.getMoves() + 1, current);
                pq.insert(newSN);
            }
        }
        return null;
    }

    public int moves() {
        return smallest.getMoves();
    }

    public Iterable<WorldState> solution() {
        searchNode sn = smallest;
        Stack<WorldState> stk = new Stack<>();
        while (sn != null) {
            stk.push(sn.getWorldState());
            sn = sn.getParent();
        }
        return stk;
    }

    private class searchNode implements Comparable<searchNode>{
        private WorldState ws;
        private int numOfMoves;
        private searchNode parent;

        public searchNode(WorldState initial) {
            ws = initial;
            numOfMoves = 0;
            parent = null;
        }

        public searchNode(WorldState initial, int num, searchNode parent) {
            ws = initial;
            numOfMoves = num;
            this.parent = parent;
        }

        @Override
        public int compareTo(searchNode other) {
            if (!cache.containsKey(this)) {
                cache.put(this, this.ws.estimatedDistanceToGoal());
            }
            if (!cache.containsKey(other)) {
                cache.put(other, other.ws.estimatedDistanceToGoal());
            }
            return numOfMoves + cache.get(this) - other.numOfMoves - cache.get(other);
        }

        public WorldState getWorldState() {
            return ws;
        }

        public int getMoves() {
            return numOfMoves;
        }

        public searchNode getParent() {
            return parent;
        }

    }

}
