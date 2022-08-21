import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testGet() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 10; i += 1) {
            a.addFirst(i);
        }
        int ans = a.get(2);
        assertTrue(ans == 7);
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        assertTrue(a.nextFirst == 0);
        a.addFirst(1);
        assertTrue(a.nextFirst == 7);
    }

}
