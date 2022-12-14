import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

    OffByOne test = new OffByOne();
    @Test
    public void testEqualChars() {
        assertTrue(test.equalChars('a', 'b'));
        assertTrue(test.equalChars('A', 'B'));
        assertTrue(test.equalChars('c', 'd'));
        assertFalse(test.equalChars('b', 'B'));
    }
}
