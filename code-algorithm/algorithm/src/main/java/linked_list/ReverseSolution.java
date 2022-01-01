package linked_list;

/**
 * Description:
 * 有关链表反转类的问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/1
 **/
public class ReverseSolution {

    /**
     * 最基础的链表反转：整个链表的反转
     *
     * @param head
     * @return
     */
    public ListNode reverse1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        for (ListNode cur = head; cur != null; ) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 对比reverse1更能反转某个范围内的子链表
     *
     * @param head
     * @return
     */
    public ListNode reverse2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (ListNode cur = head; cur.next != null; ) {
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = dummy.next;
            dummy.next = tmp;
        }
        return dummy.next;
    }

    /**
     * 翻转k个链表节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverse3(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (ListNode cur = head; cur.next != null && k > 0; k--) {
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = dummy.next;
            dummy.next = tmp;
        }
        return dummy.next;
    }

    /**
     * K个一组反转链表
     * 每k个节点一组仅在翻转
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode pre = new ListNode(0);
        pre.next = head;
        int len = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            len++;
        }
        if (len < k) {
            return head;
        }
        ListNode cur = head;
        for (int i = 0; i < len / k; i++) {
            // 这里注意j>1，因为实际翻转次数=k-1次
            for (int j = k; j > 1; j--) {
                ListNode tmp = cur.next;
                cur.next = tmp.next;
                tmp.next = pre.next;
                pre.next = tmp;
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
     * 反转链表 II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
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
        head = pre.next;
        for (int i = left; i < right; i++) {
            ListNode nex = head.next;
            head.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return dummy.next;
    }

    /**
     * 递归的方式翻转
     * 从后向前递归
     *
     * @param head
     * @return
     */
    public ListNode reverseByRec(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode last = reverseByRec(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 递归的方式反转2
     *
     * @param prev
     * @param cur
     * @return
     */
    public ListNode reverseByRec(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode tmp = null;
        tmp = cur.next;
        cur.next = prev;
        return reverseByRec(cur, tmp);
    }
}
