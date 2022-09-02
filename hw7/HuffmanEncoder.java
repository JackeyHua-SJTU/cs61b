import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> ret = new HashMap<>();
        for (char x : inputSymbols) {
            if (!ret.containsKey(x)) {
                ret.put(x, 0);
            }
            int num = ret.get(x);
            ret.put(x, num + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        String fileName = args[0];
        ObjectWriter ow = new ObjectWriter(fileName.concat(".huf"));
        char[] fileArray = FileUtils.readFile(fileName);
        Map<Character, Integer> frequencyTable = HuffmanEncoder.buildFrequencyTable(fileArray);
        BinaryTrie bt = new BinaryTrie(frequencyTable);
        ow.writeObject(bt);
        Map<Character, BitSequence> lookUpTable = bt.buildLookupTable();
        List<BitSequence> bs = new ArrayList<>();
        for (char x : fileArray) {
            bs.add(lookUpTable.get(x));
        }
        BitSequence totalSequence = BitSequence.assemble(bs);
        ow.writeObject(totalSequence);
    }

}

