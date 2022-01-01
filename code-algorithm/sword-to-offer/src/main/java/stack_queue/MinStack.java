package stack_queue;

/**
 * Description:
 * 剑指 Offer 30. 包含min函数的栈
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/28
 **/
public class MinStack {
    private Node head;

    public MinStack() {

    }

    public void push(int x) {

        if (head == null) {
            head = new Node(x, x, null);
        } else {
            head = new Node(x, Math.min(head.min, x), head);
        }
    }

    public void pop() {

        head = head.next;
    }

    public int top() {

        return head.val;
    }

    public int min() {

        return head.min;
    }

    private class Node {

        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {

            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
