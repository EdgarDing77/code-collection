package solutions;

/**
 * Description:
 * 单链表实现栈
 * 头插实现 插入 删除即可完成栈的先入后出
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/16
 **/
public class StackBySingleList {

    ListNode head;

    public StackBySingleList() {
        head = new ListNode(0);
    }

    public void push(int val) {
        ListNode node = new ListNode(val);
        node.next = head.next;
        head.next = node;
    }

    public int pop() {
        if (head.next == null) {
            return -1;
        }
        int rec = head.next.val;
        head.next = head.next.next;
        return rec;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
