import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {

    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[8];
        size = 0;
    }

    private void grow() {
        Object[] newArr = new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void addLast(T item) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index out of bounds: " + index);
        }
        return (T) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index out of bounds: " + index);
        }

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;
    }

    @Override
    public void removeLast() {
        if (size == 0) {
            throw new RuntimeException("List is empty");
        }

        elements[size - 1] = null;
        size--;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    public void set(int index, T item) {}
    public void add(int index, T item) {}
    public void addFirst(T item) {}
    public T getFirst() { return null; }
    public T getLast() { return null; }
    public void removeFirst() {}
    public void sort() {}
    public int lastIndexOf(Object object) { return -1; }
    public boolean exists(Object object) { return false; }
    public Object[] toArray() { return new Object[0]; }
    public void clear() {}

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int cur = 0;

            public boolean hasNext() {
                return cur < size;
            }

            @SuppressWarnings("unchecked")
            public T next() {
                return (T) elements[cur++];
            }
        };
    }
}