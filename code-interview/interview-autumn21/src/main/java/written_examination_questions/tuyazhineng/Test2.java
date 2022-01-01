package written_examination_questions.tuyazhineng;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/14
 **/
public class Test2 {
    public ListNode removeNthFromEnd(ListNode ListNode, int n) {
        if (ListNode == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = ListNode;
        ListNode slow = dummy, fast = dummy;
        for (; n > 0; n--) {
            fast = fast.next;
        }
        for (; fast.next != null; fast = fast.next) {
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return ListNode;
    }

    class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
