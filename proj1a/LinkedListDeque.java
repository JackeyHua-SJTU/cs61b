public class LinkedListDeque<T>{
    private class LinkedList<T> {
        T item;
        LinkedList<T> next;
        LinkedList<T> prev;
        public LinkedList(T inputItem, LinkedList inputPrev, LinkedList inputNext) {
            item = inputItem;
            next = inputNext;
            prev = inputPrev;
        }
    }

    private LinkedList<T> startSentinal;
    private LinkedList<T> endSentinal;

    private int size;

    public LinkedListDeque() {
        startSentinal = new LinkedList<T>(null, null, null);
        endSentinal = new LinkedList<T>(null, null, null);
        startSentinal.next = endSentinal;
        endSentinal.prev = startSentinal;
        size = 0;
    }

    public void addFirst(T item) {
        LinkedList<T> temp = startSentinal.next;
        LinkedList<T> newNode = new LinkedList<>(item, startSentinal, temp);
        startSentinal.next = newNode;
        temp.prev = newNode;
        size += 1;
    }

    public void addLast(T item) {
        LinkedList<T> temp = endSentinal.prev;
        LinkedList<T> newNode = new LinkedList<>(item, temp, endSentinal);
        endSentinal.prev = newNode;
        temp.next = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        if (size == 0) {
            return;
        }
        LinkedList<T> temp = startSentinal.next;
        for (int i = 0; i < size - 1; i += 1) {
            System.out.print(temp.item);
            System.out.print(' ');
            temp = temp.next;
        }
        System.out.print(temp.item);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            LinkedList<T> temp = startSentinal.next;
            startSentinal.next = temp.next;
            temp.next.prev = startSentinal;
            temp.prev = null;
            temp.next = null;
            size -= 1;
            return temp.item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        LinkedList<T> temp = endSentinal.prev;
        endSentinal.prev = temp.prev;
        temp.prev.next = endSentinal;
        size -= 1;
        temp.prev = null;
        temp.next = null;
        return temp.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size || size == 0) {
            return null;
        } else {
            LinkedList<T> temp = startSentinal.next;
            while (index > 0) {
                temp = temp.next;
            }
            return temp.item;
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, startSentinal.next);
    }

    private T getRecursiveHelper(int index, LinkedList<T> current) {
        if (current == endSentinal) {
            return null;
        } else if (index == 0) {
            return current.item;
        } else {
            return getRecursiveHelper(index - 1, current.next);
        }
    }
}
