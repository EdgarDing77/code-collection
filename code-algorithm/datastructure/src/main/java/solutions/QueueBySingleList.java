package solutions;

/**
 * Description:
 * 单链表实现队列
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/16
 **/
public class QueueBySingleList {

    ListNode head;
    ListNode last;

    public QueueBySingleList() {
        head = new ListNode(0);
        last = head;
    }

    public void offer(int val) {
        last.next = new ListNode(val);
        last = last.next;
    }

    public int poll() {
        if (head == last) {
            return -1;
        }
        int res = head.next.val;
        head.next = head.next.next;
        return res;
    }


    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
