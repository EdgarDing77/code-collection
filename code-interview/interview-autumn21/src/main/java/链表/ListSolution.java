package 链表;

/**
 * Description:
 * 有关链表的一些问题
 *
 * @author:edgarding
 * @date:2021/6/14
 **/
public class ListSolution {

    /**
     * 删除链表的倒数第n个节点
     *
     * @param head
     * @param n    第n个节点
     * @return 返回头节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 删除倒数第n个 返回头节点
        // 一趟扫描
        // 快慢指针
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode slow = dummyNode, fast = dummyNode;
        for (; n > 0; n--) {
            fast = fast.next;
        }

        for (; fast.next != null; fast = fast.next, slow = slow.next) {
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }


    /**
     * 获取倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        for (; fast != null && k > 0; k--, fast = fast.next) {
        }
        for (; fast != null; fast = fast.next, slow = slow.next) {
        }
        return slow;
    }


    /**
     * 奇偶链表
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * <p>
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // head 为奇链表头节点 o为尾节点
        ListNode o = head;
        // p 为偶链表的头节点 e为尾节点
        ListNode p = head.next, e = p;
        for (; o.next != null && e.next != null; ) {
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow, fast;
        for (slow = head, fast = head; slow != null; ) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        ListNode cur;
        for (cur = head; cur != slow; ) {
            cur = cur.next;
            slow = slow.next;
        }
        return cur;
    }
}
