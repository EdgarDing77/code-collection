package linked_list;

/**
 * Description:
 * 链表操作类问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/2
 **/
public class OperationSolution {

    /**
     * 删除节点
     * 给予单链表和要删除的节点，对该节点删除
     *
     * @param node 要删除的节点
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        while (l1 != null) {
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
        }
        while (l2 != null) {
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        return dummyNode.next;
    }

    /**
     * 合并两个有序链表2 递归版本
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists2(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists2(l1, l2.next);
        }
        return head;
    }

    /**
     * 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA, q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    /**
     * 合并K个升序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        } else if (n == 1) {
            return lists[0];
        } else if (n == 2) {
            return mergeTwoLists2(lists[0], lists[1]);
        }
        int mid = (n >> 1);
        ListNode[] l1 = new ListNode[mid];
        ListNode[] l2 = new ListNode[n - mid];
        for (int i = 0; i < mid; i++) {
            l1[i] = lists[i];
        }
        for (int i = mid, j = 0; j < l2.length; j++, i++) {
            l2[j] = lists[i];
        }
        return mergeTwoLists2(mergeKLists(l1), mergeKLists(l2));
    }

}


