import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jackey Hua
 */
public class BinaryTrie implements Serializable {

    private Node root;
    private Character[] charArray;

    /**
     * We use a Priority Queue to build the Huffman Tree.
     *
     * @param frequencyTable Used to create a Huffman Tree.
     */
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        charArray = new Character[frequencyTable.size()];
        int index = 0;
        MinPQ<Node> pq = new MinPQ<>();
        for (Character x : frequencyTable.keySet()) {
            pq.insert(new Node(x, frequencyTable.get(x), null, null));
            charArray[index] = x;
            index += 1;
        }

        while (pq.size() > 1) {
            Node smallest = pq.delMin();
            Node secondSmallest = pq.delMin();
            Node concat = new Node('\0', smallest.freq + secondSmallest.freq, smallest, secondSmallest);
            pq.insert(concat);
        }
        root = pq.delMin();
    }

    /**
     * Call a Helper Function to recursively get the answer.
     * @param querySequence
     * @return
     */
    public Match longestPrefixMatch(BitSequence querySequence) {
        return helperPrefix(querySequence, root,  0);
    }

    private Match helperPrefix(BitSequence querySequence, Node currentNode, int index) {
        int choice = querySequence.bitAt(index);
        if (choice == 1) {
            if (currentNode.right == null) {
                if (currentNode.ch != '\0') {
                    return new Match(querySequence.firstNBits(index), currentNode.ch);
                }
                return null;
            } else {
                return helperPrefix(querySequence, currentNode.right, index + 1);
            }
        } else {
            if (currentNode.left == null) {
                if (currentNode.ch != '\0') {
                    return new Match(querySequence.firstNBits(index), currentNode.ch);
                }
                return null;
            } else {
                return helperPrefix(querySequence, currentNode.left, index + 1);
            }
        }
    }

    /**
     * Still use recursive way to retain the lookupTable.
     * @return
     */
    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> ret = new HashMap<>();
        for (Character x : charArray) {
            BitSequence bs = findPath(x, root, "");
            ret.put(x, bs);
        }
        return ret;
    }

    private BitSequence findPath(Character ch, Node currentNode, String str) {
        if (currentNode.isLeaf()) {
            if (ch.equals(currentNode.ch)) {
                return new BitSequence(str);
            }
            return null;
        }
        BitSequence left = findPath(ch, currentNode.left, str.concat("0"));
        BitSequence right = findPath(ch, currentNode.right, str.concat("1"));

        if (left != null) {
            return left;
        }

        if (right != null) {
            return right;
        }
        return null;
    }

    /**
     * Note !!!
     * It is necessary to implements Comparable
     * Because in the priority queue, we decide the priority
     * based on the value of freq.
     *
     * It is also necessary to implement serializable.
     * Because if not, it cannot use the ObjectWriter.
     */
    private class Node implements Comparable<Node>, Serializable {
        private char ch;
        private int freq;
        private Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return (this.left == null) && (this.right == null);
        }

        public int compareTo(Node another) {
            return this.freq - another.freq;
        }

    }

}
