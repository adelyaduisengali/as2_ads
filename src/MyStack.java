public class MyStack<T extends Comparable<T>> {

    private MyArrayList<T> list = new MyArrayList<>();

    public void push(T item) {
        list.add(item);
    }

    public T pop() {
        T val = list.get(list.size() - 1);
        list.removeLast();
        return val;
    }
}