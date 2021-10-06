package stacks;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 数组实现栈
 *
 * @author:edgarding
 * @date:2021/6/4
 **/
public class ArrayStack<E> implements Stack<E> {
    private List<E> stack = new ArrayList<>();

    @Override
    public void push(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        stack.add(e);
    }

    @Override
    public E pop() {
        if (!stack.isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return null;
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
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i) + " ");
        }
        System.out.println();
    }
}
