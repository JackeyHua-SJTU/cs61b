import java.util.*;

// mine version
public class SortedStack {
    private PriorityQueue<Integer> temp1;
    private PriorityQueue<Integer> temp2;

    public SortedStack() {
        temp1 = new PriorityQueue<>();
        temp2 = new PriorityQueue<>();
    }

    public void push(int i) {
        while (!temp1.isEmpty()) {
            temp2.add(temp1.poll());
        }
        temp2.add(i);
        while (!temp2.isEmpty()) {
            temp1.add(temp2.poll());
        }
    }

    public void pop() {
        while (!temp1.isEmpty()) {
            System.out.print(temp1.poll() + ',');
        }
    }

}

// reference version
/** pop until the given t finds its corresponding place */
public class SortedStack<Item extends Comparable<Item>> {
    private Stack<Item> a;
    private Stack<Item> b;

    public SortedStack() {
        a = new Stack<>();
        b = new Stack<>();
    }

    public void push(Item t) {
        while (!a.empty() && a.peek().compareTo(t) < 0) {
            b.push(a.pop());
        }
        a.push(t);
        while (!b.empty()) {
            a.push(b.pop());
        }
    }

    public Item poll() {
        return a.pop();
    }

}