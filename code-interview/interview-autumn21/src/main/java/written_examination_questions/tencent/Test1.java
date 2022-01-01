package written_examination_questions.tencent;

import java.util.Arrays;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/22
 **/
public class Test1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param m int整型
     * @param a ListNode类 指向彩带的起点，val表示当前节点的val，next指向下一个节点
     * @return ListNode类一维数组
     */
    public static ListNode[] solve(int m, ListNode a) {
        // write code here
        ListNode[] res = new ListNode[m];
        ListNode[] last = new ListNode[m];
        for (ListNode cur = a; cur != null; cur = cur.next) {
            int k = cur.val % m;
            if (res[k] == null) {
                res[k] = new ListNode(cur.val);
                last[k] = res[k];
            } else if (res[k] != null) {
                last[k].next = new ListNode(cur.val);
                last[k] = last[k].next;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode cur = head, pre = head;
        for (int i = 0; i < 5; i++) {
            cur = new ListNode(i + 1);
            pre.next = cur;
            pre = pre.next;
        }
        System.out.println(Arrays.toString(solve(5, head)));
    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
