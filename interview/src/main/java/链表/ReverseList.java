package 链表;

/**
 * Description:
 * 反转链表
 *
 * @author:edgarding
 * @date:2021/5/23
 **/
public class ReverseList {
    /**
     * 反转整个链表
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 反转整个链表2
     * 优势：容易反转某个范围内的子链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head.next != null) {
            ListNode node = head.next;
            head.next = node.next;
            node.next = dummy.next;
            dummy.next = node;
        }
        return dummy.next;
    }

    /**
     * 反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = left; i < right; i++) {
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = pre.next;
            pre.next = tmp;
        }
        return dummy.next;
    }

    /**
     * k个一组反转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 注意pre的赋值
        ListNode dummy = new ListNode(0), pre = dummy, cur = head;
        dummy.next = head;
        if (head == null) {
            return null;
        }
        int len = 0;
        for (; head != null; len++) {
            head = head.next;
        }
        // 循环次数
        for (int i = 0; i < len / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                ListNode nextNode = cur.next;
                cur.next = nextNode.next;
                nextNode.next = pre.next;
                pre.next = nextNode;
            }
            // 第一次循环结束，pre已经将dummy的指向改变了
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode pre = new ListNode(0);
        ListNode cur = head;
        pre.next = head;
        int len = 0;
        for (; head != null; len++) {
            head = head.next;
        }
        if (len < k) {
            return pre.next;
        }
        for (int i = 0; i < len / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                ListNode node = cur.next;
                cur.next = node.next;
                node.next = pre.next;
                pre.next = node;
            }
            if (dummy.next == null) {
                dummy.next = pre.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 重排链表
     * 给定一个单链表L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 使得slow位于中间靠左边
        ListNode fast = dummy, slow = dummy;
        for (; fast != null && fast.next != null; fast = fast.next.next, slow = slow.next) {
        }
        ;
        ListNode last = slow.next;
        slow.next = null;
        last = reverseList(last);

        for (ListNode cur = head; last != null; ) {
            ListNode tmp = cur.next;
            ListNode tmp2 = last.next;
            cur.next = last;
            last.next = tmp;
            cur = tmp;
            last = tmp2;
        }
    }
}
