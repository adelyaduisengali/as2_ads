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

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index out of bounds");
        }

        Node current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }

        return current;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public void remove(int index) {
        Node node = getNode(index);

        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;

        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;

        size--;
    }

    @Override
    public int indexOf(Object object) {
        int i = 0;
        Node current = head;

        while (current != null) {
            if (current.data != null && current.data.equals(object)) {
                return i;
            }
            current = current.next;
            i++;
        }

        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node current = head;
        int i = 0;

        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }

        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size < 2) return;

        for (Node i = head; i != null; i = i.next) {
            for (Node j = head; j != null && j.next != null; j = j.next) {
                Comparable<T> a = (Comparable<T>) j.data;
                if (a.compareTo(j.next.data) > 0) {
                    T temp = j.data;
                    j.data = j.next.data;
                    j.next.data = temp;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    public void set(int index, T item) {}
    public void add(int index, T item) {}
    public T getFirst() { return null; }
    public T getLast() { return null; }
    public void removeFirst() {}
    public void removeLast() {}
    public int lastIndexOf(Object object) { return -1; }
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