package hw3.hash;

import edu.princeton.cs.algs4.In;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();

        // Your code here.
        /** The problem is that the number overflows. */
        List<Integer> case_1 = new ArrayList<>();
        List<Integer> case_2 = new ArrayList<>();
        List<Integer> case_3 = new ArrayList<>();
        List<Integer> case_4 = new ArrayList<>();
        List<Integer> case_5 = new ArrayList<>();
        List<Integer> case_6 = new ArrayList<>();
        List<Integer> case_7 = new ArrayList<>();
        List<Integer> case_8 = new ArrayList<>();
        for (int j = 0; j < 50; j += 1) {
            case_1.add(255);
            case_2.add(255);
            case_3.add(255);
            case_4.add(255);
            case_5.add(255);
            case_6.add(255);
            case_7.add(255);
            case_8.add(255);
        }
        case_1.add(1);
        case_2.add(2);
        case_3.add(3);
        case_4.add(4);
        case_5.add(5);
        case_6.add(6);
        case_7.add(7);
        case_8.add(8);

        ComplexOomage t1 = new ComplexOomage(case_1);
        ComplexOomage t2 = new ComplexOomage(case_2);
        ComplexOomage t3 = new ComplexOomage(case_3);
        ComplexOomage t4 = new ComplexOomage(case_4);
        ComplexOomage t5 = new ComplexOomage(case_5);
        ComplexOomage t6 = new ComplexOomage(case_6);
        ComplexOomage t7 = new ComplexOomage(case_7);
        ComplexOomage t8 = new ComplexOomage(case_8);

        deadlyList.add(t1);
        deadlyList.add(t2);
        deadlyList.add(t3);
        deadlyList.add(t4);
        deadlyList.add(t5);
        deadlyList.add(t6);
        deadlyList.add(t7);
        deadlyList.add(t8);

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
