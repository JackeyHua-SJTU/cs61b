import org.junit.Test;

public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int len;
    private int nextFirst;
    private int nextLast;

    /** front points to the index before the start
     *  nextFirst points to the place next first element will be put in
     *  nextLast refers to the place next last element will be put in
     */
    public ArrayDeque() {
        len = 0;
        array = (T[]) new Object[8];
        size = 8;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(T item) {
        if (len == size - 1) {
            resize();
        }
        array[nextFirst] = item;
        len += 1;
        nextFirst = (nextFirst - 1 + size) % size;
    }

    public void addLast(T item) {
        if (len == size - 1) {
            resize();
        }
        array[nextLast] = item;
        len += 1;
        nextLast = (nextLast + 1) % size;
    }

    public boolean isEmpty() {
        return (len == 0);
    }

    public int size() {
        return len;
    }

    public void printDeque() {
        if (len == 0) {
            return;
        }
        int iterateTime = (nextLast - nextFirst - 1 + size) % size;
        for (int i = 1; i < iterateTime; i += 1) {
            System.out.print((nextFirst + i) % size);
            System.out.print(' ');
        }
        System.out.print((nextFirst + iterateTime) % size);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % size;
        T num = array[nextFirst];
        len -= 1;
        resize();
        return num;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1 + size) % size;
        T num = array[nextLast];
        len -= 1;
        resize();
        return num;
    }
    @Test
    public T get(int index) {
        if (index < 0 || index >= len) {
            return null;
        } else {
            return array[(nextFirst + 1 + index) % size];
        }
    }

    private void resize() {
        if (len == size - 1) {
            T[] newArray = (T[]) new Object[size * 2];
            for (int i = 0; i < len; i += 1) {
                newArray[i] = array[(nextFirst + i + 1) % size];
            }
            array = newArray;
            size = size * 2;
            nextFirst = size - 1;
            nextLast = len;
        } else if (size >= 16 && len / size <= 0.25) {
            T[] newArray = (T[]) new Object[size / 2];
            for (int i = 0; i < len; i += 1) {
                newArray[i] = array[(nextFirst + i + 1) % size];
            }
            array = newArray;
            size = size / 2;
            nextFirst = size - 1;
            nextLast = len;
        } else {
            return;
        }
    }

}
