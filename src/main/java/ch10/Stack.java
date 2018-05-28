package ch10;

import java.util.Arrays;

public class Stack<T> {

    private static int DEFAULT_LEN = 16;

    private Object[] array;
    private int nextElemNum = 0;

    public Stack() {
        array = new Object[DEFAULT_LEN];
    }

    public boolean empty() {
        return nextElemNum == 0;
    }

    public void push(T i) {
        if (array.length == nextElemNum) {
            array = Arrays.copyOf(array, nextElemNum * 2);
        }
        array[nextElemNum++] = i;
    }

    public T peek() {
        if (nextElemNum == 0) {
            throw new IllegalStateException("Stack is empty. Stack should contains elements for peek");
        }
        @SuppressWarnings("unchecked")
        T value = (T)array[nextElemNum-1];
        return value;
    }

    public T pop() {
        if (nextElemNum == 0) {
            throw new IllegalStateException("Stack is empty. Stack should contains elements for pop");
        }
        @SuppressWarnings("unchecked")
        T value = (T) array[--nextElemNum];
        array[nextElemNum] = null;
        return value;
    }
}
