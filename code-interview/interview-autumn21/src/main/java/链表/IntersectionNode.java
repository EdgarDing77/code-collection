package 链表;

/**
 * Description:
 * 求相交的链表
 * 设交集链表长c,链表1除交集的长度为a，链表2除交集的长度为b，有
 * <p>
 * a + c + b = b + c + a
 * 若无交集，则a + b = b + a
 * <p>
 * 两个结点不断的去对方的轨迹中寻找对方的身影，只要二人有交集，就终会相遇
 * <p>
 * 两个链表，找出它们的第一个公共节点。
 *
 * @author:edgarding
 * @date:2021/3/4
 **/
public class IntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode h1 = headA, h2 = headB;
        while (h1 != h2) {
            h1 = h1 == null ? headB : h1.next;
            h2 = h2 == null ? headA : h2.next;
        }
        return h1;
    }
}
