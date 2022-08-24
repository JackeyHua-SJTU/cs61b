import java.util.NoSuchElementException;

public class examprep6 {
    public void exceptions() {
        /**
         * mystery of 1 is 0
         * 3
         * counter is 1
         * 1
         * counter is 2
         * mystery of 6 is 2
         * !!! Note that you first run mystery(6), then print it out
         */
    }

    public AltList<Y, X> pairSwapped() {
        /**
         * 仍然是分治法的思想
         * 将第一个配对 然后再将后面的所有部分递归处理
         * 亦可以用一个变量存储当前是奇数还是偶数来决定
         * 最开始的困难之处在于如何创建新的AltList
         */
        AltList<Y, X> ret = new AltList<>(next.item, new AltList<X, Y>(item, null));
        if (next.next != null) {
            ret.next.next = this.next.next.pairSwapped();
        }
        return ret;
    }

    public Integer next() {
        if (curList == null) {
            throw new NoSuchElementException();
        }
        Integer toReturn = curList.head;
        for (int i = 0; i < k && curList != null; i += 1) {
            curList = curList.tail;
        }
        hasNext = (curList != null);
        return toReturn;
    }

}
