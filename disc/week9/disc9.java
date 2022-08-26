public class disc9 {
    private class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isBSTGood(TreeNode T) {
        return isBSTHelper(T, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private static boolean isBSTHelper(TreeNode T, int max, int min) {
        if (T == null) {
            return true;
        }
        if (T.val >= max || T.val <= min) {
            return false;
        }
        return isBSTHelper(T.left, T.val, min) && isBSTHelper(T.right, max, T.val);
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MIN_VALUE);
    }

}
