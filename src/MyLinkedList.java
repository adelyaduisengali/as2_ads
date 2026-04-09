import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {

    // Node
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
            throw new RuntimeException("Index out of bounds: " + index);
        }

        Node current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (head == null) throw new RuntimeException("List is empty");
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) throw new RuntimeException("List is empty");
        return tail.data;
    }

    @Override
    public void remove(int index) {
        Node node = getNode(index);

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null) throw new RuntimeException("List is empty");

        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        size--;
    }

    @Override
    public void removeLast() {
        if (tail == null) throw new RuntimeException("List is empty");

        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        size--;
    }

    @Override
    public int size() {
        return size;
    }

    public void set(int index, T item) {}
    public void add(int index, T item) {}
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