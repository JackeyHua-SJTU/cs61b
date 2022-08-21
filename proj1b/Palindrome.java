public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> arr = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            arr.addLast(word.charAt(i));
        }
        return arr;
    }

    public boolean isPalindrome(String word) {
        /*Deque<Character> arr = wordToDeque(word);
        boolean flag = true;
        if (arr.size() == 0 || arr.size() == 1) {
            return true;
        } else {
            for (int i = 0; i < arr.size() / 2; i += 1) {
                if (arr.removeFirst() != arr.removeLast()) {
                    flag = false;
                }
            }
            return flag;
        }*/
        Deque<Character> arr = wordToDeque(word);
        return palindromeHelper(arr, true);
    }

    private boolean palindromeHelper(Deque<Character> word, boolean flag) {
        if (word.size() == 0 || word.size() == 1) {
            return flag;
        } else {
            if (word.removeFirst() != word.removeLast()) {
                flag = false;
            }
            return palindromeHelper(word, flag);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> arr = wordToDeque(word);
        boolean flag = true;
        if (arr.size() == 1 || arr.size() == 0) {
            return true;
        } else {
            for (int i = 0; i < arr.size() / 2; i += 1) {
                if (!cc.equalChars(arr.removeFirst(), arr.removeLast())) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

}
