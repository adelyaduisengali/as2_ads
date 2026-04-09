import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {

    private class MyNode {
        T data;
        MyNode next, prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    private MyNode head, tail;
    private int size;

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void addFirst(T item) {
        MyNode node = new MyNode(item);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode node = new MyNode(item);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size) {
            addLast(item);
            return;
        }

        MyNode cur = getNode(index);
        MyNode node = new MyNode(item);

        node.prev = cur.prev;
        node.next = cur;
        cur.prev.next = node;
        cur.prev = node;

        size++;
    }

    @Override
    public void set(int index, T item) {
        getNode(index).data = item;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        return head.data;
    }

    @Override
    public T getLast() {
        return tail.data;
    }

    @Override
    public void remove(int index) {
        MyNode node = getNode(index);

        if (node.prev != null)
            node.prev.next = node.next;
        else
            head = node.next;

        if (node.next != null)
            node.next.prev = node.prev;
        else
            tail = node.prev;

        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public void sort() {
        // insertion sort (оригинально)
        if (size < 2) return;

        MyNode current = head.next;
        while (current != null) {
            T key = current.data;
            MyNode j = current.prev;

            while (j != null && j.data.compareTo(key) > 0) {
                j.next.data = j.data;
                j = j.prev;
            }

            if (j == null) head.data = key;
            else j.next.data = key;

            current = current.next;
        }
    }

    @Override
    public int indexOf(Object obj) {
        int i = 0;
        for (MyNode cur = head; cur != null; cur = cur.next, i++) {
            if (cur.data.equals(obj)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        int i = size - 1;
        for (MyNode cur = tail; cur != null; cur = cur.prev, i--) {
            if (cur.data.equals(obj)) return i;
        }
        return -1;
    }

    @Override
    public boolean exists(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (MyNode cur = head; cur != null; cur = cur.next) {
            arr[i++] = cur.data;
        }
        return arr;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private MyNode getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        MyNode cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            MyNode cur = head;

            public boolean hasNext() {
                return cur != null;
            }

            public T next() {
                T val = cur.data;
                cur = cur.next;
                return val;
            }
        };
    }
}