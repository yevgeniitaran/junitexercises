package ch10;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StackTest {

    Stack<Integer> stack = new Stack<>();

    @Test
    public void empty_StackWithNoElements_ReturnsTrue() {
        assertTrue(stack.empty());
    }

    @Test
    public void push_ToNewStack_StackIsNotEmpty() {
        stack.push(1);
        assertFalse(stack.empty());
    }

    @Test
    public void push_ToElements_StackIsNotEmpty() {
        stack.push(1);
        assertFalse(stack.empty());
        stack.push(2);
        assertFalse(stack.empty());
    }

    @Test
    public void peek_FromOneElementsStack_getsOneElement() {
        stack.push(1);
        int value = stack.peek();
        assertEquals(1, value);
    }

    @Test(expected = IllegalStateException.class)
    public void peek_FromEmptyStack_throwException() {
        stack.peek();
    }

    @Test
    public void peek_TwoTimes_ReturnsTheSameElement() {
        stack.push(1);
        assertEquals(stack.peek(), stack.peek());
    }

    @Test
    public void pop_FromOneElementStack_retrieveOneElement() {
        stack.push(1);
        int value = stack.pop();
        assertEquals(1, value);
        assertTrue(stack.empty());
    }

    @Test(expected = IllegalStateException.class)
    public void pop_FromEmptyStack_throwsException() {
        stack.pop();
    }
}
