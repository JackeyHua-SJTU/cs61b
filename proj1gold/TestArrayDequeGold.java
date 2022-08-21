import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    @Test
    public void task1() {
        for (int i = 0; i < 10; i += 1) {
            String str = "\n";
            ArrayDequeSolution<Integer> arr1 = new ArrayDequeSolution<>();
            StudentArrayDeque<Integer> arr2 = new StudentArrayDeque<>();
            for (int j = 0; j < 10; j += 1) {
                double randomNum = StdRandom.uniform();
                if (randomNum < 0.33) {
                    arr1.addFirst(j);
                    arr2.addFirst(j);
                    str += "addFirst(" + j + ")\n";
                } else if (randomNum < 0.7) {
                    arr1.addLast(j);
                    arr2.addLast(j);
                    str += "addLast(" + j + ")\n";
                } else {
                    arr1.addFirst(j + 10);
                    arr2.addFirst(j + 10);
                    str += "addFirst(" + (j + 10) + ")\n";
                }
            }
            for (int k = 0; k < 10; k += 1) {
                if (k % 2 == 0) {
                    Integer x1 = arr1.removeFirst();
                    Integer x2 = arr2.removeFirst();
                    str += "removeFirst()\n";
                    assertEquals(str, x1, x2);
                } else {
                    Integer x1 = arr1.removeLast();
                    Integer x2 = arr2.removeLast();
                    str += "removeLast()\n";
                    assertEquals(str, x1, x2);
                }
            }
        }
    }
}
