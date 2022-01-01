package stacks;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 * 链表栈
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public class LinkedStack<E> implements Stack<E> {
    private Deque<E> stack = new LinkedList<>();

    @Override
    public void push(E e) {
        if (e != null) {
            stack.push(e);
        }
    }

    @Override
    public E pop() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void print() {
        for (E e : stack) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
