package linked_list;

/**
 * Description:
 * 关于链表中的一些特征问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/6
 **/
public class FeatureSolution {

    /**
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head, slow = head;
        // 空链表、单节点链表一定不会有环
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // 快指针，一次移动两步
            slow = slow.next;      // 慢指针，一次移动一步
            if (fast == slow) {   // 快慢指针相遇，表明有环
                return true;
            }
        }
        return false; // 正常走到链表末尾，表明没有环
    }

    /**
     * 返回入环节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // 找到环节点
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }
        if (hasCycle) {
            ListNode p = head;
            while (p != slow) {
                p = p.next;
                slow = slow.next;
            }
            return p;
        } else {
            return null;
        }
    }
}
