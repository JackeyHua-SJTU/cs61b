public class OffByN implements CharacterComparator {
    private int numOfN;

    public OffByN(int N) {
        numOfN = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == numOfN || y - x == numOfN;
    }
}

