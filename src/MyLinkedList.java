import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    @Override
    public int size() {
        return size;
    }

    public void set(int index, T item) {}
    public void add(int index, T item) {}
    public T get(int index) { return null; }
    public T getFirst() { return null; }
    public T getLast() { return null; }
    public void remove(int index) {}
    public void removeFirst() {}
    public void removeLast() {}
    public void sort() {}
    public int indexOf(Object object) { return -1; }
    public int lastIndexOf(Object object) { return -1; }
    public boolean exists(Object object) { return false; }
    public Object[] toArray() { return new Object[0]; }
    public void clear() {}

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node current = head;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                T val = current.data;
                current = current.next;
                return val;
            }
        };
    }
}