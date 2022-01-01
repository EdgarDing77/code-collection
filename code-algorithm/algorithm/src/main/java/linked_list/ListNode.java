package linked_list;

/**
 * Description:
 * 链表基本节点
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/1
 **/
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode() {
    }
}
