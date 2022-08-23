public class mt {
    public void Static_Data() {
        /** carol dan
            carol eve
            dan carol
            alice bob
            carol eve

            alice bob
            alice bob
            fritz gritz
        */
    }

    /**
     * UK Runs
     * UK Runs
     * Compile Error
     *
     * UK Runs
     * USK Runs
     * USK Runs
     *
     * Compile Error
     * Compile Error
     * UF Runs
     *
     * blank
     * USK Runs
     * USK Runs
     * UK Runs
     */

    public static int[] sans(int[] x, int y) {
        int[] xclean = new int[x.length];
        int c = 0;
        for (int i = 0; i < x.length; i += 1) {
            if (x[i] != y) {
                xclean[c] = x[i];
                c += 1;
            }
        }
        int[] r = new int[c];
        System.arraycopy(xclean, 0 , r, 0, c);
        return r;
    }

    public static IntList ilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        if (x.first == y) {
            return ilsans(x.rest, y);
        }
        return new IntList(x.first, ilsans(x.rest, y))
    }

    public static IntList dilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        // 分治法思想
        // 将尾巴和头分别处理
        x.rest = dilsans(x.rest, y);
        if (x.first == y) {
            return x.rest;
        }
        return x;
    }

    public void testSans() {
        int[] x = new int[]{4, 5, 6};
        int y = 5;
        int[] expected = new int[]{4, 6};
        int[] actual = Sans.sans(x, y);
        assertEquals(expected, actual);
        assertEquals(x, new int[]{4, 5, 6}); // non-destructive check
    }

    public void testIlsans() {
        IntList x = IntList.of(4, 5, 6);
        int y = 5;
        IntList expected = IntList.of(4, 6);
        IntList actual = Sans.ilsans(x, y);
        assertEquals(expected, actual);
        assertEquals(x, IntList.of(4, 5, 6));

    }

    public class ArrayStack<Item> implements Stack<Item> {
        private Item[] items;
        private int size;

        public ArrayStack() {
            items = (Item[]) new Object[8];
            size = 0;
        }

        private void resize(int capacity) {
            Item newArray = (Item[]) new Object[capacity];
            System.arraycopy(items, 0, newArray, 0, size);
            items = newArray;
        }

        public void push(Item x) {
            if (usageRatio() == 1) {resize(size * 2);}
            items[size] = x;
            size += 1;
        }

        public Item pop() {
            if (size() == 0) {return null;}
            if (usageRatio() < 0.25 && items.length > 8) {resize(items.length / 2);}
            size -= 1;
            Item x = items[size];
            items[size] = null; // necessary in generic class
            return x;
        }

        public int size() {return size;}

        private double usageRatio() {return ((double) size()) / items.length;}

    }

    public interface Stack<Item> {
        void push(Item x);
        Item pop();
        int size();

        /**
         * Crucial!!!
         * How to define a default function in an interface?
         */
        default void purge(Item x) {
            ArrayStack<Item> ars = new ArrayStack<>();
            while (this.size() != 0) {
                Item temp = this.pop();
                if (!temp.equals(x)) {
                    ars.push(temp);
                }
            }
            while (ars.size() != 0) {
                this.push(ars.pop());
            }
        }
    }

    public class Combine {
        public static int combine(ComFunc f, int[] x) {
            if (x.length == 0) {
                return 0;
            }
            if (x.length == 1) {
                return x[0];
            }
            return helper(f, x, 0);
        }

        private static int helper(ComFunc f, int[] x, int index) {
            if (index == x.length) {
                return 0;
            }
            int rest = helper(f, x, index + 1);
            return f.apply(x[index], rest);
        }
    }

    public static int sumAll(int[] x) {
        return Combine.combine(new Add(), x);
    }

    public interface ListOfInts {
        void addLast(int i);

        int get(int i);

        int size();

        void set(int i, int value);

        default void plusEquals(ListOfInts x) {
            if (this.size() != x.size()) {
                return;
            }
            for (int i = 0; i < this.size(); i += 1) {
                this.set(i, this.get(i) + x.get(i));
            }
        }
    }

    public class DLListOfInts implements ListOfInts {
        public class IntNode {
            public int item;
            public IntNode next, prev;
        }
        public IntNode sentinel;
        public int size;

        public void plusEquals(DLListOfInts x) {
            if (size != x.size) {
                return;
            }
            IntNode temp = x.sentinel.next;
            for (IntNode p = sentinel.next; p != null; p = p.next) {
                p.item += temp.item;
                temp = temp.next;
            }
        }

        /**
         * for question 7c, the function changes the item of list[0], because result is a
         * refence type, which stores address.
         */

    }


}
