import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args) {
        String fileName = args[0];
        String newFileName = args[1];
        ObjectReader or = new ObjectReader(fileName);
        Object x = or.readObject();
        Object y = or.readObject();
        BinaryTrie bt = (BinaryTrie) x;
        BitSequence bs = (BitSequence) y;
        List<Character> ls = new ArrayList<>();
        while (bs.length() > 0) {
            Match mc = bt.longestPrefixMatch(bs);
            ls.add(mc.getSymbol());
            BitSequence temp = mc.getSequence();
            bs = bs.allButFirstNBits(temp.length());
        }
        char[] chars = new char[ls.size()];
        int index = 0;
        for (Character z : ls) {
            chars[index++] = z;
        }
        FileUtils.writeCharArray(newFileName, chars);
    }
}
