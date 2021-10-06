package 链表;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Description:
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author:edgarding
 * @date:2021/7/6
 **/
public class MergeKLists {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // 分治
        int n = lists.length;
        if (n == 0) {
            return null;
        } else if (n == 1) {
            return lists[0];
        } else if (n == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        int mid = (n >> 1);
        ListNode[] l1 = new ListNode[mid];
        ListNode[] l2 = new ListNode[n - mid];
        for (int i = 0; i < mid; i++) {
            l1[i] = lists[i];
        }
        for (int i = mid, j = 0; j < l2.length; i++, j++) {
            l2[j] = lists[i];
        }
        // 详细思想参考 归并排序
        return mergeTwoLists(mergeKLists(l1), mergeKLists(l2));
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    /**
     * 巧妙办法 -》 利用最小堆
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        } else if (n == 1) {
            return lists[0];
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode list : lists) {
            pq.add(list);
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }
        return dummy.next;
    }
}

