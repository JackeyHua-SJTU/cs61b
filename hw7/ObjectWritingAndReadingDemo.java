import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class ObjectWritingAndReadingDemo {

    public static void main(String[] args) {
        ArrayList<Integer> integerList = new ArrayList<Integer>();
        TreeSet<String> stringSet = new TreeSet<String>();
        HashMap<Character, Integer> map =  new HashMap<>();

        map.put('a', 10);
        map.put('b', 15);
        map.put('c', 12);

        integerList.add(5);
        integerList.add(10);
        integerList.add(20);

        stringSet.add("five");
        stringSet.add("ten");
        stringSet.add("twenty");

        BinaryTrie bt = new BinaryTrie(map);

        /** Create a file called somefile.bla that ObjectWriter ow will write to. */
        ObjectWriter ow = new ObjectWriter("somefile.bla");
        ow.writeObject(integerList);
        ow.writeObject(stringSet);
        ow.writeObject(bt);

        ObjectReader or = new ObjectReader("somefile.bla");
        /* Read first object from the file. */
        Object x = or.readObject();
        /* Read second object from the file. */
        Object y = or.readObject();
        Object z = or.readObject();

        /* We happen to know that that first object is a list of integers,
         * and the second object is a set of strings, so let's cast and
         * print. */

        ArrayList<Integer> xAsList = (ArrayList<Integer>) x;
        TreeSet<String> yAsSet = (TreeSet<String>) y;
        BinaryTrie getBackbt = (BinaryTrie) z;

        System.out.println(xAsList);
        System.out.println(yAsSet);
        System.out.println(getBackbt);
    }
} 
